package com.latam.alura.hotel.controller;

import com.latam.alura.hotel.dao.HuespedDao;
import com.latam.alura.hotel.dao.ReservaDao;
import com.latam.alura.hotel.factory.ConexionFactory;
import com.latam.alura.hotel.model.Huesped;


import java.sql.Connection;
import java.util.List;

public class HuespedController {
    private ReservaDao reservaDao;
    private HuespedDao huespedDao;
    private static Long reserva = ReservaController.getReservaId();
    private Connection con;

    public HuespedController() {
        this.con = new ConexionFactory().recuperaConexion();
        this.huespedDao = new HuespedDao(this.con);
    }

    public void guardar(Huesped huesped){

        huesped.setReservaId(reserva);



        this.huespedDao.guardarHuesped(huesped);
    }

    public Long getReserva() {
        return reserva;
    }

    public List<Huesped> obtenerHuespedes() {
        return this.huespedDao.obtenerHuespedes();
    }

    public List<Huesped> obtenerPorApellido(String apellido) {
        return this.huespedDao.obtenerPorApellido(apellido);
    }

    public List<Huesped> obtenerPorReserva(int text) {
        return this.huespedDao.obtenerPorReserva(text);
    }

    public int modificar(Huesped huesped) {
        return this.huespedDao.modificar(huesped);
    }

    public int eliminar(int id) {
        return this.huespedDao.eliminar(id);
    }
}
