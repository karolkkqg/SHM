package DAO;

import TransferObject.Alergia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AlergiaDAO {
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
    
    public ArrayList<Alergia> obtenerAlergiasDelPaciente (int idPaciente) throws SQLException {
        ArrayList<Alergia> listaAlergias = new ArrayList<>();
        String consultaSQL = "SELECT id, nombre FROM antecedeAlergias LEFT JOIN alergia ON id_vacuna = id WHERE id_paciente = ?";
        
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
    
    public int agregarAlergiaAlAntecedente (int idPaciente, int idAlergia) throws SQLException {
        String consultaSQL = "INSERT INTO antecedenteAlergia (id_paciente, id_alergia) VALUES (?,?)";
        PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
        consulta.setInt(1, idPaciente);
        consulta.setInt(2, idAlergia);
        int resultado = consulta.executeUpdate();
        consulta.close();
        ConexionBaseDatos.desconectar();
        
        return resultado;
    }
    
    public int eliminarAlergiaDeAntecedente (int idPaciente, int idAlergia) throws SQLException {
        String consultaSQL = "DELETE FROM antecedenteAlergia WJERE id_paciente = ? AND id_alergia = ?;";
        PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
        consulta.setInt(1, idPaciente);
        consulta.setInt(2, idAlergia);
        int resultado = consulta.executeUpdate();
        consulta.close();
        ConexionBaseDatos.desconectar();
        
        return resultado;
    }
    
    private static Alergia convertirResultSetAlergia (ResultSet resultado) throws SQLException {
        Alergia alergia = new Alergia();
        alergia.setId(resultado.getInt("id"));
        alergia.setNombre(resultado.getString("nombre"));
        return alergia;
    }
}
