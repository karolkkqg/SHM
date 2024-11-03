package DAO;

import TransferObject.Tratamiento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author luis_cvqz
 */
public class TratamientoDAO {

    /**
     * Obtiene todos los tratamientos de la base de datos
     * @return lista de todos los tratamientos registrados en la base de datos
     * @throws SQLException en caso de que falle la conexión con la base de datos
     */
    public ArrayList<Tratamiento> obtenerTratamientos() throws SQLException {
        ArrayList<Tratamiento> listaTratamientos = new ArrayList<>();
        String consultaSQL = "SELECT id, id_paciente, fecha_inicio FROM Tratamiento;";

        PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
        ResultSet resultado = consulta.executeQuery();
        while (resultado.next()) {
            listaTratamientos.add(convertirResultSetTratamiento(resultado));
        }

        consulta.close();
        resultado.close();
        ConexionBaseDatos.desconectar();

        return listaTratamientos;
    }

    /**
     * Obtiene un tratamiento por ID de paciente
     * @param idPaciente del paciente del que se van a obtener los tratamientos
     * @return objeto Tratamiento con los datos del tratamiento
     * @throws SQLException en caso de que falle la conexión con la base de datos
     */
    public ArrayList<Tratamiento> obtenerTratamientosPorPaciente(int idPaciente) throws SQLException {
        ArrayList<Tratamiento> listaTratamientos = new ArrayList<>();
        String consultaSQL = "SELECT id, id_paciente, fecha_inicio FROM Tratamiento WHERE id_paciente = ?";

        PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
        consulta.setInt(1, idPaciente);
        ResultSet resultado = consulta.executeQuery();
        while (resultado.next()) {
            listaTratamientos.add(convertirResultSetTratamiento(resultado));
        }

        consulta.close();
        resultado.close();
        ConexionBaseDatos.desconectar();

        return listaTratamientos;
    }

    /**
     * Agrega un tratamiento a la base de datos
     * @param tratamiento que se va a agregar
     * @return 1 si se agrega correctamente, 0 si no
     * @throws SQLException en caso de que falle la conexión con la base de datos
     */
    public int agregarTratamiento(Tratamiento tratamiento) throws SQLException {
        int resultado = 0;
        String consultaSQL = "INSERT INTO Tratamiento (id_paciente, fecha_inicio) VALUES (?, ?);";

        PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
        consulta.setInt(1, tratamiento.getIdPaciente());
        consulta.setString(2, tratamiento.getFechaInicio().toString());

        resultado = consulta.executeUpdate();
        consulta.close();
        ConexionBaseDatos.desconectar();

        return resultado;
    }

    /**
     * Convierte un ResultSet a un objeto Tratamiento
     * @param resultado ResultSet con los datos del tratamiento
     * @return objeto Tratamiento con los datos del tratamiento
     * @throws SQLException en caso de que falle la conexión con la base de datos
     */
    public Tratamiento convertirResultSetTratamiento(ResultSet resultado) throws SQLException{
        Tratamiento tratamiento = new Tratamiento();

        tratamiento.setId(resultado.getInt("id"));
        tratamiento.setIdPaciente(resultado.getInt("id_paciente"));
        tratamiento.setFechaInicio(resultado.getDate("fecha_inicio"));

        return tratamiento;
    }
}
