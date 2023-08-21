package com.latam.alura.hotel.controller;

import com.latam.alura.hotel.dao.ReservaDao;
import com.latam.alura.hotel.dao.ValorDao;
import com.latam.alura.hotel.factory.ConexionFactory;
import com.latam.alura.hotel.model.Reserva;
import com.latam.alura.hotel.model.Valor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ReservaController {

    private static Long idReserva;
    private final ReservaDao reservaDao;
    private Connection con;
    private ValorDao valorDao;

    public ReservaController() {
        this.con = new ConexionFactory().recuperaConexion();
        this.reservaDao = new ReservaDao(this.con);
        this.valorDao = new ValorDao();
    }

    public BigDecimal calcularTotal(Date fecha1, Date fecha2) {
        long resultado = fecha2.getTime() - fecha1.getTime();
        Valor valor = valorDao.obtener();
        float valorReserva = valor.getValor();
        System.out.println(valorReserva);
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
        String valor = calcularTotal(reserva.getFecha_entrante(),reserva.getFecha_salida()).toString();

        reserva.setValor(Double.parseDouble(valor));
        return this.reservaDao.modificar(reserva);
    }

    public int eliminar(int id) {
        return this.reservaDao.eliminar(id);
    }
}
