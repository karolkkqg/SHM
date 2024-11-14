    package DAO;

import TransferObject.Tratamiento;
import TransferObject.Dosis;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    int idTratamiento = -1;  // Inicializamos a -1 para identificar si no se genera el ID
    String consultaSQL = "INSERT INTO Tratamiento (id_paciente, fecha_inicio) VALUES (?, ?);";

    // Usamos RETURN_GENERATED_KEYS para obtener el ID generado automáticamente
    PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL, Statement.RETURN_GENERATED_KEYS);
    consulta.setInt(1, tratamiento.getIdPaciente());
    consulta.setString(2, tratamiento.getFechaInicio().toString());

    int resultado = consulta.executeUpdate();

    // Verificamos si la inserción fue exitosa y obtenemos el ID generado
    if (resultado > 0) {
        ResultSet generatedKeys = consulta.getGeneratedKeys();
        if (generatedKeys.next()) {
            idTratamiento = generatedKeys.getInt(1);  // Obtenemos el ID generado
        }
        generatedKeys.close();
    }
    
    consulta.close();
    ConexionBaseDatos.desconectar();

    return idTratamiento;  // Retornamos el ID generado o -1 si falló
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
    
    /**
     * Obtiene la información de todos los tratamientos junto con sus dosis asociadas.
     * @return lista de tratamientos, cada uno con su lista de dosis asociadas.
     * @throws SQLException en caso de que falle la conexión con la base de datos
     */
    

    public ArrayList<Tratamiento> obtenerTratamientosConDosis(int idPaciente) throws SQLException {
    ArrayList<Tratamiento> listaTratamientos = new ArrayList<>();
    String consultaSQL = "SELECT " +
                         "tratamiento.id AS tratamiento_id, " +
                         "tratamiento.id_paciente, " +
                         "tratamiento.fecha_inicio, " +
                         "dosis.id AS dosis_id, " +
                         "dosis.id_medicamento, " +
                         "medicamento.nombre AS medicamento_nombre, " + // Nombre del medicamento
                         "dosis.dias_duracion, " +
                         "dosis.cantidad, " +
                         "dosis.metodo_administracion, " +
                         "dosis.nota " +
                         "FROM tratamiento " +
                         "LEFT JOIN dosis ON tratamiento.id = dosis.id_tratamiento " +
                         "LEFT JOIN medicamento ON dosis.id_medicamento = medicamento.id " +
                         "WHERE tratamiento.id_paciente = ?"; // Filtramos por id_paciente

    PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
    consulta.setInt(1, idPaciente); // Establecemos el id del paciente en la consulta
    ResultSet resultado = consulta.executeQuery();

    Tratamiento tratamientoActual = null;

    while (resultado.next()) {
        int tratamientoId = resultado.getInt("tratamiento_id");
        
        // Si el tratamiento actual es nulo o es un nuevo tratamiento, creamos un nuevo objeto Tratamiento
        if (tratamientoActual == null || tratamientoActual.getId() != tratamientoId) {
            tratamientoActual = new Tratamiento();
            tratamientoActual.setId(tratamientoId);
            tratamientoActual.setIdPaciente(resultado.getInt("id_paciente"));
            tratamientoActual.setFechaInicio(resultado.getDate("fecha_inicio"));
            listaTratamientos.add(tratamientoActual);
        }
        
        // Creamos un nuevo objeto Dosis y lo agregamos al tratamiento actual
        if (resultado.getInt("dosis_id") != 0) { // Verifica que haya dosis asociadas
            Dosis dosis = new Dosis();
            dosis.setId(resultado.getInt("dosis_id"));
            dosis.setIdTratamiento(tratamientoId);
            dosis.setIdMedicamento(resultado.getInt("id_medicamento"));
            dosis.setDiasDuracion(resultado.getInt("dias_duracion"));
            dosis.setCantidad(resultado.getString("cantidad"));
            dosis.setMetodoAdministracion(resultado.getString("metodo_administracion"));
            dosis.setNota(resultado.getString("nota"));
            dosis.setNombreMedicamento(resultado.getString("medicamento_nombre")); // Asigna el nombre del medicamento

            // Agrega la dosis al tratamiento actual
            tratamientoActual.agregarDosis(dosis);
        }
    }

    consulta.close();
    resultado.close();
    ConexionBaseDatos.desconectar();

    return listaTratamientos;
}



    
    public boolean tratamientoExiste(int idTratamiento) throws SQLException {
        String consultaSQL = "SELECT COUNT(*) FROM tratamiento WHERE id = ?";
        PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
        consulta.setInt(1, idTratamiento);
        ResultSet resultado = consulta.executeQuery();
        resultado.next();
        int count = resultado.getInt(1);
        consulta.close();
        resultado.close();
        return count > 0;
    }
}
