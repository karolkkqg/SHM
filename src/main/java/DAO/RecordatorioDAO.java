package DAO;

import TransferObject.Recordatorio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;

public class RecordatorioDAO {
    
    // Método para obtener todos los recordatorios
    public ArrayList<Recordatorio> obtenerRecordatorio() throws SQLException {
        ArrayList<Recordatorio> listaRecordatorios = new ArrayList<>();
        String consultaSQL = "SELECT id, hora FROM recordatoria;";
        
        PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
        ResultSet resultado = consulta.executeQuery();
        while (resultado.next()) {
            listaRecordatorios.add(convertirResultSetRecordatorio(resultado));
        }
        consulta.close();
        resultado.close();
        ConexionBaseDatos.desconectar();
        
        return listaRecordatorios;
    }
    
    // Método para obtener los recordatorios asociados a una dosis específica
    public ArrayList<Recordatorio> obtenerRecordatorioDeDosis(int idDosis) throws SQLException {
        ArrayList<Recordatorio> listaRecordatorios = new ArrayList<>();
        String consultaSQL = "SELECT id, hora FROM recordatoria WHERE id_dosis = ?";

        PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
        consulta.setInt(1, idDosis);
        ResultSet resultado = consulta.executeQuery();
        while (resultado.next()) {
            listaRecordatorios.add(convertirResultSetRecordatorio(resultado));
        }
        consulta.close();
        resultado.close();
        ConexionBaseDatos.desconectar();

        return listaRecordatorios;
    }

    // Método para agregar un nuevo recordatorio
    public int agregarRecordatorio(int idDosis, LocalTime hora) throws SQLException {
        String consultaSQL = "INSERT INTO recordatoria (id_dosis, hora) VALUES (?, ?)";
        PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
        consulta.setInt(1, idDosis);
        consulta.setTime(2, Time.valueOf(hora));
        int resultado = consulta.executeUpdate();
        consulta.close();
        ConexionBaseDatos.desconectar();
        
        return resultado;
    }
    
    // Método para eliminar un recordatorio específico
    public int eliminarRecordatorio(int idRecordatorio) throws SQLException {
        String consultaSQL = "DELETE FROM recordatoria WHERE id = ?";
        PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
        consulta.setInt(1, idRecordatorio);
        int resultado = consulta.executeUpdate();
        consulta.close();
        ConexionBaseDatos.desconectar();
        
        return resultado;
    }

    // Método auxiliar para convertir un ResultSet en un objeto Recordatorio
    private static Recordatorio convertirResultSetRecordatorio(ResultSet resultado) throws SQLException {
        Recordatorio recordatorio = new Recordatorio();
        recordatorio.setId(resultado.getInt("id"));
        Time time = resultado.getTime("hora");
        if (time != null) {
            recordatorio.setHora(time.toLocalTime());
        } else {
            recordatorio.setHora(null); 
        }
        return recordatorio;
    }
}
