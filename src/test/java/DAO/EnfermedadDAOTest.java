package DAO;

import TransferObject.Paciente;
import TransferObject.Enfermedad;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class EnfermedadDAOTest {
    private static final EnfermedadDAO ENFERMEDAD_DAO = new EnfermedadDAO();
    private static final Enfermedad ENFERMEDAD_1 = new Enfermedad(1,"Gripa");
    private static final Enfermedad ENFERMEDAD_2 = new Enfermedad(2,"Dolor de cabeza");
    private static final Paciente PACIENTE_1 = new Paciente(1,"Emmanuel","Pale","masculino",java.sql.Date.valueOf("2024-07-04"),"correo@gmail.com","123");
    
    @BeforeClass
    public static void setUpClass() {
        String registroEnfermedadesSQL = "INSERT INTO enfermedad (nombre) VALUES (?), (?)";
        String registroPacienteSQL = "INSERT INTO paciente (nombre,apellido,genero,fecha_nacimiento,correo,contrasena) VALUES (?,?,?,?,?,?)";
        
        try {
            PreparedStatement registroEnfermedades = ConexionBaseDatos.getInstancia().prepareStatement(registroEnfermedadesSQL);
            registroEnfermedades.setString(1, ENFERMEDAD_1.getNombre());
            registroEnfermedades.setString(2, ENFERMEDAD_2.getNombre());
            registroEnfermedades.executeUpdate();
            registroEnfermedades.close();
            
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
        String borrarEnfermedadesSQL = "DELETE FROM enfermedad WHERE id > 0";
        String reiniciarIdEnfermedadSQL = "ALTER TABLE enfermedad AUTO_INCREMENT = 1";
        String borrarPacienteSQL = "DELETE FROM paciente WHERE id > 0";
        String reiniciarIdPacienteSQL = "ALTER TABLE paciente AUTO_INCREMENT = 1";
  
        try {
            PreparedStatement borrarEnfermedades = ConexionBaseDatos.getInstancia().prepareStatement(borrarEnfermedadesSQL);
            borrarEnfermedades.executeUpdate();
            borrarEnfermedades.close();
            
            PreparedStatement borrarPaciente = ConexionBaseDatos.getInstancia().prepareStatement(borrarPacienteSQL);
            borrarPaciente.executeUpdate();
            borrarPaciente.close();
            
            PreparedStatement reiniciarIdEnfermedad = ConexionBaseDatos.getInstancia().prepareStatement(reiniciarIdEnfermedadSQL);
            reiniciarIdEnfermedad.execute();
            reiniciarIdEnfermedad.close();
            
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
        String vincularPacienteEnfermedadSQL = "INSERT INTO antecedenteenfermedad (id_paciente, id_enfermedad) VALUES (?,?)"; 
        
        try {
            PreparedStatement vincularPacienteEnfermedad = ConexionBaseDatos.getInstancia().prepareStatement(vincularPacienteEnfermedadSQL);
            vincularPacienteEnfermedad.setInt(1,PACIENTE_1.getId());
            vincularPacienteEnfermedad.setInt(2,ENFERMEDAD_1.getId());
            vincularPacienteEnfermedad.executeUpdate();
            vincularPacienteEnfermedad.close();
            
            ConexionBaseDatos.desconectar();
        } catch (SQLException e) {
            System.out.println("Error en metodo Before\n" + e.getMessage());
        }
    }
    
    @After
    public void tearDown() {
        String borrarAntecedenteEnfermedadSQL = "DELETE FROM antecedenteenfermedad WHERE id_paciente > 0";
        
        try {
            PreparedStatement borrarAntecedenteEnfermedad = ConexionBaseDatos.getInstancia().prepareStatement(borrarAntecedenteEnfermedadSQL);
            borrarAntecedenteEnfermedad.execute();
            borrarAntecedenteEnfermedad.close();
            
            ConexionBaseDatos.desconectar();
        } catch (SQLException e) {
            System.out.println("Error en método After\n" + e.getMessage());
        }
    }

    @Test
    public void testObtenerEnfermedades() throws Exception {
        ArrayList<Enfermedad> esperado = new ArrayList<>();
        esperado.add(ENFERMEDAD_1);
        esperado.add(ENFERMEDAD_2);
        ArrayList<Enfermedad> resultado = ENFERMEDAD_DAO.obtenerEnfermedades();
        assertEquals(esperado,resultado);
    }

    @Test
    public void testObtenerEnfermedadesDelPaciente() throws Exception {
        ArrayList<Enfermedad> esperado = new ArrayList<>();
        esperado.add(ENFERMEDAD_1);
        ArrayList<Enfermedad> resultado = ENFERMEDAD_DAO.obtenerEnfermedadesDelPaciente(PACIENTE_1.getId());
        assertEquals(esperado,resultado);
    }

    @Test
    public void testAgregarEnfermedadAlAntecedente() throws Exception {
        int esperado = 1;
        int resultado = ENFERMEDAD_DAO.agregarEnfermedadAlAntecedente(PACIENTE_1.getId(), ENFERMEDAD_2.getId());
        assertEquals(esperado,resultado);
    }

    @Test
    public void testEliminarEnfermedadDeAntecedente() throws Exception {
        int esperado = 1;
        int resultado = ENFERMEDAD_DAO.eliminarEnfermedadDeAntecedente(PACIENTE_1.getId(), ENFERMEDAD_1.getId());
        assertEquals(esperado,resultado);
    }
}
