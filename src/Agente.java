// Representa un agente del organismo sobre el cual se puede registrar un tramite de baja

public class Agente {

    private int idAgente;
    private String apellidoNombre;
    private String ubicacion;
    private String usuarioGDEBA;

    // Constructor para inicializar los datos del agente
    public Agente(int idAgente, String apellidoNombre,
                  String ubicacion, String usuarioGDEBA) {
        this.idAgente = idAgente;
        this.apellidoNombre = apellidoNombre;
        this.ubicacion = ubicacion;
        this.usuarioGDEBA = usuarioGDEBA;
    }

    public int getIdAgente() {
        return idAgente;
    }

    public String getApellidoNombre() {
        return apellidoNombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getUsuarioGDEBA() {
        return usuarioGDEBA;
    }
}
