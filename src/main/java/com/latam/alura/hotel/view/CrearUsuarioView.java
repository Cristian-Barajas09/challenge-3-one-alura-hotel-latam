package com.latam.alura.hotel.view;

import com.latam.alura.hotel.controller.LoginController;
import com.latam.alura.hotel.controller.SessionController;
import com.latam.alura.hotel.model.Session;
import com.latam.alura.hotel.model.Usuario;
import com.latam.alura.hotel.utils.ValidatePasswords;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Objects;

public class CrearUsuarioView extends JFrame {



    private static final long serialVersionUID = 1L;
    private final JTextField txtNombre;
    private final JTextField txtApellido;
    private final JLabel labelAtras;
    private JPanel contentPane;
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    int xMouse, yMouse;
    private JLabel labelExit;

    private LoginController login;
    private SessionController sessionController;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CrearUsuarioView frame = new CrearUsuarioView();
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
    public CrearUsuarioView() {

        setResizable(false);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 788, 527);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);

        this.login = new LoginController();
        this.sessionController = new SessionController();
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 788, 527);
        panel.setBackground(Color.WHITE);
        contentPane.add(panel);
        panel.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(12, 138, 199));
        panel_1.setBounds(484, 0, 304, 527);
        panel.add(panel_1);
        panel_1.setLayout(null);


