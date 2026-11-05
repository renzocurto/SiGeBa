import javax.swing.SwingUtilities;

public class App {

    public static void main(String[] args) {

        //Inicio de la interfaz gráfica de SiGeBa
        SwingUtilities.invokeLater(() -> {

            PantallaPrincipal pantalla =
                new PantallaPrincipal();

            pantalla.setVisible(true);

        });
    }
}