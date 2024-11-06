package DAO;

import TransferObject.Cirugia;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * La clase CirugiaDAO se encarga de ejecutar operaciones relacionadas a las cirugías y el antecedente de cirugías del paciente.
 * @author epale
 */
public class CirugiaDAO {
    /**
     * Recupera todas las cirugías de la base de datos.
     * @return Lista de Ciugias con su id y nombre
     * @throws SQLException Si ocurre un error en la conexión con la base de datos.
     */
    public ArrayList<Cirugia> obtenerCirugias() throws SQLException {
        ArrayList<Cirugia> listaCirugias = new ArrayList<>();
        String consultaSQL = "SELECT id, nombre FROM cirugia";
        
        PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
        ResultSet resultado = consulta.executeQuery();
        while (resultado.next()) {
            listaCirugias.add(convertirResultSetCirugia(resultado));
        }
        consulta.close();
        resultado.close();
        ConexionBaseDatos.desconectar();
        
        return listaCirugias;
    }
    
    public ArrayList<Cirugia> obtenerCirugiasDelPaciente (int idPaciente) throws SQLException {
        ArrayList<Cirugia> listaCirugias = new ArrayList<>();
        String consultaSQL = "SELECT id, nombre, fecha_aplicacion FROM antecedenteCirugia LEFT JOIN cirugia ON id_cirugia = id WHERE id_paciente = ?";
        
        PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
        consulta.setInt(1, idPaciente);
        ResultSet resultado = consulta.executeQuery();
        while (resultado.next()) {
            listaCirugias.add(convertirResultSetAntecedenteCirugia(resultado));
        }
        consulta.close();
        resultado.close();
        ConexionBaseDatos.desconectar();
        
        return listaCirugias;
    }
    
    public int agregarCirugiaAlAntecedente (int idPaciente, int idCirugia, Date fechaAplicacion) throws SQLException {
        String consultaSQL = "INSERT INTO antecedentecirugia (id_paciente, id_cirugia, fecha_aplicacion) VALUES (?,?,?)";
        PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
        consulta.setInt(1, idPaciente);
        consulta.setInt(2, idCirugia);
        consulta.setDate(3, fechaAplicacion);
        int resultado = consulta.executeUpdate();
        consulta.close();
        ConexionBaseDatos.desconectar();
        
        return resultado;
    }
    
    public int eliminarCirugiaDeAntecedente (int idPaciente, int idCirugia) throws SQLException {
        String consultaSQL = "DELETE FROM antecedenteCirugia WHERE id_paciente = ? AND id_cirugia = ?";
        PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
        consulta.setInt(1, idPaciente);
        consulta.setInt(2, idCirugia);
        int resultado = consulta.executeUpdate();
        consulta.close();
        ConexionBaseDatos.desconectar();
        
        return resultado;
    }
    
    private static Cirugia convertirResultSetCirugia (ResultSet resultado) throws SQLException {
        Cirugia Cirugia = new Cirugia();
        Cirugia.setId(resultado.getInt("id"));
        Cirugia.setNombre(resultado.getString("nombre"));
        return Cirugia;
    }
    
    private static Cirugia convertirResultSetAntecedenteCirugia (ResultSet resultado) throws SQLException {
        Cirugia Cirugia = new Cirugia();
        Cirugia.setId(resultado.getInt("id"));
        Cirugia.setNombre(resultado.getString("nombre"));
        Cirugia.setFechaAplicacion(resultado.getDate("fecha_aplicacion"));
        return Cirugia;
    }
}
