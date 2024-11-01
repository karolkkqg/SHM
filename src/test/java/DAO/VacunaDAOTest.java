package DAO;

import TransferObject.Vacuna;
import TransferObject.Paciente;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VacunaDAOTest {
    private static final VacunaDAO VACUNA_DAO = new VacunaDAO();
    private static final Vacuna VACUNA_1 = new Vacuna("covid-19");
    private static final Vacuna VACUNA_2 = new Vacuna("influenza");
    public static final Paciente PACIENTE_1 = new Paciente("Emmanuel","Pale","masculino",java.sql.Date.valueOf("2024-07-04"),"correo@gmail.com","123");
    
    @BeforeClass
    public static void setUp() throws SQLException {
        String registroVacunasSQL = "INSERT INTO vacuna (nombre) VALUES (?), (?)";
        String registroPacienteSQL = "INSERT INTO paciente (nombre,apellido,genero,fecha_nacimiento,correo,contraseña) VALUES (?,?,?,?,?,?)";
        
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
            registroPaciente.close();
            
            ConexionBaseDatos.desconectar();
        } catch (SQLException e) {
            System.out.println("Error en metodo before" + e.getMessage());
        }
    }
    
    @AfterClass
    public static void tearDown () {
        String borrarVacunaSQL = "DELETE FROM vacuna WHERE nombre = ? AND nombre = ";
        String borrarPacienteSQL = "DELETE FROM vacuna WHERE nombre = ?";
        
        try {
            PreparedStatement borrarVacunas = ConexionBaseDatos.getInstancia().prepareStatement(borrarVacunaSQL);
            borrarVacunas.setString(1, VACUNA_1.getNombre());
            borrarVacunas.setString(2, VACUNA_2.getNombre());
            borrarVacunas.executeUpdate();
            borrarVacunas.close();
            
            PreparedStatement borrarPaciente = ConexionBaseDatos.getInstancia().prepareStatement(borrarPacienteSQL);
            borrarPaciente.setString(1, PACIENTE_1.getNombre());
            borrarPaciente.executeUpdate();
            borrarPaciente.close();
            
            ConexionBaseDatos.desconectar();
        } catch (SQLException e) {
            System.out.println("Error en método AfterClass\n" + e.getMessage());
        }
    }

    @Test
    public void testObtenerVacunas() throws Exception {
        ArrayList<Vacuna> esperado = new ArrayList<Vacuna>();
        esperado.add(VACUNA_1);
        esperado.add(VACUNA_2);
        ArrayList<Vacuna> resultado = VACUNA_DAO.obtenerVacunas();
        assertEquals(resultado, esperado);
    }

    @Test
    public void testObtenerVacunasDelPaciente() throws Exception {
        
    }

    @Test
    public void testAgregarVacunaAlAntecedente() throws Exception {
        
    }

    @Test
    public void testEliminarVacunaDeAntecedente() throws Exception {
        
    }
    
}
