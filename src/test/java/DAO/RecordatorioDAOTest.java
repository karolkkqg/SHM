package DAO;

import TransferObject.Recordatorio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class RecordatorioDAOTest {

    private static final RecordatorioDAO RECORDATORIO_DAO = new RecordatorioDAO();
    private static final Recordatorio RECORDATORIO_1 = new Recordatorio();
    private static final Recordatorio RECORDATORIO_2 = new Recordatorio();
    private static int PACIENTE_ID;
    private static int TRATAMIENTO_ID;
    private static int MEDICAMENTO_ID;
    private static int DOSIS_ID;

    @BeforeClass
    public static void setUpClass() throws SQLException {
        limpiarTabla("dosis");
        limpiarTabla("tratamiento");
        limpiarTabla("paciente");
        limpiarTabla("medicamento");

        RECORDATORIO_1.setHora(LocalTime.of(8, 0));
        RECORDATORIO_2.setHora(LocalTime.of(20, 0));

        PACIENTE_ID = insertarPacienteDePrueba();

        TRATAMIENTO_ID = insertarTratamientoDePrueba(PACIENTE_ID);

        MEDICAMENTO_ID = insertarMedicamentoDePrueba();

        DOSIS_ID = insertarDosisDePrueba(TRATAMIENTO_ID, MEDICAMENTO_ID);
    }

    @Before
    public void setUp() throws SQLException {
        limpiarTabla("recordatoria");

        RECORDATORIO_DAO.agregarRecordatorio(DOSIS_ID, RECORDATORIO_1.getHora());
        RECORDATORIO_DAO.agregarRecordatorio(DOSIS_ID, RECORDATORIO_2.getHora());
    }

    @After
    public void tearDown() throws SQLException {
        limpiarTabla("recordatoria");
    }

    @AfterClass
    public static void tearDownClass() throws SQLException {
        limpiarTabla("dosis");
        limpiarTabla("tratamiento");
        limpiarTabla("paciente");
        limpiarTabla("medicamento");
    }

    @Test
    public void testObtenerRecordatorio() throws Exception {
        ArrayList<Recordatorio> recordatorios = RECORDATORIO_DAO.obtenerRecordatorio();
        assertEquals("Debe haber exactamente 2 recordatorios en la base de datos", 2, recordatorios.size());
    }

    @Test
    public void testObtenerRecordatorioDeDosis() throws Exception {
        ArrayList<Recordatorio> recordatorios = RECORDATORIO_DAO.obtenerRecordatorioDeDosis(DOSIS_ID);
        assertNotNull(recordatorios);
        assertEquals(2, recordatorios.size());
        assertTrue(recordatorios.stream().anyMatch(r -> r.getHora().equals(RECORDATORIO_1.getHora())));
        assertTrue(recordatorios.stream().anyMatch(r -> r.getHora().equals(RECORDATORIO_2.getHora())));
    }

    @Test
    public void testEliminarRecordatorio() throws Exception {
        ArrayList<Recordatorio> recordatorios = RECORDATORIO_DAO.obtenerRecordatorioDeDosis(DOSIS_ID);
        assertTrue(recordatorios.size() > 0);
        int idRecordatorioAEliminar = recordatorios.get(0).getId();

        int resultado = RECORDATORIO_DAO.eliminarRecordatorio(idRecordatorioAEliminar);
        assertEquals(1, resultado);

        ArrayList<Recordatorio> recordatoriosDespues = RECORDATORIO_DAO.obtenerRecordatorioDeDosis(DOSIS_ID);
        assertFalse(recordatoriosDespues.stream().anyMatch(r -> r.getId() == idRecordatorioAEliminar));
    }

    @Test
    public void testAgregarRecordatorio() throws Exception {
        LocalTime nuevaHora = LocalTime.of(14, 0);
        int resultado = RECORDATORIO_DAO.agregarRecordatorio(DOSIS_ID, nuevaHora);
        assertEquals(1, resultado);

        ArrayList<Recordatorio> recordatorios = RECORDATORIO_DAO.obtenerRecordatorio();
        assertTrue(recordatorios.stream().anyMatch(r -> r.getHora().equals(nuevaHora)));
    }

    private static void limpiarTabla(String nombreTabla) throws SQLException {
        String limpiarSQL = "DELETE FROM " + nombreTabla;
        PreparedStatement limpiar = ConexionBaseDatos.getInstancia().prepareStatement(limpiarSQL);
        limpiar.executeUpdate();
        limpiar.close();
    }

    private static int insertarPacienteDePrueba() throws SQLException {
        String sql = "INSERT INTO paciente (nombre, apellido, genero, fecha_nacimiento, correo, contrasena) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = ConexionBaseDatos.getInstancia().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, "PacientePrueba");
        ps.setString(2, "ApellidoPrueba");
        ps.setString(3, "masculino");
        ps.setDate(4, java.sql.Date.valueOf("1990-01-01"));
        ps.setString(5, "pacienteprueba@gmail.com");
        ps.setString(6, "password123");
        ps.executeUpdate();

        ResultSet keys = ps.getGeneratedKeys();
        keys.next();
        int id = keys.getInt(1);
        ps.close();
        return id;
    }

    private static int insertarTratamientoDePrueba(int pacienteId) throws SQLException {
        String sql = "INSERT INTO tratamiento (id_paciente, fecha_inicio) VALUES (?, ?)";
        PreparedStatement ps = ConexionBaseDatos.getInstancia().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, pacienteId);
        ps.setDate(2, java.sql.Date.valueOf("2024-01-01"));
        ps.executeUpdate();

        ResultSet keys = ps.getGeneratedKeys();
        keys.next();
        int id = keys.getInt(1);
        ps.close();
        return id;
    }

    private static int insertarMedicamentoDePrueba() throws SQLException {
        String sql = "INSERT INTO medicamento (nombre) VALUES (?)";
        PreparedStatement ps = ConexionBaseDatos.getInstancia().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, "MedicamentoPrueba");
        ps.executeUpdate();

        ResultSet keys = ps.getGeneratedKeys();
        keys.next();
        int id = keys.getInt(1);
        ps.close();
        return id;
    }

    private static int insertarDosisDePrueba(int tratamientoId, int medicamentoId) throws SQLException {
        String sql = "INSERT INTO dosis (id_tratamiento, id_medicamento, dias_duracion, cantidad, metodo_administracion, nota) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = ConexionBaseDatos.getInstancia().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, tratamientoId);
        ps.setInt(2, medicamentoId);
        ps.setInt(3, 7);
        ps.setString(4, "2 ml");
        ps.setString(5, "Oral");
        ps.setString(6, "Tomar con comida");
        ps.executeUpdate();

        ResultSet keys = ps.getGeneratedKeys();
        keys.next();
        int id = keys.getInt(1);
        ps.close();
        return id;
    }
}
