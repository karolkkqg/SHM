package TransferObject;

import java.util.Objects;

/**
 *
 * @author luis_
 */
public class Dosis {
    private int id;
    private int idTratamiento;
    private int idMedicamento;
    private int diasDuracion;
    private String cantidad;
    private String metodoAdministracion;
    private String nota;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTratamiento() {
        return idTratamiento;
    }

    public void setIdTratamiento(int idTratamiento) {
        this.idTratamiento = idTratamiento;
    }

    public int getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public int getDiasDuracion() {
        return diasDuracion;
    }

    public void setDiasDuracion(int diasDuracion) {
        this.diasDuracion = diasDuracion;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getMetodoAdministracion() {
        return metodoAdministracion;
    }

    public void setMetodoAdministracion(String metodoAdministracion) {
        this.metodoAdministracion = metodoAdministracion;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dosis dosis = (Dosis) o;
        return id == dosis.id
                && idTratamiento == dosis.idTratamiento
                && idMedicamento == dosis.idMedicamento
                && diasDuracion == dosis.diasDuracion
                && Objects.equals(cantidad, dosis.cantidad)
                && Objects.equals(metodoAdministracion, dosis.metodoAdministracion)
                && Objects.equals(nota, dosis.nota);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idTratamiento, idMedicamento, diasDuracion, cantidad, metodoAdministracion, nota);
    }
}
