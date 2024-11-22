package BussinesLogic;

/**
 *
 * @author luis_
 */

public class SessionDetails {
    private static SessionDetails instance;
    private final int id;
    private final String nombre;
    private final String apellido;
    private final String correo;

    public static SessionDetails getInstance(String nombre, String apellido, String correo, int id) {
        if (instance == null) {
            instance = new SessionDetails(nombre, apellido, correo, id);
        }
        return instance;
    }

    public static SessionDetails getInstance() {
        return instance;
    }

    private SessionDetails(String nombre, String apellido, String correo, int id) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.id = id;
    }

    public static void cleanSessionDetails() {
        instance = null;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public int getId() {
        return id;
    }
}
