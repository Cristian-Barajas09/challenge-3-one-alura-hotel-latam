package com.latam.alura.hotel.view;

import com.latam.alura.hotel.controller.HuespedController;
import com.latam.alura.hotel.controller.ReservaController;
import com.latam.alura.hotel.model.Huesped;
import com.latam.alura.hotel.model.Reserva;
import com.toedter.calendar.JCalendar;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.sql.Date;
import java.util.List;
import java.awt.Toolkit;
import java.util.Objects;
import java.util.Optional;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

    private JPanel contentPane;
    private JTextField txtBuscar;
    private JTable tbHuespedes;
    private JTable tbReservas;
    private String[] formaPago = new String[] {"Tarjeta de Crédito", "Tarjeta de Débito", "Dinero en efectivo"};
    private DefaultTableModel modelo;
    private DefaultTableModel modeloHuesped;
    private JLabel labelAtras;
    private JLabel labelExit;
    int xMouse, yMouse;
    private ReservaController reservaController;
    private HuespedController huespedController;

    private final String[] nacionalidad = new String[] {
            "afgano-afgana",
            "alemán-",
            "alemana",
            "árabe-árabe",
            "argentino-argentina",
            "australiano-australiana",
            "belga-belga",
            "boliviano-boliviana",
            "brasileño-brasileña",
            "camboyano-camboyana",
            "canadiense-canadiense",
            "chileno-chilena",
            "chino-china",
            "colombiano-colombiana",
            "coreano-coreana",
            "costarricense-costarricense",
            "cubano-cubana",
            "danés-danesa",
            "ecuatoriano-ecuatoriana",
            "egipcio-egipcia",
            "salvadoreño-salvadoreña",
            "escocés-escocesa",
            "español-española",
            "estadounidense-estadounidense",
            "estonio-estonia",
            "etiope-etiope",
            "filipino-filipina",
            "finlandés-finlandesa",
            "francés-francesa",
            "galés-galesa",
            "griego-griega",
            "guatemalteco-guatemalteca",
            "haitiano-haitiana",
            "holandés-holandesa",
            "hondureño-hondureña",
            "indonés-indonesa",
            "inglés-inglesa",
            "iraquí-iraquí",
            "iraní-iraní",
            "irlandés-irlandesa",
            "israelí-israelí",
            "italiano-italiana",
            "japonés-japonesa",
            "jordano-jordana",
            "laosiano-laosiana",
            "letón-letona",
            "letonés-letonesa",
            "malayo-malaya",
            "marroquí-marroquí",
            "mexicano-mexicana",
            "nicaragüense-nicaragüense",
            "noruego-noruega",
            "neozelandés-neozelandesa",
            "panameño-panameña",
            "paraguayo-paraguaya",
            "peruano-peruana",
            "polaco-polaca",
            "portugués-portuguesa",
            "puertorriqueño-puertorriqueño",
            "dominicano-dominicana",
            "rumano-rumana",
            "ruso-rusa",
            "sueco-sueca",
            "suizo-suiza",
            "tailandés-tailandesa",
            "taiwanes-taiwanesa",
            "turco-turca",
            "ucraniano-ucraniana",
            "uruguayo-uruguaya",
            "venezolano-venezolana",
            "vietnamita-vietnamita"
    };

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Busqueda frame = new Busqueda();
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
    public Busqueda() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/lupa2.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 910, 571);

        this.huespedController = new HuespedController();
        this.reservaController = new ReservaController();
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        setUndecorated(true);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(536, 127, 193, 31);
        txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contentPane.add(txtBuscar);
        txtBuscar.setColumns(10);


        JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
        lblNewLabel_4.setForeground(new Color(12, 138, 199));
        lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
        lblNewLabel_4.setBounds(331, 62, 280, 42);
        contentPane.add(lblNewLabel_4);

        JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
        panel.setBackground(new Color(12, 138, 199));
        panel.setFont(new Font("Roboto", Font.PLAIN, 16));
        panel.setBounds(20, 169, 865, 328);
        contentPane.add(panel);


        tbReservas = new JTable();
        tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));


        modelo = (DefaultTableModel) tbReservas.getModel();
        modelo.addColumn("Numero de Reserva");
        modelo.addColumn("Fecha Check In");
        modelo.addColumn("Fecha Check Out");
        modelo.addColumn("Valor");
        modelo.addColumn("Forma de Pago");

        JComboBox<String> formaPagoCombo = new JComboBox<>(formaPago);
        TableColumn tableColumn1 = this.tbReservas.getColumnModel().getColumn(4);
        TableCellEditor tce1 = new DefaultCellEditor(formaPagoCombo);
        tableColumn1.setCellEditor(tce1);


        cargarTablaReservas();



        JScrollPane scroll_table = new JScrollPane(tbReservas);
        panel.addTab("Reservas", new ImageIcon(Objects.requireNonNull(getClass().getResource("/img/reservado.png"))), scroll_table, null);
        scroll_table.setVisible(true);


        tbHuespedes = new JTable();
        tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
        modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();

        modeloHuesped.addColumn("Número de Huesped");
        modeloHuesped.addColumn("Nombre");
        modeloHuesped.addColumn("Apellido");
        modeloHuesped.addColumn("Fecha de Nacimiento");
        modeloHuesped.addColumn("Nacionalidad");
        modeloHuesped.addColumn("Telefono");
        modeloHuesped.addColumn("Número de Reserva");

        JComboBox<String> jComboBox = new JComboBox<>(nacionalidad);
        TableColumn tableColumn = this.tbHuespedes.getColumnModel().getColumn(4);
        TableCellEditor tce = new DefaultCellEditor(jComboBox);
        tableColumn.setCellEditor(tce);

        JCalendar fechaNacimiento = new JCalendar();
        TableColumn tableColumn2 = this.tbHuespedes.getColumnModel().getColumn(3);

        cargarTablaHuespedes();
        JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
        panel.addTab("Huéspedes", new ImageIcon(Objects.requireNonNull(getClass().getResource("/img/pessoas.png"))), scroll_tableHuespedes, null);
        scroll_tableHuespedes.setVisible(true);

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/img/Ha-100px.png"))));
        lblNewLabel_2.setBounds(56, 51, 104, 107);
        contentPane.add(lblNewLabel_2);

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
        header.setLayout(null);
        header.setBackground(Color.WHITE);
        header.setBounds(0, 0, 910, 36);
        contentPane.add(header);

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

        JPanel btnexit = new JPanel();
        btnexit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int result = JOptionPane.showConfirmDialog(
                        new JFrame(),
                        "¿Desea salir de la aplicacion?",
                        "pregunta",
                        JOptionPane.YES_NO_OPTION);
                if (result == 0) {
                    System.exit(0);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
                btnexit.setBackground(Color.red);
                labelExit.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
                btnexit.setBackground(Color.white);
                labelExit.setForeground(Color.black);
            }
        });
        btnexit.setLayout(null);
        btnexit.setBackground(Color.WHITE);
        btnexit.setBounds(857, 0, 53, 36);
        header.add(btnexit);

        labelExit = new JLabel("X");
        labelExit.setHorizontalAlignment(SwingConstants.CENTER);
        labelExit.setForeground(Color.BLACK);
        labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelExit.setBounds(0, 0, 53, 36);
        btnexit.add(labelExit);

        JSeparator separator_1_2 = new JSeparator();
        separator_1_2.setForeground(new Color(12, 138, 199));
        separator_1_2.setBackground(new Color(12, 138, 199));
        separator_1_2.setBounds(539, 159, 193, 2);
        contentPane.add(separator_1_2);

        JPanel btnbuscar = new JPanel();
        btnbuscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {

                    cargarTablaHuespedesPorReserva(huespedController.obtenerPorReserva(Integer.parseInt(txtBuscar.getText())));
                    cargarTablaReservasResult(reservaController.obtenerReservaPorNumero(Integer.parseInt(txtBuscar.getText())));
                } catch (NumberFormatException err) {


                    cargarTablaHuespedesResult(huespedController.obtenerPorApellido(txtBuscar.getText()));
                }
            }
        });
        btnbuscar.setLayout(null);
        btnbuscar.setBackground(new Color(12, 138, 199));
        btnbuscar.setBounds(748, 125, 122, 35);
        btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnbuscar);

        JLabel lblBuscar = new JLabel("BUSCAR");
        lblBuscar.setBounds(0, 0, 122, 35);
        btnbuscar.add(lblBuscar);
        lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
        lblBuscar.setForeground(Color.WHITE);
        lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

        // restaurar resultados
        JPanel btnRestaurar = new JPanel();
        btnRestaurar.setLayout(null);
        btnRestaurar.setBackground(new Color(12, 138, 199));
        btnRestaurar.setBounds(503, 508, 122, 35);
        btnRestaurar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnRestaurar);

        JLabel restaurar = new JLabel("RESTAURAR");
        restaurar.setHorizontalAlignment(SwingConstants.CENTER);
        restaurar.setForeground(Color.WHITE);
        restaurar.setFont(new Font("Roboto", Font.PLAIN, 18));
        restaurar.setBounds(0, 0, 122, 35);
        btnRestaurar.add(restaurar);

        btnRestaurar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                cargarTablaReservas();
                cargarTablaHuespedes();
            }
        });


        // fin restaurar resultados

        // editar
        JPanel btnEditar = new JPanel();
        btnEditar.setLayout(null);
        btnEditar.setBackground(new Color(12, 138, 199));
        btnEditar.setBounds(635, 508, 122, 35);
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnEditar);

        JLabel lblEditar = new JLabel("EDITAR");
        lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
        lblEditar.setForeground(Color.WHITE);
        lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblEditar.setBounds(0, 0, 122, 35);
        btnEditar.add(lblEditar);

        btnEditar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                modificar();


            }
        });



        // fin editar

        //eliminar

        JPanel btnEliminar = new JPanel();
        btnEliminar.setLayout(null);
        btnEliminar.setBackground(new Color(12, 138, 199));
        btnEliminar.setBounds(767, 508, 122, 35);
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnEliminar);

        JLabel lblEliminar = new JLabel("ELIMINAR");
        lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
        lblEliminar.setForeground(Color.WHITE);
        lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblEliminar.setBounds(0, 0, 122, 35);
        btnEliminar.add(lblEliminar);
        setResizable(false);

        btnEliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                eliminar();
            }
        });
        //fin eliminar
    }

    private void eliminar(){


        if(tieneFilaElegidaReserva() && tieneFilaElegidaHuespedes()) {
            JOptionPane.showMessageDialog(this, "Por favor, elija un campo de reserva o huesped");
            return;
        }

        if(!tieneFilaElegidaReserva()){

            eliminarReserva();
        } else {
            eliminarHuesped();
        }
    }

    private void eliminarReserva() {
        Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
                .ifPresentOrElse(fila -> {
                    int id = Integer.parseInt(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
                    int CantidadEliminada;
                    CantidadEliminada = this.reservaController.eliminar(id);

                    modelo.removeRow(tbReservas.getSelectedRow());

                    JOptionPane.showMessageDialog(this,CantidadEliminada + " Item eliminado con éxito!");
                }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
    }

    private void eliminarHuesped() {
        Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
                .ifPresentOrElse(fila -> {
                    int id = Integer.parseInt(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
                    int CantidadEliminada;
                    CantidadEliminada = this.huespedController.eliminar(id);

                    modeloHuesped.removeRow(tbHuespedes.getSelectedRow());

                    JOptionPane.showMessageDialog(this,CantidadEliminada + " huesped eliminado con éxito!");
                }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un huesped"));
    }

    private void modificar(){


        if(tieneFilaElegidaReserva() && tieneFilaElegidaHuespedes()) {
            JOptionPane.showMessageDialog(this, "Por favor, elija un campo de reserva o huesped");
            return;
        }

        if(!tieneFilaElegidaReserva()){
            modificarReserva();
            cargarTablaReservas();
        } else {
            this.modificarHuesped();
            cargarTablaHuespedes();

        }

    }
    private void modificarHuesped() {
        Optional.ofNullable(this.modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
                .ifPresentOrElse(fila -> {
                    Long id = Long.parseLong( modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
                    String nombre = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 1);
                    String apellido = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 2);
                    Date fNacimiento =  Date.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 3).toString());
                    String nacionalidad = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 4);
                    String telefono =(String)  modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(),5);

                    Long reserva =Long.valueOf(  modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(),6).toString());


                    int cantidadAfectada;

                    Huesped huesped = new Huesped(
                        id,nombre,apellido,fNacimiento,nacionalidad,telefono,reserva
                    );


                    cantidadAfectada = this.huespedController.modificar(huesped);

                    JOptionPane.showMessageDialog(this,cantidadAfectada + " Huesped actualizado con éxito!");
                }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un Huesped"));
    }

    private void modificarReserva() {
        Optional.ofNullable(this.modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
                .ifPresentOrElse(fila -> {
                    Long id = Long.parseLong(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
                    Date fEntrada =  Date.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 1).toString());
                    Date fSalida = Date.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 2).toString());
                    double valor = Double.parseDouble( modelo.getValueAt(tbReservas.getSelectedRow(), 3).toString());
                    String formaDePago =(String)  modelo.getValueAt(tbReservas.getSelectedRow(),4);

//

                    int cantidadAfectada;

                    Reserva reserva = new Reserva(
                            fEntrada,fSalida,valor,formaDePago
                    );
                    reserva.setId(id);


                    cantidadAfectada = this.reservaController.modificar(reserva);
                    JOptionPane.showMessageDialog(this,cantidadAfectada + " reserva actualizado con éxito!");
                }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije una reserva"));
    }

    private boolean tieneFilaElegidaReserva() {
        return tbReservas.getSelectedRowCount() == 0 || tbReservas.getSelectedColumnCount() == 0;
    }

    private boolean tieneFilaElegidaHuespedes() {
        return tbHuespedes.getSelectedRowCount() == 0 || tbHuespedes.getSelectedColumnCount() == 0;
    }

    private void cargarTablaHuespedes() {

        modeloHuesped.setRowCount(0);


        var huespedes = this.huespedController.obtenerHuespedes();



        huespedes.forEach(huesped -> this.modeloHuesped.addRow(new Object[]{
                huesped.getId(),
                huesped.getNombre(),
                huesped.getApellido(),
                huesped.getF_nacimiento(),
                huesped.getNacionalidad(),
                huesped.getTelefono(),huesped.getReservaId()
        }));
    }



    private void cargarTablaHuespedesResult(List<Huesped> huespedList) {
        modeloHuesped.setRowCount(0);

        huespedList.forEach(huesped -> this.modeloHuesped.addRow(new Object[]{
                huesped.getId(),
                huesped.getNombre(),
                huesped.getApellido(),
                huesped.getF_nacimiento(),
                huesped.getNacionalidad(),
                huesped.getTelefono(),huesped.getReservaId()
        }));
    }
    private void cargarTablaHuespedesPorReserva(List<Huesped> huespedList) {
        modeloHuesped.setRowCount(0);

        huespedList.forEach(huesped -> this.modeloHuesped.addRow(new Object[]{
                huesped.getId(),
                huesped.getNombre(),
                huesped.getApellido(),
                huesped.getF_nacimiento(),
                huesped.getNacionalidad(),
                huesped.getTelefono(),huesped.getReservaId()
        }));
    }

    private void cargarTablaReservas() {
        modelo.setRowCount(0);
        var reservas = this.reservaController.obtenerReservas();
        reservas.forEach(reserva -> this.modelo.addRow(new Object[]{
                reserva.getId(),reserva.getFecha_entrante(),
                reserva.getFecha_salida(),reserva.getValor(),
                reserva.getForma_pago()
        }));
    }
    private void cargarTablaReservasResult(List<Reserva> reservaList) {
        modelo.setRowCount(0);
        reservaList.forEach(reserva -> this.modelo.addRow(new Object[]{
                reserva.getId(),reserva.getFecha_entrante(),
                reserva.getFecha_salida(),reserva.getValor(),
                reserva.getForma_pago()
        }));
    }

    //Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
    private void headerMousePressed(java.awt.event.MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
    }

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }
}