package DAO;

import TransferObject.Recordatorio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;

public class RecordatorioDAO {
    public ArrayList<Recordatorio> obtenerRecordatorio() throws SQLException {
        ArrayList<Recordatorio> listaAlergia = new ArrayList<>();
        String consultaSQL = "SELECT id, hora FROM recordatorio;";
        
        PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
        ResultSet resultado = consulta.executeQuery();
        while (resultado.next()) {
            listaAlergia.add(convertirResultSetRecordatorio(resultado));
        }
        consulta.close();
        resultado.close();
        ConexionBaseDatos.desconectar();
        
        return listaAlergia;
    }
    
    public ArrayList<Recordatorio> obtenerRecodatorioDeDosisPaciente (int idPaciente, int idDosis) throws SQLException {
        ArrayList<Recordatorio> listaAlergias = new ArrayList<>();
        String consultaSQL = "SELECT recordatorio.id, recordatorio.hora FROM recordatorio LEFT JOIN dosis ON recordatorio.id_dosis = dosis.id WHERE id_dosis = ? AND dosis.id_paciente = ?;";
        
        PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
        consulta.setInt(1, idPaciente);
        consulta.setInt(2, idDosis);
        ResultSet resultado = consulta.executeQuery();
        while (resultado.next()) {
            listaAlergias.add(convertirResultSetRecordatorio(resultado));
        }
        consulta.close();
        resultado.close();
        ConexionBaseDatos.desconectar();
        
        return listaAlergias;
    }
    
    public int agregarAlergiaAlAntecedente (int idRecordatorio, LocalTime hora) throws SQLException {
        String consultaSQL = "INSERT INTO recordatorio (id, hora) VALUES (?,?)";
        PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
        consulta.setInt(1, idRecordatorio);
        consulta.setTime(2, Time.valueOf(hora));
        int resultado = consulta.executeUpdate();
        consulta.close();
        ConexionBaseDatos.desconectar();
        
        return resultado;
    }
    
    public int eliminarAlergiaDeAntecedente (int idRecordatorio) throws SQLException {
        String consultaSQL = "DELETE FROM recordatorio WJERE id = ?;";
        PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
        consulta.setInt(1, idRecordatorio);
        int resultado = consulta.executeUpdate();
        consulta.close();
        ConexionBaseDatos.desconectar();
        
        return resultado;
    }
    
    private static Recordatorio convertirResultSetRecordatorio (ResultSet resultado) throws SQLException {
        Recordatorio alergia = new Recordatorio();
        alergia.setId(resultado.getInt("id"));
        Time time = resultado.getTime("hora");
        if (time != null) {
            alergia.setHora(time.toLocalTime());
        } else {
            alergia.setHora(null); 
        }

        return alergia;
    }
}
