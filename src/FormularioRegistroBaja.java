
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.sql.SQLException;

// Ventana para ingresar los datos del agente y registrar un nuevo tramite de baja.
public class FormularioRegistroBaja extends JFrame {

    private JTextField txtApellidoNombre;
    private JTextField txtUbicacion;
    private JTextField txtUsuarioGDEBA;
    private JTextField txtFechaBaja;
    private JTextField txtMotivo;
    private JTextField txtActoAdministrativo;
    private JTextField txtTicketAsociado;
    private JTextArea txtObservaciones;
    private PantallaPrincipal pantallaPrincipal;

    private final Color AZUL = new Color(37, 99, 166);
    private final Color FONDO = new Color(247, 249, 252);
    private final Color TEXTO = new Color(36, 54, 75);

    // Construye el formulario
    public FormularioRegistroBaja(PantallaPrincipal pantallaPrincipal) {

        this.pantallaPrincipal = pantallaPrincipal;

        setTitle("SiGeBa - Sistema de Gestión de Bajas");
        setSize(780, 750);
        setMinimumSize(new Dimension(650, 650));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

        // Contenedor del formulario
        JPanel formulario = new JPanel(new GridBagLayout());
        formulario.setBackground(Color.WHITE);
        formulario.setBorder(new EmptyBorder(25, 30, 25, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.weightx = 1.0;

        JLabel encabezadoFormulario =
            new JLabel("Nuevo trámite de baja");

        encabezadoFormulario.setFont(
            new Font("SansSerif", Font.BOLD, 21)
        );
        encabezadoFormulario.setForeground(TEXTO);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formulario.add(encabezadoFormulario, gbc);

        gbc.gridwidth = 1;

        // Datos del agente
        txtApellidoNombre = new JTextField();
        txtUbicacion = new JTextField();
        txtUsuarioGDEBA = new JTextField();

        agregarCampo(formulario, gbc,
            "Apellido y nombre *", txtApellidoNombre, 1, 0);

        agregarCampo(formulario, gbc,
            "Ubicación", txtUbicacion, 2, 0);

        agregarCampo(formulario, gbc,
            "Usuario GDEBA", txtUsuarioGDEBA, 2, 1);

        // Datos del trámite
        txtFechaBaja = new JTextField("DD/MM/AAAA");
        txtMotivo = new JTextField();
        txtActoAdministrativo = new JTextField();
        txtTicketAsociado = new JTextField();

        agregarCampo(formulario, gbc,
            "Fecha de baja *", txtFechaBaja, 3, 0);

        agregarCampo(formulario, gbc,
            "Motivo *", txtMotivo, 3, 1);

        agregarCampo(formulario, gbc,
            "Acto administrativo", txtActoAdministrativo, 4, 0);

        agregarCampo(formulario, gbc,
            "Ticket asociado", txtTicketAsociado, 4, 1);

        // Observaciones
        JLabel lblObservaciones = new JLabel("Observaciones");
        lblObservaciones.setFont(
            new Font("SansSerif", Font.PLAIN, 13)
        );

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;

        formulario.add(lblObservaciones, gbc);

        txtObservaciones = new JTextArea(4, 20);
        txtObservaciones.setLineWrap(true);
        txtObservaciones.setWrapStyleWord(true);
        txtObservaciones.setFont(
            new Font("SansSerif", Font.PLAIN, 14)
        );

        JScrollPane scrollObservaciones =
            new JScrollPane(txtObservaciones);

        gbc.gridy = 6;
        formulario.add(scrollObservaciones, gbc);

        // Botones
        JPanel botones = new JPanel(new FlowLayout(
            FlowLayout.RIGHT, 12, 0
        ));
        botones.setOpaque(false);

        JButton btnCancelar = new JButton("Cancelar");
        JButton btnGuardar = new JButton("Guardar trámite");

        btnGuardar.setBackground(AZUL);
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setOpaque(true);
        btnGuardar.setContentAreaFilled(true);
        btnGuardar.setBorderPainted(false);
        btnGuardar.setFocusPainted(false);
        btnGuardar.setPreferredSize(new Dimension(160, 36));

        btnCancelar.addActionListener(e -> dispose());

        btnGuardar.addActionListener(e -> guardarTramite());

        botones.add(btnCancelar);
        botones.add(btnGuardar);

        gbc.gridy = 7;
        gbc.insets = new Insets(20, 8, 0, 8);
        formulario.add(botones, gbc);

        JPanel contenedorFormulario = new JPanel(new BorderLayout());
        contenedorFormulario.setBackground(Color.WHITE);
        contenedorFormulario.add(formulario, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(contenedorFormulario);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        JPanel centro = new JPanel(new BorderLayout());
        centro.setBackground(FONDO);
        centro.setBorder(new EmptyBorder(20, 25, 20, 25));
        centro.add(scroll, BorderLayout.CENTER);

        principal.add(centro, BorderLayout.CENTER);

        setContentPane(principal);
    }

    private void agregarCampo(
            JPanel panel,
            GridBagConstraints gbc,
            String etiqueta,
            JTextField campo,
            int fila,
            int columna) {

        JPanel contenedor = new JPanel(new BorderLayout(0, 6));
        contenedor.setOpaque(false);

        JLabel label = new JLabel(etiqueta);
        label.setFont(new Font("SansSerif", Font.PLAIN, 13));
        label.setForeground(TEXTO);

        campo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        campo.setPreferredSize(new Dimension(200, 35));

        contenedor.add(label, BorderLayout.NORTH);
        contenedor.add(campo, BorderLayout.CENTER);

        gbc.gridx = columna;
        gbc.gridy = fila;
        gbc.gridwidth = 1;

        panel.add(contenedor, gbc);
    }


    // Obtiene los datos del formulario, pide el registro y muestra el resultado de la operación.
    private void guardarTramite() {

        try {

            // Obtener los datos del formulario
            String apellidoNombre = txtApellidoNombre.getText().trim();
            String ubicacion = txtUbicacion.getText().trim();
            String usuarioGDEBA = txtUsuarioGDEBA.getText().trim();
            String motivo = txtMotivo.getText().trim();
            String actoAdministrativo =
                txtActoAdministrativo.getText().trim();
            String ticketAsociado =
                txtTicketAsociado.getText().trim();
            String observaciones =
                txtObservaciones.getText().trim();

            // Convertir la fecha ingresada
            DateTimeFormatter formato = DateTimeFormatter
                .ofPattern("dd/MM/uuuu")
                .withResolverStyle(ResolverStyle.STRICT);

            LocalDate fechaBaja = LocalDate.parse(
                txtFechaBaja.getText().trim(),
                formato
        );

            // Crear el agente
            Agente agente = new Agente(
                0,
                apellidoNombre,
                ubicacion,
                usuarioGDEBA
        );

        // Asigna el estado Iniciado al nuevo trámite
            EstadoTramite estado = new EstadoTramite(
                1,
                "Iniciado"
        );

        // Crear el trámite
            TramiteBaja tramite = new TramiteBaja(
                0,
                fechaBaja,
                motivo,
                actoAdministrativo,
                observaciones,
                ticketAsociado,
                agente,
                estado
        );

        // Registrar mediante el servicio
            RegistroBajaService service =
                new RegistroBajaService();

            boolean registrado =
                service.registrarTramite(agente, tramite);

            if (registrado) {

                JOptionPane.showMessageDialog(
                    this,
                    "El trámite de baja se registró correctamente.",
                    "Registro exitoso",
                    JOptionPane.INFORMATION_MESSAGE
                    
            );

            pantallaPrincipal.cargarTramites();
            
            dispose();

        }   else {

                JOptionPane.showMessageDialog(
                    this,
                    "Verifique los datos obligatorios y sus longitudes.",
                    "Datos inválidos",
                    JOptionPane.WARNING_MESSAGE
            );
        }

        }catch (DateTimeParseException e) {

            JOptionPane.showMessageDialog(
                this,
                "La fecha debe tener el formato DD/MM/AAAA y ser válida.",
                "Fecha incorrecta",
                JOptionPane.WARNING_MESSAGE
        );

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                this,
                "No se pudo registrar el trámite en la base de datos.\n"
                        + e.getMessage(),
                "Error de base de datos",
                JOptionPane.ERROR_MESSAGE
        );
    }
}

    
}
