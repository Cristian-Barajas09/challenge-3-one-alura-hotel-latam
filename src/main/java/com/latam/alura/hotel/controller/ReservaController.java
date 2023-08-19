package com.latam.alura.hotel.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ReservaController {
    public BigDecimal calcularTotal(Date fecha1, Date fecha2) {
        long resultado = fecha2.getTime() - fecha1.getTime();
        int valorReserva = 15;
        TimeUnit time = TimeUnit.DAYS;

        long resto = time.convert(resultado, TimeUnit.MILLISECONDS);

        return new BigDecimal(resto * valorReserva);



    }
}
