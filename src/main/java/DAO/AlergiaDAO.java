package DAO;

import TransferObject.Alergia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AlergiaDAO {

    // Método para obtener todas las alergias
    public ArrayList<Alergia> obtenerAlergia() throws SQLException {
        ArrayList<Alergia> listaAlergia = new ArrayList<>();
        String consultaSQL = "SELECT id, nombre FROM alergia;";
        
        PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
        ResultSet resultado = consulta.executeQuery();
        while (resultado.next()) {
            listaAlergia.add(convertirResultSetAlergia(resultado));
        }
        consulta.close();
        resultado.close();
        ConexionBaseDatos.desconectar();
        
        return listaAlergia;
    }
    
    // Método para obtener las alergias específicas de un paciente
    public ArrayList<Alergia> obtenerAlergiasDelPaciente(int idPaciente) throws SQLException {
        ArrayList<Alergia> listaAlergias = new ArrayList<>();
        String consultaSQL = "SELECT a.id, a.nombre FROM antecedenteAlergia aa LEFT JOIN alergia a ON aa.id_alergia = a.id WHERE aa.id_paciente = ?";
        
        PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
        consulta.setInt(1, idPaciente);
        ResultSet resultado = consulta.executeQuery();
        while (resultado.next()) {
            listaAlergias.add(convertirResultSetAlergia(resultado));
        }
        consulta.close();
        resultado.close();
        ConexionBaseDatos.desconectar();
        
        return listaAlergias;
    }
    
    // Método para agregar una alergia al historial de un paciente
    public int agregarAlergiaAlAntecedente(int idPaciente, int idAlergia) throws SQLException {
        String consultaSQL = "INSERT INTO antecedenteAlergia (id_paciente, id_alergia) VALUES (?, ?)";
        PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
        consulta.setInt(1, idPaciente);
        consulta.setInt(2, idAlergia);
        int resultado = consulta.executeUpdate();
        consulta.close();
        ConexionBaseDatos.desconectar();
        
        return resultado;
    }
    
    // Método para eliminar una alergia del historial de un paciente
    public int eliminarAlergiaDeAntecedente(int idPaciente, int idAlergia) throws SQLException {
        String consultaSQL = "DELETE FROM antecedenteAlergia WHERE id_paciente = ? AND id_alergia = ?";
        PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
        consulta.setInt(1, idPaciente);
        consulta.setInt(2, idAlergia);
        int resultado = consulta.executeUpdate();
        consulta.close();
        ConexionBaseDatos.desconectar();
        
        return resultado;
    }

    // Método para verificar si existe un registro de una alergia específica en el historial de un paciente
    public int verificarRegistroEnAntecedente(int idPaciente, int idAlergia) throws SQLException {
        String consultaSQL = "SELECT COUNT(*) FROM antecedenteAlergia WHERE id_paciente = ? AND id_alergia = ?";
        PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
        consulta.setInt(1, idPaciente);
        consulta.setInt(2, idAlergia);
        ResultSet resultado = consulta.executeQuery();
        resultado.next();
        int cuenta = resultado.getInt(1);
        consulta.close();
        resultado.close();
        
        return cuenta;
    }
    
    // Método auxiliar para convertir un ResultSet en un objeto Alergia
    private static Alergia convertirResultSetAlergia(ResultSet resultado) throws SQLException {
        Alergia alergia = new Alergia();
        alergia.setId(resultado.getInt("id"));
        alergia.setNombre(resultado.getString("nombre"));
        return alergia;
    }
}
