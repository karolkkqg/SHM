package DAO;

import TransferObject.Medicamento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedicamentoDAO {
    public ArrayList<Medicamento> obtenerMedicamento() throws SQLException {
        ArrayList<Medicamento> listaMedicamentos = new ArrayList<>();
        String consultaSQL = "SELECT id, nombre FROM medicamento;";
        
        PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
        ResultSet resultado = consulta.executeQuery();
        while (resultado.next()) {
            listaMedicamentos.add(convertirResultSetMedicamento(resultado));
        }
        consulta.close();
        resultado.close();
        ConexionBaseDatos.desconectar();
        
        return listaMedicamentos;
    }
    
    public ArrayList<Medicamento> obtenerMedicamentosDelPaciente (int idPaciente) throws SQLException {
        ArrayList<Medicamento> listaMedicamentos = new ArrayList<>();
        String consultaSQL = "SELECT id, nombre FROM antecedeMedicamento LEFT JOIN medicamento ON id_vacuna = id WHERE id_paciente = ?";
        
        PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
        consulta.setInt(1, idPaciente);
        ResultSet resultado = consulta.executeQuery();
        while (resultado.next()) {
            listaMedicamentos.add(convertirResultSetMedicamento(resultado));
        }
        consulta.close();
        resultado.close();
        ConexionBaseDatos.desconectar();
        
        return listaMedicamentos;
    }
    
    public int agregarMedicamentoAlAntecedente (int idPaciente, int idMedicamento) throws SQLException {
        String consultaSQL = "INSERT INTO antecedenteMedicamento (id_paciente, id_medicamento) VALUES (?,?)";
        PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
        consulta.setInt(1, idPaciente);
        consulta.setInt(2, idMedicamento);
        int resultado = consulta.executeUpdate();
        consulta.close();
        ConexionBaseDatos.desconectar();
        
        return resultado;
    }
    
    public int eliminarVacunaDeAntecedente (int idPaciente, int idMedicamento) throws SQLException {
        String consultaSQL = "DELETE FROM antecedenteMedicamento WJERE id_paciente = ? AND id_medicamento = ?;";
        PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
        consulta.setInt(1, idPaciente);
        consulta.setInt(2, idMedicamento);
        int resultado = consulta.executeUpdate();
        consulta.close();
        ConexionBaseDatos.desconectar();
        
        return resultado;
    }
    
    private static Medicamento convertirResultSetMedicamento (ResultSet resultado) throws SQLException {
        Medicamento medicamento = new Medicamento();
        medicamento.setId(resultado.getInt("id"));
        medicamento.setNombre(resultado.getString("nombre"));
        return medicamento;
    }
    
}
