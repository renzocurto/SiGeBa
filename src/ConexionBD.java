
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Administra la conexión de SiGeBa con la base de datos MySQL
public class ConexionBD {

        // Datos necesarios para conectarse a la base de datos
    private static final String URL =
            "jdbc:mysql://localhost:3306/sigeba";

    private static final String USUARIO = "root";

    // La contraseña es definida como una variable del entorno
    private static final String PASSWORD =
            System.getenv("SIGEBA_DB_PASSWORD");

        // Devuelve una conexión JDBC con MySQL
    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(
                URL, USUARIO, PASSWORD
        );
    }
}
