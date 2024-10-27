package DAO;

import TransferObject.Vacuna;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class VacunaDAO {
    public ArrayList<Vacuna> obtenerVacunas() throws SQLException {
        ArrayList<Vacuna> listaVacunas = new ArrayList<>();
        String consultaSQL = "";
        
        PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
        ResultSet resultado = consulta.executeQuery();

        while (resultado.next()) {
            listaVacunas.add(convertirResultSetAVacuna(resultado));
        }

        consulta.close();
        resultado.close();
        ConexionBaseDatos.desconectar();
        
        return listaVacunas;
    }
    
    public ArrayList<Vacuna> obtenerVacunasDelPaciente (int idPaciente) throws SQLException {
        return null;
    }
    
    public int agregarVacunaAlAntecedente (int idPaciente, int idVacuna) throws SQLException {
        return -1;
    }
    
    public int eliminarVacunaDeAntecedente (int idPaciente, int idVacuna) throws SQLException {
        return -1;
    }
    
    private static Vacuna convertirResultSetAVacuna (ResultSet resultado) throws SQLException {
        Vacuna vacuna = new Vacuna();
        vacuna.setId(resultado.getInt("id"));
        vacuna.setNombre(resultado.getString("nombre"));
        return vacuna;
    }
}
