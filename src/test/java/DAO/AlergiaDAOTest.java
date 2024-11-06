package DAO;

import TransferObject.Alergia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AlergiaDAOTest {

    private static final AlergiaDAO ALERGIA_DAO = new AlergiaDAO();
    private static final Alergia ALERGIA_1 = new Alergia("Polen");
    private static final Alergia ALERGIA_2 = new Alergia("Polvo");
    private static int PACIENTE_ID;

    @BeforeClass
    public static void setUp() throws SQLException {
        limpiarTablaAntecedenteAlergia();
        limpiarTablaAlergia();
        limpiarTablaPaciente();

        PACIENTE_ID = insertarPacienteDePrueba();
        insertarAlergiasDePrueba();
    }

    @AfterClass
    public static void tearDown() throws SQLException {
        limpiarTablaAntecedenteAlergia();
        limpiarTablaAlergia();
        limpiarTablaPaciente();
    }

    @Test
    public void testObtenerAlergia() throws Exception {
        ArrayList<Alergia> esperado = new ArrayList<>();
        esperado.add(ALERGIA_1);
        esperado.add(ALERGIA_2);

        ArrayList<Alergia> resultado = ALERGIA_DAO.obtenerAlergia();

        assertEquals(esperado, resultado);
    }

    @Test
    public void testObtenerAlergiasDelPaciente() throws Exception {
        ArrayList<Alergia> alergiasDelPaciente = ALERGIA_DAO.obtenerAlergiasDelPaciente(PACIENTE_ID);
        assertNotNull(alergiasDelPaciente);
    }

    @Test
    public void testAgregarAlergiaAlAntecedente() throws Exception {
        int resultado = ALERGIA_DAO.agregarAlergiaAlAntecedente(PACIENTE_ID, ALERGIA_1.getId());
        assertEquals(1, resultado);

        int verificacion = ALERGIA_DAO.verificarRegistroEnAntecedente(PACIENTE_ID, ALERGIA_1.getId());
        assertEquals(1, verificacion);
    }

    @Test
    public void testEliminarAlergiaDeAntecedente() throws Exception {
        ALERGIA_DAO.agregarAlergiaAlAntecedente(PACIENTE_ID, ALERGIA_2.getId());

        int resultado = ALERGIA_DAO.eliminarAlergiaDeAntecedente(PACIENTE_ID, ALERGIA_2.getId());
        assertEquals(1, resultado);

        int verificacion = ALERGIA_DAO.verificarRegistroEnAntecedente(PACIENTE_ID, ALERGIA_2.getId());
        assertEquals(0, verificacion);
    }

    private static void limpiarTablaAntecedenteAlergia() throws SQLException {
        String sql = "DELETE FROM antecedenteAlergia";
        ejecutarActualizacion(sql);
    }

    private static void limpiarTablaAlergia() throws SQLException {
        String sql = "DELETE FROM alergia";
        ejecutarActualizacion(sql);
    }

    private static void limpiarTablaPaciente() throws SQLException {
        String sql = "DELETE FROM paciente";
        ejecutarActualizacion(sql);
    }

    private static int insertarPacienteDePrueba() throws SQLException {
        String sql = "INSERT INTO paciente (nombre, apellido, genero, fecha_nacimiento, correo, contrasena) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = ConexionBaseDatos.getInstancia().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, "Franco");
        ps.setString(2, "Colapinto");
        ps.setString(3, "masculino");
        ps.setDate(4, java.sql.Date.valueOf("2024-04-04"));
        ps.setString(5, "colapinti@gmail.com");
        ps.setString(6, "123");
        ps.executeUpdate();

        ResultSet keys = ps.getGeneratedKeys();
        keys.next();
        int id = keys.getInt(1);
        ps.close();
        return id;
    }

    private static void insertarAlergiasDePrueba() throws SQLException {
        String sql = "INSERT INTO alergia (nombre) VALUES (?), (?)";
        PreparedStatement ps = ConexionBaseDatos.getInstancia().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, ALERGIA_1.getNombre());
        ps.setString(2, ALERGIA_2.getNombre());
        ps.executeUpdate();

        ResultSet keys = ps.getGeneratedKeys();
        if (keys.next()) {
            ALERGIA_1.setId(keys.getInt(1));
        }
        if (keys.next()) {
            ALERGIA_2.setId(keys.getInt(1));
        }
        ps.close();
    }

    private static void ejecutarActualizacion(String sql) throws SQLException {
        PreparedStatement ps = ConexionBaseDatos.getInstancia().prepareStatement(sql);
        ps.executeUpdate();
        ps.close();
    }
}
