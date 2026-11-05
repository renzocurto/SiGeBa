
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// Realiza las operaciones de consulta y registro de los trámites de baja en MySQL
public class TramiteBajaDAO {

    // Busca un trámite por su ID y recupera los datos del agente y su estado
    public TramiteBaja buscarPorId(int id) throws SQLException {

        String sql = """
            SELECT
                t.id_tramite,
                t.fecha_baja,
                t.motivo,
                t.acto_administrativo,
                t.observaciones,
                t.ticket_asociado,
                a.id_agente,
                a.apellido_nombre,
                a.ubicacion,
                a.usuario_gdeba,
                e.id_estado,
                e.descripcion AS estado
            FROM tramite_baja t
            INNER JOIN agente a
                ON t.id_agente = a.id_agente
            INNER JOIN estado_tramite e
                ON t.id_estado = e.id_estado
            WHERE t.id_tramite = ?
            """;

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia =
                     conexion.prepareStatement(sql)) {

            sentencia.setInt(1, id);

            try (ResultSet resultado = sentencia.executeQuery()) {

                if (resultado.next()) {

                    Agente agente = new Agente(
                        resultado.getInt("id_agente"),
                        resultado.getString("apellido_nombre"),
                        resultado.getString("ubicacion"),
                        resultado.getString("usuario_gdeba")
                    );

                    EstadoTramite estado = new EstadoTramite(
                        resultado.getInt("id_estado"),
                        resultado.getString("estado")
                    );

                    return new TramiteBaja(
                        resultado.getInt("id_tramite"),
                        resultado.getDate("fecha_baja").toLocalDate(),
                        resultado.getString("motivo"),
                        resultado.getString("acto_administrativo"),
                        resultado.getString("observaciones"),
                        resultado.getString("ticket_asociado"),
                        agente,
                        estado
                    );
                }
            }
        }

        return null;
    }

    // Registra un nuevo agente y su trámite
    public boolean insertar(TramiteBaja tramite) throws SQLException {

    String sqlAgente = """
        INSERT INTO agente (
            apellido_nombre,
            ubicacion,
            usuario_gdeba
        )
        VALUES (?, ?, ?)
        """;

    String sqlTramite = """
        INSERT INTO tramite_baja (
            id_agente,
            id_estado,
            fecha_baja,
            motivo,
            acto_administrativo,
            observaciones,
            ticket_asociado
        )
        VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

    try (Connection conexion = ConexionBD.obtenerConexion()) {

        // Los cambios se confirman únicamente si ambas inserciones funcionan 
        conexion.setAutoCommit(false);

        try {
            int idAgente;

            // Registrar el agente
            try (PreparedStatement sentenciaAgente =
                    conexion.prepareStatement(
                        sqlAgente,
                        Statement.RETURN_GENERATED_KEYS)) {

                sentenciaAgente.setString(1,
                    tramite.getAgente().getApellidoNombre());

                sentenciaAgente.setString(2,
                    tramite.getAgente().getUbicacion());

                sentenciaAgente.setString(3,
                    tramite.getAgente().getUsuarioGDEBA());

                sentenciaAgente.executeUpdate();

                try (ResultSet claves =
                        sentenciaAgente.getGeneratedKeys()) {

                    if (!claves.next()) {
                        throw new SQLException(
                            "No se pudo obtener el ID del agente."
                        );
                    }

                    idAgente = claves.getInt(1);
                }
            }

            // Registrar el trámite
            try (PreparedStatement sentenciaTramite =
                    conexion.prepareStatement(sqlTramite)) {

                sentenciaTramite.setInt(1, idAgente);
                sentenciaTramite.setInt(2,
                    tramite.getEstadoTramite().getIdEstado());

                sentenciaTramite.setDate(3,
                    java.sql.Date.valueOf(tramite.getFechaBaja()));

                sentenciaTramite.setString(4,
                    tramite.getMotivo());

                sentenciaTramite.setString(5,
                    tramite.getActoAdministrativo());

                sentenciaTramite.setString(6,
                    tramite.getObservaciones());

                sentenciaTramite.setString(7,
                    tramite.getTicketAsociado());

                int filas = sentenciaTramite.executeUpdate();

                if (filas != 1) {
                    throw new SQLException(
                        "No se pudo registrar el trámite."
                    );
                }
            }

            conexion.commit();
            return true;

            // Revertir los cambios si ocurre un error
        } catch (SQLException e) {
            try {
                conexion.rollback();
            } catch (SQLException rollbackError) {
                e.addSuppressed(rollbackError);
            }
            throw e;
        }
    }
}

    public List<TramiteBaja> listarTodos() throws SQLException {

        List<TramiteBaja> tramites = new ArrayList<>();

        String sql = """
            SELECT
                t.id_tramite,
                t.fecha_baja,
                t.motivo,
                t.acto_administrativo,
                t.observaciones,
                t.ticket_asociado,
                a.id_agente,
                a.apellido_nombre,
                a.ubicacion,
                a.usuario_gdeba,
                e.id_estado,
                e.descripcion AS estado
            FROM tramite_baja t
            INNER JOIN agente a
                ON t.id_agente = a.id_agente
            INNER JOIN estado_tramite e
                ON t.id_estado = e.id_estado
            ORDER BY t.id_tramite DESC
            """;

        try (Connection conexion = ConexionBD.obtenerConexion();
            PreparedStatement sentencia =
                    conexion.prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {

                Agente agente = new Agente(
                    resultado.getInt("id_agente"),
                    resultado.getString("apellido_nombre"),
                    resultado.getString("ubicacion"),
                    resultado.getString("usuario_gdeba")
                );

                EstadoTramite estado = new EstadoTramite(
                    resultado.getInt("id_estado"),
                    resultado.getString("estado")
                );

                TramiteBaja tramite = new TramiteBaja(
                    resultado.getInt("id_tramite"),
                    resultado.getDate("fecha_baja").toLocalDate(),
                    resultado.getString("motivo"),
                    resultado.getString("acto_administrativo"),
                    resultado.getString("observaciones"),
                    resultado.getString("ticket_asociado"),
                    agente,
                    estado
                );

                tramites.add(tramite);
            }
        }

    return tramites;
}


    
   
}


