package com.latam.alura.hotel.dao;



import com.latam.alura.hotel.factory.ConexionFactory;
import com.latam.alura.hotel.model.Reserva;

import java.sql.*;

public class ReservaDao {
    private final Connection con;

    public ReservaDao(Connection con){
        this.con = con;
    }
    public Long guardar(Reserva reserva) {
        try(this.con) {
            this.con.setAutoCommit(false);
            final PreparedStatement statement = con.prepareStatement(
                    "INSERT INTO RESERVAS " +
                            "(FECHA_ENTRADA,FECHA_SALIDA,VALOR,FORMA_PAGO)" +
                            " VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS
            );

            try(statement){
                ejecutarRegistro(statement,reserva);
                con.commit();
            }

        } catch (SQLException err){
            err.printStackTrace();
            throw new RuntimeException();
        }
        return reserva.getId();
    }

    private void ejecutarRegistro(PreparedStatement statement, Reserva reserva) throws SQLException {
        statement.setDate(1,reserva.getFecha_entrante());
        statement.setDate(2,reserva.getFecha_salida());
        statement.setDouble(3,reserva.getValor());
        statement.setString(4,reserva.getForma_pago());

        statement.execute();

        final ResultSet resultSet = statement.getGeneratedKeys();
        try(resultSet) {
            while (resultSet.next()) {
                reserva.setId(resultSet.getLong(1));
            }
        }
    }
}
