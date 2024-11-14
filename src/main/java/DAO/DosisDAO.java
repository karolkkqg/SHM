package DAO;

import TransferObject.Dosis;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author luis_cvqz
 */
public class DosisDAO {
    
    /**
     * Obtiene todas las dosis de la base de datos
     * @return lista de todas las dosis registradas en la base de datos
     * @throws SQLException en caso de que falle la conexión con la base de datos
     */
    public ArrayList<Dosis> obtenerDosis() throws SQLException {
        ArrayList<Dosis> listaDosis = new ArrayList<>();
        String consultaSQL = "SELECT id, id_tratamiento, id_medicamento, dias_duracion, cantidad, metodo_administracion, nota FROM dosis;";
        
        PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
        ResultSet resultado = consulta.executeQuery();
        while (resultado.next()) {
            listaDosis.add(convertirResultSetDosis(resultado));
        }

        consulta.close();
        resultado.close();
        ConexionBaseDatos.desconectar();
        
        return listaDosis;
    }

    /**
     * Obtiene todas las dosis de un tratamiento
     * @param idTratamiento del que se van a obtener las dosis
     * @return lista de todas las dosis de un tratamiento
     * @throws SQLException en caso de que falle la conexión con la base de datos
     */
    public ArrayList<Dosis> obtenerDosisDelTratamiento(int idTratamiento) throws SQLException {
        ArrayList<Dosis> listaDosis = new ArrayList<>();
        String consultaSQL = "SELECT id, id_tratamiento, id_medicamento, dias_duracion, cantidad, metodo_administracion, nota FROM dosis WHERE id_tratamiento = ?";
        
        PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
        consulta.setInt(1, idTratamiento);
        ResultSet resultado = consulta.executeQuery();
        while (resultado.next()) {
            listaDosis.add(convertirResultSetDosis(resultado));
        }

        consulta.close();
        resultado.close();
        ConexionBaseDatos.desconectar();
        
        return listaDosis;
    }

    /**
     * Agrega una dosis a un tratamiento
     * @param dosis que se va a agregar
     * @return 1 si se agrega correctamente, 0 si no se agrega
     * @throws SQLException en caso de que falle la conexión con la base de datos
     */
    public int agregarDosisAlTratamiento(Dosis dosis) throws SQLException {
    int resultado = 0;
    String consultaSQL = "INSERT INTO dosis (id_tratamiento, id_medicamento, dias_duracion, cantidad, metodo_administracion, nota) VALUES (?,?,?,?,?,?)";

    PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
    consulta.setInt(1, dosis.getIdTratamiento());
    consulta.setInt(2, dosis.getIdMedicamento());
    consulta.setInt(3, dosis.getDiasDuracion());
    consulta.setString(4, dosis.getCantidad());
    consulta.setString(5, dosis.getMetodoAdministracion());
    consulta.setString(6, dosis.getNota());

    resultado = consulta.executeUpdate();
    consulta.close();
    ConexionBaseDatos.desconectar();
    
    return resultado;
}

    /**
     * Elimina una dosis de un tratamiento
     * @param idDosis de la dosis que se va a eliminar
     * @return 1 si se elimina correctamente, 0 si no se elimina
     * @throws SQLException en caso de que falle la conexión con la base de datos
     */
    public int eliminarDosisDelTratamiento(int idDosis) throws SQLException {
        int resultado = 0;
        String consultaSQL = "DELETE FROM dosis WHERE id = ?;";

        PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
        consulta.setInt(1, idDosis);

        resultado = consulta.executeUpdate();
        consulta.close();
        ConexionBaseDatos.desconectar();
        
        return resultado;
    }

    /**
     * Modifica una dosis de un tratamiento
     * @param dosis que se va a modificar
     * @return 1 si se modifica correctamente, 0 si no se modifica
     * @throws SQLException en caso de que falle la conexión con la base de datos
     */
    public int modificarDosisDelTratamiento(Dosis dosis) throws SQLException {
        int resultado = 0;
        String consultaSQL = "UPDATE dosis SET id_tratamiento = ?, id_medicamento = ?, dias_duracion = ?, cantidad = ?, metodo_administracion = ?, nota = ? WHERE id = ?";

        PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
        consulta.setInt(1, dosis.getIdTratamiento());
        consulta.setInt(2, dosis.getIdMedicamento());
        consulta.setInt(3, dosis.getDiasDuracion());
        consulta.setString(4, dosis.getCantidad());
        consulta.setString(5, dosis.getMetodoAdministracion());
        consulta.setString(6, dosis.getNota());
        consulta.setInt(7, dosis.getId());
        
        resultado = consulta.executeUpdate();
        consulta.close();
        ConexionBaseDatos.desconectar();
        
        return resultado;
    }
    
