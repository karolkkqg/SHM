package DAO;

import TransferObject.Vacuna;
import TransferObject.Paciente;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class VacunaDAOTest {
    private static final VacunaDAO VACUNA_DAO = new VacunaDAO();
    private static final Vacuna VACUNA_1 = new Vacuna("covid-19");
    private static final Vacuna VACUNA_2 = new Vacuna("influenza");
    private static final Paciente PACIENTE_1 = new Paciente(1,"Emmanuel","Pale","masculino",java.sql.Date.valueOf("2024-07-04"),"correo@gmail.com","123");
    
    @BeforeClass
    public static void setUp() {
        String registroVacunasSQL = "INSERT INTO vacuna (nombre) VALUES (?), (?)";
        String registroPacienteSQL = "INSERT INTO paciente (nombre,apellido,genero,fecha_nacimiento,correo,contrasena) VALUES (?,?,?,?,?,?)";
        
        try {
            PreparedStatement registroVacuna = ConexionBaseDatos.getInstancia().prepareStatement(registroVacunasSQL);
            registroVacuna.setString(1, VACUNA_1.getNombre());
            registroVacuna.setString(2, VACUNA_2.getNombre());
            registroVacuna.executeUpdate();
            registroVacuna.close();
            
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
    
    @Before
    public void before () {
        String vincularPacienteVacunaSQL = "INSERT INTO antecedentevacuna (id_paciente, id_vacuna, fecha_aplicacion) VALUES (?,?,?)"; 
        
        try {
            PreparedStatement vincularPacienteVacuna = ConexionBaseDatos.getInstancia().prepareStatement(vincularPacienteVacunaSQL);
            vincularPacienteVacuna.setInt(1,1);
            vincularPacienteVacuna.setInt(2,1);
            vincularPacienteVacuna.setDate(3, null);
            vincularPacienteVacuna.executeUpdate();
            vincularPacienteVacuna.close();
            
            ConexionBaseDatos.desconectar();
        } catch (SQLException e) {
            System.out.println("Error en metodo Before\n" + e.getMessage());
        }
    }
    
    @After
    public void after () {
        String borrarAntecedenteVacunaSQL = "DELETE FROM antecedentevacuna WHERE id_paciente > 0";
        
        try {
            PreparedStatement borrarAntecedenteVacuna = ConexionBaseDatos.getInstancia().prepareStatement(borrarAntecedenteVacunaSQL);
            borrarAntecedenteVacuna.execute();
            borrarAntecedenteVacuna.close();
            
            ConexionBaseDatos.desconectar();
        } catch (SQLException e) {
            System.out.println("Error en método After\n" + e.getMessage());
        }
    }
    
    @AfterClass
    public static void tearDown () {
        String borrarVacunaSQL = "DELETE FROM vacuna WHERE id > 0";
        String reiniciarIdVacunaSQL = "ALTER TABLE vacuna AUTO_INCREMENT = 1";
        String borrarPacienteSQL = "DELETE FROM paciente WHERE id > 0";
        String reiniciarIdPacienteSQL = "ALTER TABLE paciente AUTO_INCREMENT = 1";
  
        try {
            PreparedStatement borrarVacunas = ConexionBaseDatos.getInstancia().prepareStatement(borrarVacunaSQL);
            borrarVacunas.executeUpdate();
            borrarVacunas.close();
            
            PreparedStatement borrarPaciente = ConexionBaseDatos.getInstancia().prepareStatement(borrarPacienteSQL);
            borrarPaciente.executeUpdate();
            borrarPaciente.close();
            
            PreparedStatement reiniciarIdVacuna = ConexionBaseDatos.getInstancia().prepareStatement(reiniciarIdVacunaSQL);
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

    @Test
    public void testObtenerVacunas() throws Exception {
        ArrayList<Vacuna> esperado = new ArrayList<>();
        esperado.add(VACUNA_1);
        esperado.add(VACUNA_2);
        ArrayList<Vacuna> resultado = VACUNA_DAO.obtenerVacunas();
        assertEquals(resultado, esperado);
    }

    @Test
    public void testObtenerVacunasDelPaciente() throws Exception {
        ArrayList<Vacuna> esperado = new ArrayList<>();
        esperado.add(VACUNA_1);
        ArrayList<Vacuna> resultado = VACUNA_DAO.obtenerVacunasDelPaciente(1);
        assertEquals(esperado,resultado);
    }

    @Test
    public void testAgregarVacunaAlAntecedenteFechaNula() throws Exception {
        int esperado = 1;
        int resultado = VACUNA_DAO.agregarVacunaAlAntecedente(1, 2, null);
        assertEquals(esperado, resultado);
    }
    
    @Test
    public void testAgregarVacunaAlAntecedente () throws Exception {
        int esperado = 1;
        int resultado = VACUNA_DAO.agregarVacunaAlAntecedente(1, 2, java.sql.Date.valueOf("2024-11-05"));
        assertEquals(esperado, resultado);
    }

    @Test
    public void testEliminarVacunaDeAntecedente() throws Exception {
        int esperado = 1;
        int resultado = VACUNA_DAO.eliminarVacunaDeAntecedente(1, 1);
        assertEquals(esperado,resultado);
    }
    
}
