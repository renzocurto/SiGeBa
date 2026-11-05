
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

// Ventana principal de SiGeBa
public class PantallaPrincipal extends JFrame {

    private JTable tablaTramites;
    private DefaultTableModel modeloTabla;
    private JButton btnNuevoTramite;

    private final Color AZUL = new Color(37, 99, 166);
    private final Color FONDO = new Color(247, 249, 252);
    private final Color TEXTO = new Color(36, 54, 75);

    public PantallaPrincipal() {

        setTitle("SiGeBa - Sistema de Gestión de Bajas");
        setSize(1000, 650);
        setMinimumSize(new Dimension(800, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel principal = new JPanel(new BorderLayout());
        principal.setBackground(FONDO);

        // Encabezado
        JPanel encabezado = new JPanel(new BorderLayout());
        encabezado.setBackground(Color.WHITE);
        encabezado.setBorder(new EmptyBorder(20, 30, 20, 30));

        JLabel titulo = new JLabel("SiGeBa");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 26));
        titulo.setForeground(AZUL);

        JLabel subtitulo = new JLabel(
            "Sistema de Gestión de Bajas - Dirección de Informática"
        );
        subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 13));
        subtitulo.setForeground(TEXTO);

        JPanel textos = new JPanel(new GridLayout(2, 1, 0, 4));
        textos.setOpaque(false);
        textos.add(titulo);
        textos.add(subtitulo);

        encabezado.add(textos, BorderLayout.WEST);
        principal.add(encabezado, BorderLayout.NORTH);

        // Contenido principal
        JPanel contenido = new JPanel(new BorderLayout(0, 20));
        contenido.setBackground(Color.WHITE);
        contenido.setBorder(new EmptyBorder(25, 30, 25, 30));

        JLabel tituloListado = new JLabel("Trámites de baja");
        tituloListado.setFont(new Font("SansSerif", Font.BOLD, 21));
        tituloListado.setForeground(TEXTO);

        btnNuevoTramite = new JButton("Nuevo trámite");
        JButton btnActualizar = new JButton("Actualizar");

        JPanel botones = new JPanel(new FlowLayout(
            FlowLayout.RIGHT, 10, 0
        ));
        botones.setOpaque(false);
        botones.add(btnActualizar);
        botones.add(btnNuevoTramite);

        JPanel barraSuperior = new JPanel(new BorderLayout());
        barraSuperior.setOpaque(false);
        barraSuperior.add(tituloListado, BorderLayout.WEST);
        barraSuperior.add(botones, BorderLayout.EAST);

        contenido.add(barraSuperior, BorderLayout.NORTH);

        // Tabla de trámites
        String[] columnas = {
            "ID", "Agente", "Fecha de baja", "Motivo", "Estado"
        };

        // Impide que los registros puedan editarse directamente desde la tabla
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tablaTramites = new JTable(modeloTabla);
        tablaTramites.setRowHeight(32);
        tablaTramites.setFont(
            new Font("SansSerif", Font.PLAIN, 13)
        );
        tablaTramites.getTableHeader().setFont(
            new Font("SansSerif", Font.BOLD, 13)
        );
        tablaTramites.setSelectionMode(
            ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrollTabla = new JScrollPane(tablaTramites);
        scrollTabla.setBorder(
            BorderFactory.createLineBorder(
                new Color(226, 232, 240)
            )
        );

        contenido.add(scrollTabla, BorderLayout.CENTER);

        JPanel contenedorCentral =
            new JPanel(new BorderLayout());

        contenedorCentral.setBackground(FONDO);
        contenedorCentral.setBorder(
            new EmptyBorder(20, 25, 20, 25)
        );
        contenedorCentral.add(contenido, BorderLayout.CENTER);

        principal.add(contenedorCentral, BorderLayout.CENTER);
        setContentPane(principal);

        // Acciones
        btnActualizar.addActionListener(e -> cargarTramites());

        btnNuevoTramite.addActionListener(e -> {

            FormularioRegistroBaja formulario =
                new FormularioRegistroBaja(this);
        
            formulario.setVisible(true);
        });

        cargarTramites();
    }

    // Consulta los trámites de MySQL y actualiza las filas de la tabla
    public void cargarTramites() {

        modeloTabla.setRowCount(0);

        TramiteBajaDAO dao = new TramiteBajaDAO();

        try {
            List<TramiteBaja> tramites = dao.listarTodos();

            for (TramiteBaja tramite : tramites) {

                Object[] fila = {
                    tramite.getIdTramite(),
                    tramite.getAgente().getApellidoNombre(),
                    tramite.getFechaBaja(),
                    tramite.getMotivo(),
                    tramite.getEstadoTramite().getDescripcion()
                };

                modeloTabla.addRow(fila);
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                this,
                "No se pudieron cargar los trámites.\n"
                    + e.getMessage(),
                "Error de consulta",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
