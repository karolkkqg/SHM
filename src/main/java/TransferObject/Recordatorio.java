
package TransferObject;

import java.util.Objects;
import java.time.LocalTime;

/**
 *
 * @author kahun
 */
public class Recordatorio {
    
    private int id;
    private LocalTime hora;

    public void setId(int id) {
        this.id = id;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public int getId() {
        return id;
    }

    public LocalTime getHora() {
        return hora;
    }
    
    @Override
    public boolean equals(Object obj) {
        boolean esIgual;
        
        if (obj == null || getClass() != obj.getClass()) {
            esIgual = false;
        }
        else {
            final Recordatorio other = (Recordatorio) obj;
            esIgual = this.id == other.id && this.hora.equals(other.hora);
        }

        return esIgual;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id;
        hash = 59 * hash + Objects.hashCode(this.hora);
        return hash;
    }
}
