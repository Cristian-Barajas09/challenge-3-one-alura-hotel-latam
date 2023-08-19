package com.latam.alura.hotel.dao;

import com.latam.alura.hotel.factory.ConexionFactory;
import com.latam.alura.hotel.model.Huesped;

import java.sql.*;

public class HuespedDao {
    private final Connection con;

    public HuespedDao(Connection con){
        this.con = con;
    }

    public void guardarHuesped(Huesped huesped){
        final Connection con = new ConexionFactory().recuperaConexion();
        try(con){
            con.setAutoCommit(false);
            final PreparedStatement statement = con.prepareStatement(
                    "INSERT INTO HUESPEDES" +
                            "(NOMBRE,APELLIDO,F_NACIMIENTO,NACIONALIDAD,TELEFONO,ID_RESERVA) VALUES " +
                            "(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS
            );
            try(statement){
                this.ejecutarRegistro(statement,huesped);
                con.commit();
            }
        } catch (SQLException err){
            err.printStackTrace();
            throw new RuntimeException();
        }
    }

    private void ejecutarRegistro(PreparedStatement statement, Huesped huesped) throws SQLException {

        statement.setString(1, huesped.getNombre());
        statement.setString(2, huesped.getApellido());
        statement.setDate(3, huesped.getF_nacimiento());
        statement.setString(4,huesped.getNacionalidad());
        statement.setString(5,huesped.getTelefono());
        statement.setLong(6,huesped.getReservaId());

        statement.execute();

        ResultSet resultSet = statement.getGeneratedKeys();

        try(resultSet) {
            while (resultSet.next()) {
                huesped.setId(resultSet.getLong(1));
                System.out.printf("el usuario del id %s fue insertado exitosamente%n",huesped);
            }
        }

    }


}
