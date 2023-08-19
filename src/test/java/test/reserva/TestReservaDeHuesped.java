package test.reserva;

import com.latam.alura.hotel.Enums.FormPago;
import com.latam.alura.hotel.dao.HuespedDao;
import com.latam.alura.hotel.dao.ReservaDao;
import com.latam.alura.hotel.factory.ConexionFactory;
import com.latam.alura.hotel.model.Huesped;
import com.latam.alura.hotel.model.Reserva;

import java.sql.Connection;
import java.sql.Date;

public class TestReservaDeHuesped {
    public static void main(String[] args) {
        Connection con = new ConexionFactory().recuperaConexion();
        HuespedDao huespedDao = new HuespedDao(con);
        ReservaDao reservaDao = new ReservaDao(con);


        Reserva reserva = new Reserva(
                Date.valueOf("2023-08-30"),
                Date.valueOf("2023-09-5"),
                10,
                FormPago.DINERO_EN_EFECTIVO
        );

        Long result =  reservaDao.guardar(reserva);

        Huesped huesped = new Huesped(
                "Shiara",
                "Barajas",
                Date.valueOf("2019-05-09"),
                "Venezolana",
                "01234567890",
                result
        );

        huespedDao.guardarHuesped(huesped);





    }
}
