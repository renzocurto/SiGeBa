
import java.sql.SQLException;
// Contiene a lógica de validación y registro de los trámites de baja
public class RegistroBajaService {

    private TramiteBajaDAO tramiteBajaDAO;
    
    // Constructor que inicializa el acceso a datos
    public RegistroBajaService() {
        this.tramiteBajaDAO = new TramiteBajaDAO();
    }

    // Verifica los datos obligatorios y sus longitudes
    public boolean validarDatos(Agente agente, TramiteBaja tramite) {

        if (agente == null || tramite == null) {
            return false;
        }

        if (agente.getApellidoNombre() == null ||
            agente.getApellidoNombre().isBlank() ||
            agente.getApellidoNombre().length() > 150) {
            return false;
        }

        if (tramite.getFechaBaja() == null) {
            return false;
        }

        if (tramite.getMotivo() == null ||
            tramite.getMotivo().isBlank() ||
            tramite.getMotivo().length() > 200) {
            return false;
        }

        if (tramite.getEstadoTramite() == null ||
            tramite.getEstadoTramite().getIdEstado() <= 0) {
            return false;
        }

        

        return true;
    }

    // Registra el trámite solamente si los datos son validos
    public boolean registrarTramite(Agente agente, TramiteBaja tramite)
            throws SQLException {

        if (!validarDatos(agente, tramite)) {
            return false;
        }

        return tramiteBajaDAO.insertar(tramite);
    }
}
