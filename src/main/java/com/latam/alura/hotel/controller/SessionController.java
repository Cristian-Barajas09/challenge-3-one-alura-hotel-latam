package com.latam.alura.hotel.controller;

import com.latam.alura.hotel.dao.SessionDao;
import com.latam.alura.hotel.factory.ConexionFactory;
import com.latam.alura.hotel.model.Session;

import java.sql.Connection;

public class  SessionController {
    private static Session session;

    private  SessionDao sessionDao;

    public SessionController(){
        Connection con = new ConexionFactory().recuperaConexion();
        this.sessionDao = new SessionDao(con);
    }

    public void guardarSession(Session sessionUser){
        session = sessionUser;
        this.sessionDao.guardarSession(sessionUser);
    }

    public Session obtenerSession(){
        if(session == null){
            return this.sessionDao.obtenerSession();
        }

        return session;
    }

    public void eliminarSesion() {
        if(session == null) {
            this.sessionDao.eliminarSession();
            return;
        }
        session = null;

    }
}