    public int obtenerIdDosis(int idMedicamento, int diasDuracion, String cantidad, String metodoAdministracion, String nota) throws SQLException {
    int idDosis = -1;
    String consultaSQL = "SELECT id FROM dosis WHERE id_medicamento = ? AND dias_duracion = ? AND cantidad = ? AND metodo_administracion = ? AND nota = ?";

    PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
    consulta.setInt(1, idMedicamento);
    consulta.setInt(2, diasDuracion);
    consulta.setString(3, cantidad);
    consulta.setString(4, metodoAdministracion);
    consulta.setString(5, nota);

    ResultSet resultado = consulta.executeQuery();
    
    // Si se encuentra una dosis que coincide con los criterios, se guarda el id
    if (resultado.next()) {
        idDosis = resultado.getInt("id");
    }

    consulta.close();
    resultado.close();
    ConexionBaseDatos.desconectar();

    return idDosis; // Regresa -1 si no se encontró la dosis
}

    public int obtenerIdTratamiento(int idMedicamento, int diasDuracion, String cantidad, String metodoAdministracion, String nota) throws SQLException {
    int idTratamiento = -1;
    String consultaSQL = "SELECT id_tratamiento FROM dosis WHERE id_medicamento = ? AND dias_duracion = ? AND cantidad = ? AND metodo_administracion = ? AND nota = ?";

    PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
    consulta.setInt(1, idMedicamento);
    consulta.setInt(2, diasDuracion);
    consulta.setString(3, cantidad);
    consulta.setString(4, metodoAdministracion);
    consulta.setString(5, nota);

    ResultSet resultado = consulta.executeQuery();
    
    // Si se encuentra una dosis que coincide con los criterios, se obtiene el id_tratamiento
    if (resultado.next()) {
        idTratamiento = resultado.getInt("id_tratamiento");
    }

    consulta.close();
    resultado.close();
    ConexionBaseDatos.desconectar();

    return idTratamiento; // Regresa -1 si no se encontró el tratamiento
}

    public int actualizarDosis(Dosis dosis) throws SQLException {
    int resultado = 0;
    String consultaSQL = "UPDATE dosis SET dias_duracion = ?, cantidad = ?, metodo_administracion = ?, nota = ? WHERE id_tratamiento = ?";

    PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
    consulta.setInt(1, dosis.getDiasDuracion());
    consulta.setString(2, dosis.getCantidad());
    consulta.setString(3, dosis.getMetodoAdministracion());
    consulta.setString(4, dosis.getNota());
    consulta.setInt(5, dosis.getIdTratamiento()); // El ID de la dosis para especificar la fila a actualizar

    resultado = consulta.executeUpdate();
    consulta.close();
    ConexionBaseDatos.desconectar();
    
    return resultado;
}

    /**
     * Convierte un ResultSet en un objeto Dosis
     * @param resultado ResultSet con los datos de la dosis
     * @return dosis con los datos del ResultSet
     * @throws SQLException en caso de que falle la conexión con la base de datos
     */
    private static Dosis convertirResultSetDosis(ResultSet resultado) throws SQLException {
        Dosis dosis = new Dosis();
        dosis.setId(resultado.getInt("id"));
        dosis.setIdTratamiento(resultado.getInt("id_tratamiento"));
        dosis.setIdMedicamento(resultado.getInt("id_medicamento"));
        dosis.setDiasDuracion(resultado.getInt("dias_duracion"));
        dosis.setCantidad(resultado.getString("cantidad"));
        dosis.setMetodoAdministracion(resultado.getString("metodo_administracion"));
        dosis.setNota(resultado.getString("nota"));
        
        return dosis;
    }
}
