package DAO;

import TransferObject.Vacuna;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;

/**
 * La clase VacunaDAO se encarga de ejecutar operaciones relacionadas a las vacunas y el antecedente de vacunas del paciente.
 * @author epale
 */
public class VacunaDAO {
    /**
     * Recupera todos los tipos de vacunas que hay en la base de datos
     * @return Lista de vacunas con su id y nombre
     * @throws SQLException si ocurre un error en la conexión con la base de datos.
     */
    public ArrayList<Vacuna> obtenerVacunas() throws SQLException {
        ArrayList<Vacuna> listaVacunas = new ArrayList<>();
        String consultaSQL = "SELECT id, nombre FROM vacuna;";
        
        PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
        ResultSet resultado = consulta.executeQuery();
        while (resultado.next()) {
            listaVacunas.add(convertirResultSetVacuna(resultado));
        }
        consulta.close();
        resultado.close();
        ConexionBaseDatos.desconectar();
        
        return listaVacunas;
    }
    
    /**
     * Recupera el antecedente de vacunas de un paciente
     * @param idPaciente
     * @return Lista del antecedente de vacunas del paciente
     * @throws SQLException Si ocurre un error en la conexión con la base de datos
     */
    public ArrayList<Vacuna> obtenerVacunasDelPaciente (int idPaciente) throws SQLException {
        ArrayList<Vacuna> listaVacunas = new ArrayList<>();
        String consultaSQL = "SELECT id, nombre, fecha_aplicacion FROM antecedentevacuna LEFT JOIN vacuna ON id_vacuna = id WHERE id_paciente = ?";
        
        PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
        consulta.setInt(1, idPaciente);
        ResultSet resultado = consulta.executeQuery();
        while (resultado.next()) {
            listaVacunas.add(convertirResultSetAntecedenteVacuna(resultado));
        }
        consulta.close();
        resultado.close();
        ConexionBaseDatos.desconectar();
        
        return listaVacunas;
    }
    
    /**
     * Agrega una vacuna al antecedente de un paciente
     * @param idPaciente
     * @param idVacuna
     * @param fechaAplicacion
     * @return 1 si la operación fue exitosa, de otra manera 0.
     * @throws SQLException Si ocurre un error durante la conexión de base de datos.
     */
    public int agregarVacunaAlAntecedente (int idPaciente, int idVacuna, Date fechaAplicacion) throws SQLException {
        String consultaSQL = "INSERT INTO antecedentevacuna (id_paciente, id_vacuna, fecha_aplicacion) VALUES (?,?,?)";
        PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
        consulta.setInt(1, idPaciente);
        consulta.setInt(2, idVacuna);
        consulta.setDate(3, fechaAplicacion);
        int resultado = consulta.executeUpdate();
        consulta.close();
        ConexionBaseDatos.desconectar();
        
        return resultado;
    }
    
    /**
     * Elimina una vacuna del antecedente del paciente
     * @param idPaciente
     * @param idVacuna
     * @param fechaAplicacion
     * @return 1 si la operación fue exitosa, de otra manera 0.
     * @throws SQLException Si ocurre un error en la conexión a la base de datos.
     */
    public int eliminarVacunaDeAntecedente (int idPaciente, int idVacuna, Date fechaAplicacion) throws SQLException {
        String consultaSQL = "DELETE FROM antecedenteVacuna WHERE id_paciente = ? AND id_vacuna = ? AND fecha_aplicacion = ?";
        PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
        consulta.setInt(1, idPaciente);
        consulta.setInt(2, idVacuna);
        consulta.setDate(3, fechaAplicacion);
        int resultado = consulta.executeUpdate();
        consulta.close();
        ConexionBaseDatos.desconectar();
        
        return resultado;
    }
    
    private static Vacuna convertirResultSetVacuna (ResultSet resultado) throws SQLException {
        Vacuna vacuna = new Vacuna();
        vacuna.setId(resultado.getInt("id"));
        vacuna.setNombre(resultado.getString("nombre"));
        return vacuna;
    }
    
    private static Vacuna convertirResultSetAntecedenteVacuna (ResultSet resultado) throws SQLException {
        Vacuna vacuna = new Vacuna();
        vacuna.setId(resultado.getInt("id"));
        vacuna.setNombre(resultado.getString("nombre"));
        vacuna.setFechaAplicacion(resultado.getDate("fecha_aplicacion"));
        return vacuna;
    }
}
