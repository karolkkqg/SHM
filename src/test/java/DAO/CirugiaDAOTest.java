package DAO;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import TransferObject.Cirugia;
import TransferObject.Paciente;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class CirugiaDAOTest {
    private static final CirugiaDAO CIRUGIA_DAO = new CirugiaDAO(); 
    private static final Cirugia CIRUGIA_1 = new Cirugia(1,"Apendice", java.sql.Date.valueOf("2024-05-11"));
    private static final Cirugia CIRUGIA_2 = new Cirugia(2,"Pancreas", java.sql.Date.valueOf("2024-05-11"));
    private static final Paciente PACIENTE_1 = new Paciente(1,"Emmanuel","Pale","masculino",java.sql.Date.valueOf("2024-07-04"),"correo@gmail.com","123");
    
    @BeforeClass
    public static void setUpClass() {
        String registroCirugiasSQL = "INSERT INTO cirugia (nombre) VALUES (?), (?)";
        String registroPacienteSQL = "INSERT INTO paciente (nombre,apellido,genero,fecha_nacimiento,correo,contrasena) VALUES (?,?,?,?,?,?)";
        
        try {
            PreparedStatement registroCirugias = ConexionBaseDatos.getInstancia().prepareStatement(registroCirugiasSQL);
            registroCirugias.setString(1, CIRUGIA_1.getNombre());
            registroCirugias.setString(2, CIRUGIA_2.getNombre());
            registroCirugias.executeUpdate();
            registroCirugias.close();
            
            PreparedStatement registroPaciente = ConexionBaseDatos.getInstancia().prepareStatement(registroPacienteSQL);
            registroPaciente.setString(1, PACIENTE_1.getNombre());
            registroPaciente.setString(2, PACIENTE_1.getApellido());
            registroPaciente.setString(3, PACIENTE_1.getGenero());
            registroPaciente.setDate(4, (java.sql.Date)PACIENTE_1.getFechaNacimiento());
            registroPaciente.setString(5, PACIENTE_1.getCorreo());
            registroPaciente.setString(6, PACIENTE_1.getContrasena());
            registroPaciente.executeUpdate();
            registroPaciente.close();

            ConexionBaseDatos.desconectar();
        } catch (SQLException e) {
            System.out.println("Error en metodo BeforeClass\n" + e.getMessage());
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
        String borrarCirugiasSQL = "DELETE FROM cirugia WHERE id > 0";
        String reiniciarIdCirugiasSQL = "ALTER TABLE cirugia AUTO_INCREMENT = 1";
        String borrarPacienteSQL = "DELETE FROM paciente WHERE id > 0";
        String reiniciarIdPacienteSQL = "ALTER TABLE paciente AUTO_INCREMENT = 1";
  
        try {
            PreparedStatement borrarVacunas = ConexionBaseDatos.getInstancia().prepareStatement(borrarCirugiasSQL);
            borrarVacunas.executeUpdate();
            borrarVacunas.close();
            
            PreparedStatement borrarPaciente = ConexionBaseDatos.getInstancia().prepareStatement(borrarPacienteSQL);
            borrarPaciente.executeUpdate();
            borrarPaciente.close();
            
            PreparedStatement reiniciarIdVacuna = ConexionBaseDatos.getInstancia().prepareStatement(reiniciarIdCirugiasSQL);
            reiniciarIdVacuna.execute();
            reiniciarIdVacuna.close();
            
            PreparedStatement reiniciarIdPaciente = ConexionBaseDatos.getInstancia().prepareStatement(reiniciarIdPacienteSQL);
            reiniciarIdPaciente.execute();
            reiniciarIdPaciente.close();
            
            ConexionBaseDatos.desconectar();
        } catch (SQLException e) {
            System.out.println("Error en método AfterClass\n" + e.getMessage());
        }
    }
    
    @Before
    public void setUp() {
        String vincularPacienteCirugiaSQL = "INSERT INTO antecedentecirugia (id_paciente, id_cirugia, fecha_aplicacion) VALUES (?,?,?)"; 
        
        try {
            PreparedStatement vincularPacienteCirugia = ConexionBaseDatos.getInstancia().prepareStatement(vincularPacienteCirugiaSQL);
            vincularPacienteCirugia.setInt(1,PACIENTE_1.getId());
            vincularPacienteCirugia.setInt(2,CIRUGIA_1.getId());
            vincularPacienteCirugia.setDate(3, null);
            vincularPacienteCirugia.executeUpdate();
            vincularPacienteCirugia.close();
            
            ConexionBaseDatos.desconectar();
        } catch (SQLException e) {
            System.out.println("Error en metodo Before\n" + e.getMessage());
        }
    }
    
    @After
    public void tearDown() {
        String borrarAntecedenteCirugiaSQL = "DELETE FROM antecedentecirugia WHERE id_paciente > 0";
        
        try {
            PreparedStatement borrarAntecedenteCirugia = ConexionBaseDatos.getInstancia().prepareStatement(borrarAntecedenteCirugiaSQL);
            borrarAntecedenteCirugia.execute();
            borrarAntecedenteCirugia.close();
            
            ConexionBaseDatos.desconectar();
        } catch (SQLException e) {
            System.out.println("Error en método After\n" + e.getMessage());
        }
    }

    @Test
    public void testObtenerCirugias() throws Exception {
        ArrayList<Cirugia> esperado = new ArrayList<>();
        esperado.add(CIRUGIA_1);
        esperado.add(CIRUGIA_2);
        ArrayList<Cirugia> resultado = CIRUGIA_DAO.obtenerCirugias();
        assertEquals(resultado, esperado);
    }

    @Test
    public void testObtenerCirugiasDelPaciente() throws Exception {
        ArrayList<Cirugia> esperado = new ArrayList<>();
        esperado.add(CIRUGIA_1);
        ArrayList<Cirugia> resultado = CIRUGIA_DAO.obtenerCirugiasDelPaciente(PACIENTE_1.getId());
        assertEquals(esperado,resultado);
    }

    @Test
    public void testAgregarCirugiaAlAntecedenteFechaNula() throws Exception {
        int esperado = 1;
        int resultado = CIRUGIA_DAO.agregarCirugiaAlAntecedente(PACIENTE_1.getId(), CIRUGIA_2.getId(), null);
        assertEquals(esperado, resultado);
    }
    
    @Test
    public void testAgregarCirugiaAlAntecedente() throws Exception {
        int esperado = 1;
        int resultado = CIRUGIA_DAO.agregarCirugiaAlAntecedente(PACIENTE_1.getId(), CIRUGIA_2.getId(), java.sql.Date.valueOf("2024-11-05"));
        assertEquals(esperado, resultado);
    }

    @Test
    public void testEliminarCirugiaDeAntecedente() throws Exception {
        int esperado = 1;
        int resultado = CIRUGIA_DAO.eliminarCirugiaDeAntecedente(PACIENTE_1.getId(), CIRUGIA_1.getId());
        assertEquals(esperado,resultado);
    }
}
