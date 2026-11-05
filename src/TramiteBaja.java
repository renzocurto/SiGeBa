// Representa un tramite de baja, con sus datos y las referencias al agente y al estado correspondiente.
import java.time.LocalDate;

public class TramiteBaja {

    private int idTramite;
    private LocalDate fechaBaja;
    private String motivo;
    private String actoAdministrativo;
    private String observaciones;
    private String ticketAsociado;

    // Relaciones del trámite con el agente y su estado
    private Agente agente;
    private EstadoTramite estadoTramite;

    // Constructor para inicializar los datos del agente
    public TramiteBaja(
            int idTramite,
            LocalDate fechaBaja,
            String motivo,
            String actoAdministrativo,
            String observaciones,
            String ticketAsociado,
            Agente agente,
            EstadoTramite estadoTramite) {

        this.idTramite = idTramite;
        this.fechaBaja = fechaBaja;
        this.motivo = motivo;
        this.actoAdministrativo = actoAdministrativo;
        this.observaciones = observaciones;
        this.ticketAsociado = ticketAsociado;
        this.agente = agente;
        this.estadoTramite = estadoTramite;
    }

    public int getIdTramite() {
        return idTramite;
    }

    public LocalDate getFechaBaja() {
        return fechaBaja;
    }

    public String getMotivo() {
        return motivo;
    }

    public String getActoAdministrativo() {
        return actoAdministrativo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public String getTicketAsociado() {
        return ticketAsociado;
    }

    public Agente getAgente() {
        return agente;
    }

    public EstadoTramite getEstadoTramite() {
        return estadoTramite;
    }
}
