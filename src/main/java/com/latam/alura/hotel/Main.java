package com.latam.alura.hotel;
import com.latam.alura.hotel.controller.SessionController;
import com.latam.alura.hotel.model.Session;
import com.latam.alura.hotel.view.MenuPrincipal;
import com.latam.alura.hotel.view.MenuUsuario;


import java.awt.*;


public class Main {
    private static final SessionController session = new SessionController();
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Session session1 = session.obtenerSession();
            try {
                if(session1.getIdUsuario() == null) {
                    MenuPrincipal frame = new MenuPrincipal();
                    frame.setVisible(true);
                } else {
                    MenuUsuario frame = new MenuUsuario();
                    frame.setVisible(true);
                }




            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}