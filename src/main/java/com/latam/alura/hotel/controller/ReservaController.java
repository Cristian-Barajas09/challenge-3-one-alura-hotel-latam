package com.latam.alura.hotel.controller;

import com.latam.alura.hotel.dao.ReservaDao;
import com.latam.alura.hotel.factory.ConexionFactory;
import com.latam.alura.hotel.model.Reserva;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ReservaController {

    private static Long idReserva;
    private final ReservaDao reservaDao;
    private Connection con;

    public ReservaController() {
        this.con = new ConexionFactory().recuperaConexion();
        this.reservaDao = new ReservaDao(this.con);
    }

    public BigDecimal calcularTotal(Date fecha1, Date fecha2) {
        long resultado = fecha2.getTime() - fecha1.getTime();
        int valorReserva = 15;
        TimeUnit time = TimeUnit.DAYS;

        long resto = time.convert(resultado, TimeUnit.MILLISECONDS);

        return new BigDecimal(resto * valorReserva);



    }

    public void guardarReserva(Reserva reserva) {

        idReserva = this.reservaDao.guardar(reserva);
    }

    public static Long getReservaId(){
        return idReserva;
    }

    public List<Reserva> obtenerReservas() {
        return this.reservaDao.obtenerReservas();
    }

    public List<Reserva> obtenerReservaPorNumero(int numero) {
        return this.reservaDao.obtenerReservaPorNumero(numero);
    }

    public int modificar(Reserva reserva) {
        return this.reservaDao.modificar(reserva);
    }

    public int eliminar(int id) {
        return this.reservaDao.eliminar(id);
    }
}
