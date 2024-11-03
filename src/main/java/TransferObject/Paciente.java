package TransferObject;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author luis_cvqz
 */
public class Paciente {
    private int id;
    private String nombre;
    private String apellido;
    private String genero;
    private Date fechaNacimiento;
    private String correo;
    private String contrasena;

    public Paciente () {}

    public Paciente(String nombre, String apellido, String genero, Date fechaNacimiento, String correo, String contrasena) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.correo = correo;
        this.contrasena = contrasena;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paciente paciente = (Paciente) o;
        return id == paciente.id
                && Objects.equals(nombre, paciente.nombre)
                && Objects.equals(apellido, paciente.apellido)
                && Objects.equals(genero, paciente.genero)
                && Objects.equals(fechaNacimiento, paciente.fechaNacimiento)
                && Objects.equals(correo, paciente.correo)
                && Objects.equals(contrasena, paciente.contrasena);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellido, genero, fechaNacimiento, correo, contrasena);
    }
}