//        Icon imagenFondo = new ImageIcon("img/img-hotel-login-.png");
        JLabel imgHotel = new JLabel("");
        imgHotel.setBounds(0, 0, 304, 538);
        panel_1.add(imgHotel);

        Icon imagenFondo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/img/img-hotel-login-.png")));

        imgHotel.setIcon(imagenFondo);



        JPanel btnexit = new JPanel();
        btnexit.setBounds(251, 0, 53, 36);
        panel_1.add(btnexit);
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
                btnexit.setBackground(new Color(12, 138, 199));
                labelExit.setForeground(Color.white);
            }
        });
        btnexit.setBackground(new Color(12, 138, 199));
        btnexit.setLayout(null);
        btnexit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        labelExit = new JLabel("X");
        labelExit.setBounds(0, 0, 53, 36);
        btnexit.add(labelExit);
        labelExit.setForeground(SystemColor.text);
        labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelExit.setHorizontalAlignment(SwingConstants.CENTER);

        txtNombre = new JTextField();
        txtNombre.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (txtApellido.getText().isEmpty()) {
                    txtApellido.setText("Ingrese su apellido");
                    txtApellido.setForeground(Color.black);
                }

                if (txtUsuario.getText().isEmpty()) {
                    txtUsuario.setText("Ingrese su nombre de usuario");
                    txtUsuario.setForeground(Color.black);
                }

                if (txtNombre.getText().equals("Ingrese su nombre")) {
                    txtNombre.setText("");
                    txtNombre.setForeground(Color.black);
                }

                if (String.valueOf(txtContrasena.getPassword()).isEmpty()) {
                    txtContrasena.setText("********");
                    txtContrasena.setForeground(Color.gray);
                }
            }
        });
        txtNombre.setFont(new Font("Roboto", Font.PLAIN, 16));
        txtNombre.setText("Ingrese su nombre");
        txtNombre.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        txtNombre.setForeground(SystemColor.activeCaptionBorder);
        txtNombre.setBounds(65, 156, 324, 32);
        panel.add(txtNombre);
        txtNombre.setColumns(10);

        JLabel LabelNombre = new JLabel("Nombre");
        LabelNombre.setForeground(SystemColor.textInactiveText);
        LabelNombre.setFont(new Font("Roboto Black", Font.PLAIN, 20));
        LabelNombre.setBounds(65, 126, 107, 26);
        panel.add(LabelNombre);

        JSeparator separator2 = new JSeparator();
        separator2.setBackground(new Color(0, 120, 215));
        separator2.setBounds(65, 188, 324, 2);
        panel.add(separator2);


        txtApellido = new JTextField();
        txtApellido.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (txtApellido.getText().equals("Ingrese su apellido")) {
                    txtApellido.setText("");
                    txtApellido.setForeground(Color.black);
                }

                if (txtUsuario.getText().isEmpty()) {
                    txtUsuario.setText("Ingrese su nombre de usuario");
                    txtUsuario.setForeground(Color.black);
                }

                if (txtNombre.getText().isEmpty()) {
                    txtNombre.setText("Ingrese su nombre");
                    txtNombre.setForeground(Color.black);
                }

                if (String.valueOf(txtContrasena.getPassword()).isEmpty()) {
                    txtContrasena.setText("********");
                    txtContrasena.setForeground(Color.gray);
                }
            }
        });
        txtApellido.setFont(new Font("Roboto", Font.PLAIN, 16));
        txtApellido.setText("Ingrese su apellido");
        txtApellido.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        txtApellido.setForeground(SystemColor.activeCaptionBorder);
        txtApellido.setBounds(65, 252, 324, 32);
        panel.add(txtApellido);
        txtApellido.setColumns(10);

        JLabel LabelApellido= new JLabel("Apellido");
        LabelApellido.setForeground(SystemColor.textInactiveText);
        LabelApellido.setFont(new Font("Roboto Black", Font.PLAIN, 20));
        LabelApellido.setBounds(65, 222, 107, 26);
        panel.add(LabelApellido);

        JSeparator separator3 = new JSeparator();
        separator3.setBackground(new Color(0, 120, 215));
        separator3.setBounds(65, 284, 324, 2);
        panel.add(separator3);


        txtUsuario = new JTextField();
        txtUsuario.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (txtUsuario.getText().equals("Ingrese su nombre de usuario")) {
                    txtUsuario.setText("");
                    txtUsuario.setForeground(Color.black);
                }
                if (String.valueOf(txtContrasena.getPassword()).isEmpty()) {
                    txtContrasena.setText("********");
                    txtContrasena.setForeground(Color.gray);
                }
                if (txtNombre.getText().isEmpty()) {
                    txtNombre.setText("Ingrese su nombre");
                    txtNombre.setForeground(Color.black);
                }
                if (txtApellido.getText().isEmpty()) {
                    txtApellido.setText("Ingrese su apellido");
                    txtApellido.setForeground(Color.black);
                }
            }
        });
        txtUsuario.setFont(new Font("Roboto", Font.PLAIN, 16));
        txtUsuario.setText("Ingrese su nombre de usuario");
        txtUsuario.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        txtUsuario.setForeground(SystemColor.activeCaptionBorder);
        txtUsuario.setBounds(65, 332, 324, 32);
        panel.add(txtUsuario);
        txtUsuario.setColumns(10);

        JSeparator separator = new JSeparator();
        separator.setBackground(new Color(0, 120, 215));
        separator.setBounds(65, 364, 324, 2);
        panel.add(separator);

        JLabel labelTitulo = new JLabel("REGISTRAR USUARIO");
        labelTitulo.setForeground(SystemColor.textHighlight);
        labelTitulo.setFont(new Font("Roboto Black", Font.PLAIN, 26));
        labelTitulo.setBounds(65, 30, 350, 26);
        panel.add(labelTitulo);

        JSeparator separator_1 = new JSeparator();
        separator_1.setBackground(SystemColor.textHighlight);
        separator_1.setBounds(65, 448, 324, 2);
        panel.add(separator_1);

        txtContrasena = new JPasswordField();
        txtContrasena.setText("********");

        txtContrasena.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (String.valueOf(txtContrasena.getPassword()).equals("********")) {
                    txtContrasena.setText("");
                    txtContrasena.setForeground(Color.gray);
                }
                if (txtUsuario.getText().isEmpty()) {
                    txtUsuario.setText("Ingrese su nombre de usuario");
                    txtUsuario.setForeground(Color.gray);
                }
                if (txtNombre.getText().isEmpty()) {
                    txtNombre.setText("Ingrese su nombre");
                    txtNombre.setForeground(Color.black);
                }
                if(txtApellido.getText().isEmpty()) {
                    txtApellido.setText("Ingrese su apellido");
                    txtNombre.setForeground(Color.gray);
                }
            }
        });
        txtContrasena.setForeground(SystemColor.activeCaptionBorder);
        txtContrasena.setFont(new Font("Roboto", Font.PLAIN, 16));
        txtContrasena.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        txtContrasena.setBounds(65, 416, 324, 32);
        panel.add(txtContrasena);


        JLabel LabelUsuario = new JLabel("USUARIO");
        LabelUsuario.setForeground(SystemColor.textInactiveText);
        LabelUsuario.setFont(new Font("Roboto Black", Font.PLAIN, 20));
        LabelUsuario.setBounds(65, 300, 107, 26);
        panel.add(LabelUsuario);

        JLabel lblContrasea = new JLabel("CONTRASEÑA");
        lblContrasea.setForeground(SystemColor.textInactiveText);
        lblContrasea.setFont(new Font("Roboto Black", Font.PLAIN, 20));
        lblContrasea.setBounds(65, 384, 140, 26);
        panel.add(lblContrasea);

        JPanel btnLogin = new JPanel();
        btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnLogin.setBackground(new Color(0, 156, 223));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnLogin.setBackground(SystemColor.textHighlight);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                signup();
            }
        });
        btnLogin.setBackground(SystemColor.textHighlight);
        btnLogin.setBounds(65, 471, 122, 44);
        panel.add(btnLogin);
        btnLogin.setLayout(null);
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        JLabel lblNewLabel = new JLabel("REGISTRAR");
        lblNewLabel.setBounds(0, 0, 122, 44);
        btnLogin.add(lblNewLabel);
        lblNewLabel.setForeground(SystemColor.controlLtHighlight);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setIcon(new ImageIcon("/img/lOGO-50PX.png"));
        lblNewLabel_1.setBounds(65, 65, 48, 59);
        panel.add(lblNewLabel_1);

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
        header.setBackground(SystemColor.window);
        header.setBounds(0, 0, 784, 36);
        panel.add(header);
        header.setLayout(null);

        JPanel btnAtras = new JPanel();
        btnAtras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuario usuario = new MenuUsuario();
                usuario.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnAtras.setBackground(new Color(12, 138, 199));
                labelAtras.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAtras.setBackground(Color.white);
                labelAtras.setForeground(Color.black);
            }
        });
        btnAtras.setLayout(null);
        btnAtras.setBackground(Color.WHITE);
        btnAtras.setBounds(0, 0, 53, 36);
        header.add(btnAtras);

        labelAtras = new JLabel("<");
        labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
        labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
        labelAtras.setBounds(0, 0, 53, 36);
        btnAtras.add(labelAtras);

    }

    private void signup() {

        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String usuario = txtUsuario.getText();
        String contrase = new String (txtContrasena.getPassword());


        if(
                (usuario.equals("Ingrese su nombre de usuario") || contrase.equals("********") || nombre.equals("Ingrese su nombre") || apellido.equals("Ingrese su apellido")) ||
                        (usuario.isEmpty() || contrase.isEmpty() || nombre.isEmpty() || apellido.isEmpty())
        ){
            JOptionPane.showMessageDialog(this, "por favor rellene todos los campos");
        } else {
            String newPass = ValidatePasswords.encryptPassword(contrase);
            Usuario usuario1 = new Usuario(
                    nombre,apellido,usuario,newPass
            );

            login.guardar(usuario1);

            MenuUsuario menuUsuario = new MenuUsuario();
            menuUsuario.setVisible(true);
            dispose();

        }
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

