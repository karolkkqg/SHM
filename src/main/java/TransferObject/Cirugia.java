package TransferObject;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author epale
 */
public class Cirugia {
    private int id;
    private String nombre;
    private Date fechaAplicacion;

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaAplicacion(Date fechaAplicacion) {
        this.fechaAplicacion = fechaAplicacion;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Date getFechaAplicacion() {
        return fechaAplicacion;
    }
    
    @Override
    public boolean equals(Object obj) {
        boolean esIgual;
        
        if (obj == null || getClass() != obj.getClass()) {
            esIgual = false;
        }
        else {
            final Cirugia other = (Cirugia) obj;
            esIgual = this.id == other.id && this.nombre.equals(other.nombre) && this.fechaAplicacion == other.fechaAplicacion;
        }

        return esIgual;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id;
        hash = 59 * hash + Objects.hashCode(this.nombre);
        hash = 59 * hash + Objects.hashCode(this.fechaAplicacion);
        return hash;
    }
}
