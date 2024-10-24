package TransferObject;

import java.util.Objects;

/**
 *
 * @author epale
 */
public class Enfermedad {
    private int id;
    private String nombre;

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
    
    @Override
    public boolean equals(Object obj) {
        boolean esIgual;
        
        if (obj == null || getClass() != obj.getClass()) {
            esIgual = false;
        }
        else {
            final Enfermedad other = (Enfermedad) obj;
            esIgual = this.id == other.id && this.nombre.equals(other.nombre);
        }

        return esIgual;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id;
        hash = 59 * hash + Objects.hashCode(this.nombre);
        return hash;
    }
}
