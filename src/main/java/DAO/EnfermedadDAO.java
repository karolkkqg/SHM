package DAO;

import TransferObject.Enfermedad;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EnfermedadDAO {
    /**
     * Regresa una lista con todas las enfermedades registradas en la base de datos
     * @return Lista de objetos Enfermedad con su id y nombre
     * @throws SQLException 
     */
    public ArrayList<Enfermedad> obtenerEnfermedades() throws SQLException {
        ArrayList<Enfermedad> listaEnfermedades = new ArrayList<>();
        String consultaSQL = "SELECT id, nombre FROM enfermedad";
        
        PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
        ResultSet resultado = consulta.executeQuery();
        while (resultado.next()) {
            listaEnfermedades.add(convertirResultSetEnfermedad(resultado));
        }
        consulta.close();
        resultado.close();
        ConexionBaseDatos.desconectar();
        
        return listaEnfermedades;
    }
    
    public ArrayList<Enfermedad> obtenerEnfermedadesDelPaciente (int idPaciente) throws SQLException {
        ArrayList<Enfermedad> listaEnfermedades = new ArrayList<>();
        String consultaSQL = "SELECT id, nombre FROM antecedenteEnfermedad LEFT JOIN enfermedad ON id_enfermedad = id WHERE id_paciente = ?";
        
        PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
        consulta.setInt(1, idPaciente);
        ResultSet resultado = consulta.executeQuery();
        while (resultado.next()) {
            listaEnfermedades.add(convertirResultSetEnfermedad(resultado));
        }
        consulta.close();
        resultado.close();
        ConexionBaseDatos.desconectar();
        
        return listaEnfermedades;
    }
    
    public int agregarEnfermedadAlAntecedente (int idPaciente, int idEnfermedad) throws SQLException {
        String consultaSQL = "INSERT INTO antecedenteEnfermedad (id_paciente, id_enfermedad) VALUES (?,?)";
        PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
        consulta.setInt(1, idPaciente);
        consulta.setInt(2, idEnfermedad);
        int resultado = consulta.executeUpdate();
        consulta.close();
        ConexionBaseDatos.desconectar();
        
        return resultado;
    }
    
    /**
     * Elimina una enfermedad del antecedente médico del paciente
     * @param idPaciente
     * @param idEnfermedad
     * @return 1 si la operación fue exitosa, 0 si no lo fue
     * @throws SQLException 
     */
    public int eliminarEnfermedadDeAntecedente (int idPaciente, int idEnfermedad) throws SQLException {
        String consultaSQL = "DELETE FROM antecedenteEnfermedad WHERE id_paciente = ? AND id_enfermedad = ?";
        PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
        consulta.setInt(1, idPaciente);
        consulta.setInt(2, idEnfermedad);
        int resultado = consulta.executeUpdate();
        consulta.close();
        ConexionBaseDatos.desconectar();
        
        return resultado;
    }
    
    private static Enfermedad convertirResultSetEnfermedad (ResultSet resultado) throws SQLException {
        Enfermedad enfermedad = new Enfermedad();
        enfermedad.setId(resultado.getInt("id"));
        enfermedad.setNombre(resultado.getString("nombre"));
        return enfermedad;
    }
}
