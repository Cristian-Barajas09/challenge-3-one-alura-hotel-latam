package com.latam.alura.hotel.view;

import com.latam.alura.hotel.controller.LoginController;
import com.latam.alura.hotel.controller.SessionController;
import com.latam.alura.hotel.dao.ValorDao;
import com.latam.alura.hotel.model.Usuario;
import com.latam.alura.hotel.model.Valor;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@SuppressWarnings("serial")
public class MenuUsuario extends JFrame {

    private final JPanel btnPerfil;
    private JPanel contentPane;
    int xMouse, yMouse;
    private JLabel labelExit;
    private JLabel labelRegistro;

    private SessionController sessionController;
    private ValorDao valorDao;
    private LoginController login ;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MenuUsuario frame = new MenuUsuario();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public MenuUsuario() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/aH-40px.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 944, 609);

        login =  new LoginController();
        sessionController = new SessionController();
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setUndecorated(true);

        JPanel header = new JPanel();
        header.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                headerMouseDragged(e);
            }
        });
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                headerMousePressed(e);
            }
        });

        JPanel panelMenu = new JPanel();
        panelMenu.setBackground(new Color(12, 138, 199));
        panelMenu.setBounds(0, 0, 257, 609);
        contentPane.add(panelMenu);
        panelMenu.setLayout(null);

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setBounds(50, 58, 150, 150);
        panelMenu.add(lblNewLabel_2);
        lblNewLabel_2.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/img/aH-150px.png"))));

        JPanel btnRegistro = new JPanel();
        btnRegistro.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnRegistro.setBackground(new Color(118, 187, 223));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnRegistro.setBackground(new Color(12, 138, 199));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                ReservasView reservas = new ReservasView();
                reservas.setVisible(true);
                dispose();
            }
        });
        btnRegistro.setBounds(0, 255, 257, 56);
        btnRegistro.setBackground(new Color(12, 138, 199));
        panelMenu.add(btnRegistro);
        btnRegistro.setLayout(null);

        labelRegistro = new JLabel("Registro de reservas");
        labelRegistro.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/img/reservado.png"))));
        labelRegistro.setForeground(SystemColor.text);
        labelRegistro.setBounds(25, 11, 205, 34);
        labelRegistro.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelRegistro.setHorizontalAlignment(SwingConstants.LEFT);
        btnRegistro.add(labelRegistro);

        JPanel btnBusqueda = new JPanel();
        btnBusqueda.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnBusqueda.setBackground(new Color(118, 187, 223));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnBusqueda.setBackground(new Color(12, 138, 199));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                Busqueda busqueda = new Busqueda();
                busqueda.setVisible(true);
                dispose();
            }
        });
        btnBusqueda.setBounds(0, 312, 257, 56);
        btnBusqueda.setBackground(new Color(12, 138, 199));
        panelMenu.add(btnBusqueda);
        btnBusqueda.setLayout(null);

        JLabel lblBusquedaDeReservas = new JLabel("Búsqueda");
        lblBusquedaDeReservas.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/img/pessoas.png"))));
        lblBusquedaDeReservas.setBounds(27, 11, 200, 34);
        lblBusquedaDeReservas.setHorizontalAlignment(SwingConstants.LEFT);
        lblBusquedaDeReservas.setForeground(Color.WHITE);
        lblBusquedaDeReservas.setFont(new Font("Roboto", Font.PLAIN, 18));
        btnBusqueda.add(lblBusquedaDeReservas);

        JPanel btnGuardarValor = new JPanel();
        btnGuardarValor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnGuardarValor.setBackground(new Color(118, 187, 223));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnGuardarValor.setBackground(new Color(12, 138, 199));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                String valor = JOptionPane.showInputDialog("ingrese el nuevo precio de las reservas");
                valorDao = new ValorDao();

                Valor valor1 = new Valor(Float.parseFloat(valor));

                valorDao.guardar(valor1);
            }
        });
        btnGuardarValor.setBounds(0, 369, 257, 56);
        btnGuardarValor.setBackground(new Color(12, 138, 199));
        panelMenu.add(btnGuardarValor);
        btnGuardarValor.setLayout(null);

        JLabel lblGuardarValor = new JLabel("Guardar valor");
        lblGuardarValor.setBounds(27, 11, 200, 34);
        lblGuardarValor.setHorizontalAlignment(SwingConstants.LEFT);
        lblGuardarValor.setForeground(Color.WHITE);
        lblGuardarValor.setFont(new Font("Roboto", Font.PLAIN, 18));
        btnGuardarValor.add(lblGuardarValor);


        JPanel btnCrearUsuario = new JPanel();
        btnCrearUsuario.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnCrearUsuario.setBackground(new Color(118, 187, 223));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnCrearUsuario.setBackground(new Color(12, 138, 199));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                CrearUsuarioView frame = new CrearUsuarioView();
                frame.setVisible(true);
                dispose();
            }
        });
        btnCrearUsuario.setBounds(0, 425, 257, 56);
        btnCrearUsuario.setBackground(new Color(12, 138, 199));
        panelMenu.add(btnCrearUsuario);
        btnCrearUsuario.setLayout(null);

        JLabel lblCrearUsuario = new JLabel("Crear Usuario");
        lblCrearUsuario.setBounds(27, 11, 200, 34);
        lblCrearUsuario.setHorizontalAlignment(SwingConstants.LEFT);
        lblCrearUsuario.setForeground(Color.WHITE);
        lblCrearUsuario.setFont(new Font("Roboto", Font.PLAIN, 18));
        btnCrearUsuario.add(lblCrearUsuario);

        btnCrearUsuario.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });


        JPanel btnCerrarSesion = new JPanel();
        btnCerrarSesion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnCerrarSesion.setBackground(new Color(118, 187, 223));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnCerrarSesion.setBackground(new Color(12, 138, 199));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                sessionController = new SessionController();
                sessionController.eliminarSesion();
                MenuPrincipal menuPrincipal = new MenuPrincipal();
                menuPrincipal.setVisible(true);
                dispose();
            }
        });
        btnCerrarSesion.setBounds(0, 481, 257, 56);
        btnCerrarSesion.setBackground(new Color(12, 138, 199));
        panelMenu.add(btnCerrarSesion);
        btnCerrarSesion.setLayout(null);

        JLabel lblCerrarSesion = new JLabel("Cerrar Sesión");
        lblCerrarSesion.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/img/cerrar-sesion 32-px.png"))));
        lblCerrarSesion.setBounds(27, 11, 200, 34);
        lblCerrarSesion.setHorizontalAlignment(SwingConstants.LEFT);
        lblCerrarSesion.setForeground(Color.WHITE);
        lblCerrarSesion.setFont(new Font("Roboto", Font.PLAIN, 18));
        btnCerrarSesion.add(lblCerrarSesion);






        JSeparator separator = new JSeparator();
        separator.setBounds(26, 219, 201, 2);
        panelMenu.add(separator);
        header.setLayout(null);
        header.setBackground(Color.WHITE);
        header.setBounds(0, 0, 944, 36);
        contentPane.add(header);

        JPanel btnexit = new JPanel();
        btnexit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int result = JOptionPane.showConfirmDialog(
                        new JFrame(),
                        "¿Desea salir de la aplicacion?",
                        "pregunta",
                        JOptionPane.YES_NO_OPTION);
                if(result == 0) {
                    System.exit(0);
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                btnexit.setBackground(Color.red);
                labelExit.setForeground(Color.white);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnexit.setBackground(Color.white);
                labelExit.setForeground(Color.black);
            }
        });

        btnexit.setLayout(null);
        btnexit.setBackground(Color.WHITE);
        btnexit.setBounds(891, 0, 53, 36);
        header.add(btnexit);

        labelExit = new JLabel("X");
        labelExit.setBounds(0, 0, 53, 36);
        btnexit.add(labelExit);
        labelExit.setHorizontalAlignment(SwingConstants.CENTER);
        labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));

        JPanel panelFecha = new JPanel();
        panelFecha.setBackground(new Color(118, 187, 223));
        panelFecha.setBounds(256, 84, 688, 121);
        contentPane.add(panelFecha);
        panelFecha.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("Sistema de reservas Hotel Alura");
        lblNewLabel_1.setBounds(180, 11, 356, 42);
        panelFecha.add(lblNewLabel_1);
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setFont(new Font("Roboto", Font.PLAIN, 24));

        JLabel labelFecha = new JLabel("New label");
        labelFecha.setBounds(35, 64, 294, 36);
        panelFecha.add(labelFecha);
        labelFecha.setForeground(Color.WHITE);
        labelFecha.setFont(new Font("Roboto", Font.PLAIN, 33));
        Date fechaActual = new Date(); //fecha y hora actual
        String fecha = new SimpleDateFormat("dd/MM/yyyy").format(fechaActual); //formatear la fecha en una cadena
        labelFecha.setText("Hoy es " + fecha); //setear la representacion en cadena de la fecha



        Usuario  usuario = login.obtenerUsuarioPorId(sessionController.obtenerSession().getIdUsuario());

        MenuPerfil menuPerfil = new MenuPerfil(usuario);

        btnPerfil = new JPanel();
        btnPerfil.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnPerfil.setBackground(new Color(215, 215, 215));
            }
            @Override
            public void mouseExited(MouseEvent e) {btnPerfil.setBackground(new Color(255, 255, 255));}
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuPerfil menuPerfil = new MenuPerfil(usuario);
                menuPerfil.setVisible(true);
                dispose();
            }
        });
        btnPerfil.setBounds(590, 65, 37, 36);
        btnPerfil.setBackground(new Color(255, 255, 255));
        btnPerfil.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelFecha.add(btnPerfil);
        btnPerfil.setLayout(null);

        JLabel lblPerfil = new JLabel();
        lblPerfil.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/img/perfil-del-usuario.png"))));
        lblPerfil.setBounds(5, 0, 33, 36);
        lblPerfil.setHorizontalAlignment(SwingConstants.LEFT);
        lblPerfil.setForeground(Color.WHITE);
        lblPerfil.setFont(new Font("Roboto", Font.PLAIN, 18));
        btnPerfil.add(lblPerfil);


        JLabel lblNewLabel = new JLabel("Bienvenido " + usuario.getNombre());
        lblNewLabel.setFont(new Font("Roboto", Font.BOLD, 24));
        lblNewLabel.setBounds(302, 234, 447, 46);
        contentPane.add(lblNewLabel);

        String textoDescripcion = "<html><body>Sistema de reserva de hotel. Controle y administre de forma óptima y fácil <br> el flujo de reservas y de huespédes del hotel   </body></html>";
        JLabel labelDescripcion = new JLabel(textoDescripcion);
        labelDescripcion.setFont(new Font("Roboto", Font.PLAIN, 17));

        labelDescripcion.setBounds(312, 291, 598, 66);
        contentPane.add(labelDescripcion);

        String textoDescripcion1 = "<html><body> Esta herramienta le permitirá llevar un control completo y detallado de sus reservas y huéspedes, tendrá acceso a heramientas especiales para tareas específicas como lo son:</body></html>";
        JLabel labelDescripcion_1 = new JLabel(textoDescripcion1);
        labelDescripcion_1.setFont(new Font("Roboto", Font.PLAIN, 17));
        labelDescripcion_1.setBounds(311, 345, 569, 88);
        contentPane.add(labelDescripcion_1);

        JLabel lblNewLabel_3 = new JLabel("- Registro de Reservas y Huéspedes");
        lblNewLabel_3.setFont(new Font("Roboto", Font.PLAIN, 17));
        lblNewLabel_3.setBounds(312, 444, 295, 27);
        contentPane.add(lblNewLabel_3);

        JLabel lblNewLabel_3_1 = new JLabel("- Edición de Reservas y Huéspedes existentes");
        lblNewLabel_3_1.setFont(new Font("Roboto", Font.PLAIN, 17));
        lblNewLabel_3_1.setBounds(312, 482, 355, 27);
        contentPane.add(lblNewLabel_3_1);

        JLabel lblNewLabel_3_2 = new JLabel("- Eliminar todo tipo de registros");
        lblNewLabel_3_2.setFont(new Font("Roboto", Font.PLAIN, 17));
        lblNewLabel_3_2.setBounds(312, 520, 295, 27);
        contentPane.add(lblNewLabel_3_2);
    }

    private void headerMousePressed(java.awt.event.MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_headerMousePressed

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }
}