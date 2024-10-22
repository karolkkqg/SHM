package DAO;

import java.sql.Connection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author epale
 */
public class ConexionBaseDatosTest {
    
    public ConexionBaseDatosTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getInstanciaTest() throws Exception {
        System.out.println("getInstanciaTest");
        Connection result = ConexionBaseDatos.getInstancia();
        assertNotNull("getInstanciaTest", result);
    }

    @Test
    public void desconectarTest() throws Exception {
        boolean result = ConexionBaseDatos.desconectar();
        assertTrue("desconectarTest", result);
    }
    
}
