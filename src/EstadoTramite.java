// Representa el estado de un trámite de baja. Puede ser Iniciado, En Proceso, En espera o Finalizado.
public class EstadoTramite {

    private int idEstado;
    private String descripcion;

    // Constructor para inicializar el estado del trámite
    public EstadoTramite(int idEstado, String descripcion) {
        this.idEstado = idEstado;
        this.descripcion = descripcion;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public String getDescripcion() {
        return descripcion;
    }
}

