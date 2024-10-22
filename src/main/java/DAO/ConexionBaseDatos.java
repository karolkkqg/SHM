package DAO;

import java.io.*;
import java.sql.*;
import java.util.Properties;
import java.sql.SQLException;
/**
 *
 * @author epale
 */
public class ConexionBaseDatos {
    private static Connection conexion;
    private static final String URL_DB_PROPERTY = "db.url";
    private static final String USUARIO_DB_PROPERTY = "db.usuario";
    private static final String CLAVE_DB_PROPERTY = "db.clave";

    /**
     * Constructor privado para evitar la creación de instancias de la clase.
     * La clase utiliza el patrón Singleton para asegurar una única conexión.
     */
    private ConexionBaseDatos () {

    }

    /**
     * Obtiene la instancia única de la conexión a la base de datos.
     * Si la conexión no existe o está cerrada, se establece una nueva conexión.
     *
     * @return conexion regresa una conexión a la base de datos
     * @throws SQLException si ocurre una falla al establecer conexión
     */
    public static Connection getInstancia () throws SQLException {
        if (conexion == null || conexion.isClosed()) {
                conexion = getConexion();
        }
        return conexion;
    }
    
        /**
     * Desconecta la conexión a la base de datos si está abierta.
     *
     * @return true si la conexión se cerró exitosamente, false en caso contrario
     * @throws SQLException si ocurres una falla mientras se cierra la conexión
     */
    public static boolean desconectar () throws SQLException {
        if (conexion != null) {
                conexion.close();
        }
        return true;
    }

    /**
     * Establece una nueva conexión a la base de datos utilizando las propiedades configuradas.
     *
     * @return una nueva conexión a la base de datos
     * @throws SQLException si ocurre un error al intentar conectarse
     */
    private static Connection getConexion () throws SQLException {
        Connection nuevaConexion;
        Properties propiedades = new ConexionBaseDatos().getConfiguracionDB();
        if (propiedades != null) {
            nuevaConexion = DriverManager.getConnection(
                    propiedades.getProperty(URL_DB_PROPERTY),
                    propiedades.getProperty(USUARIO_DB_PROPERTY),
                    propiedades.getProperty(CLAVE_DB_PROPERTY)
            );
        }
        else {
            throw new SQLException("No es posible encontrar las credenciales de la base de datos");
        }
        return nuevaConexion;
    }

    /**
     * Obtiene la configuración de la base de datos desde un archivo de propiedades.
     *
     * @return un objeto Properties con la configuración de la base de datos, o null si ocurre un error
     */
    private Properties getConfiguracionDB () {
        Properties configuracion = null;
        try (InputStream archivoConfiguracion = new FileInputStream("src/main/java/DAO/ConfiguracionBaseDatos.properties")) {
            if (archivoConfiguracion != null) {
                configuracion = new Properties();
                configuracion.load(archivoConfiguracion);
            }
        }
        catch (FileNotFoundException error) {
            System.out.println("No es posible encontrar el archivo");
            //TODO: Implementar logger?
        }
        catch (IOException error) {
            //TODO: Implementar logger?
        }
        return configuracion;
    }
}
