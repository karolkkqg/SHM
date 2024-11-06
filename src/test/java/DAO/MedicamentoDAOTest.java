package DAO;

import TransferObject.Medicamento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MedicamentoDAOTest {

    private static final MedicamentoDAO MEDICAMENTO_DAO = new MedicamentoDAO();
    private static final Medicamento MEDICAMENTO_1 = new Medicamento("Ibuprofeno");
    private static final Medicamento MEDICAMENTO_2 = new Medicamento("Paracetamol");
    private static int PACIENTE_ID;

    @BeforeClass
    public static void setUp() throws SQLException {
        limpiarTablaAntecedenteMedicamento();
        limpiarTablaMedicamento();
        limpiarTablaPaciente();

        PACIENTE_ID = insertarPacienteDePrueba();
        insertarMedicamentosDePrueba();
    }

    @AfterClass
    public static void tearDown() throws SQLException {
        limpiarTablaAntecedenteMedicamento();
        limpiarTablaMedicamento();
        limpiarTablaPaciente();
    }

    @Test
    public void testObtenerMedicamento() throws Exception {
        ArrayList<Medicamento> esperado = new ArrayList<>();
        esperado.add(MEDICAMENTO_1);
        esperado.add(MEDICAMENTO_2);

        ArrayList<Medicamento> resultado = MEDICAMENTO_DAO.obtenerMedicamento();

        assertEquals(esperado, resultado);
    }

    @Test
    public void testObtenerMedicamentosDelPaciente() throws Exception {
        ArrayList<Medicamento> medicamentosDelPaciente = MEDICAMENTO_DAO.obtenerMedicamentosDelPaciente(PACIENTE_ID);
        assertNotNull(medicamentosDelPaciente);
    }

    @Test
    public void testAgregarMedicamentoAlAntecedente() throws Exception {
        int resultado = MEDICAMENTO_DAO.agregarMedicamentoAlAntecedente(PACIENTE_ID, MEDICAMENTO_1.getId());
        assertEquals(1, resultado);

        int verificacion = MEDICAMENTO_DAO.verificarRegistroEnAntecedente(PACIENTE_ID, MEDICAMENTO_1.getId());
        assertEquals(1, verificacion);
    }

    @Test
    public void testEliminarMedicamentoDeAntecedente() throws Exception {
        MEDICAMENTO_DAO.agregarMedicamentoAlAntecedente(PACIENTE_ID, MEDICAMENTO_2.getId());

        int resultado = MEDICAMENTO_DAO.eliminarVacunaDeAntecedente(PACIENTE_ID, MEDICAMENTO_2.getId());
        assertEquals(1, resultado);

        int verificacion = MEDICAMENTO_DAO.verificarRegistroEnAntecedente(PACIENTE_ID, MEDICAMENTO_2.getId());
        assertEquals(0, verificacion);
    }

    private static void limpiarTablaAntecedenteMedicamento() throws SQLException {
        String sql = "DELETE FROM antecedentemedicamento";
        ejecutarActualizacion(sql);
    }

    private static void limpiarTablaMedicamento() throws SQLException {
        String sql = "DELETE FROM medicamento";
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

    private static void insertarMedicamentosDePrueba() throws SQLException {
        String sql = "INSERT INTO medicamento (nombre) VALUES (?), (?)";
        PreparedStatement ps = ConexionBaseDatos.getInstancia().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, MEDICAMENTO_1.getNombre());
        ps.setString(2, MEDICAMENTO_2.getNombre());
        ps.executeUpdate();

        ResultSet keys = ps.getGeneratedKeys();
        if (keys.next()) {
            MEDICAMENTO_1.setId(keys.getInt(1));
        }
        if (keys.next()) {
            MEDICAMENTO_2.setId(keys.getInt(1));
        }
        ps.close();
    }

    private static void ejecutarActualizacion(String sql) throws SQLException {
        PreparedStatement ps = ConexionBaseDatos.getInstancia().prepareStatement(sql);
        ps.executeUpdate();
        ps.close();
    }
}
