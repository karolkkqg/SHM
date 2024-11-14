package TransferObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author luis_cvqz
 */
public class Tratamiento {
    private int id;
    private int idPaciente;
    private Date fechaInicio;
     private List<Dosis> dosisList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    
    public List<Dosis> getDosisList() {
        return dosisList;
    }

    public void agregarDosis(Dosis dosis) {
        dosisList.add(dosis);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tratamiento that = (Tratamiento) o;
        return id == that.id && idPaciente == that.idPaciente && Objects.equals(fechaInicio, that.fechaInicio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idPaciente, fechaInicio);
    }
}
