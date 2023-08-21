package com.latam.alura.hotel.dao;



import com.latam.alura.hotel.factory.ConexionFactory;
import com.latam.alura.hotel.model.Reserva;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

            throw new RuntimeException(err);
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
/*modelo.addColumn("Numero de Reserva");
        modelo.addColumn("Fecha Check In");
        modelo.addColumn("Fecha Check Out");
        modelo.addColumn("Valor");
        modelo.addColumn("Forma de Pago");*/
    public List<Reserva> obtenerReservas() {
        List<Reserva> reservaList = new ArrayList<>();
        final Connection con = new ConexionFactory().recuperaConexion();
        try(con){
            final PreparedStatement statement = con.prepareStatement(
                    "SELECT ID," +
                            "FECHA_ENTRADA," +
                            "FECHA_SALIDA," +
                            "VALOR," +
                            "FORMA_PAGO FROM RESERVAS");
            try(statement) {
                statement.execute();
                ResultSet resultSet = statement.getResultSet();

                while (resultSet.next()){
                    Reserva reserva = new Reserva(
                            resultSet.getDate("FECHA_ENTRADA"),
                            resultSet.getDate("FECHA_SALIDA"),
                            resultSet.getDouble("VALOR"),
                            resultSet.getString("FORMA_PAGO")
                    );
                    reserva.setId(resultSet.getLong("ID"));

                    reservaList.add(reserva);

                }

                return reservaList;

            }
        } catch (SQLException err) {
            throw new RuntimeException(err);
        }


    }

    public List<Reserva> obtenerReservaPorNumero(int numero) {
        List<Reserva> reservaList = new ArrayList<>();
        final Connection con = new ConexionFactory().recuperaConexion();
        try(con){
            final PreparedStatement statement = con.prepareStatement(
                    "SELECT ID," +
                            "FECHA_ENTRADA," +
                            "FECHA_SALIDA," +
                            "VALOR," +
                            "FORMA_PAGO FROM RESERVAS WHERE ID = ?");
            try(statement) {
                statement.setInt(1,numero);
                statement.execute();
                ResultSet resultSet = statement.getResultSet();

                while (resultSet.next()){
                    Reserva reserva = new Reserva(
                            resultSet.getDate("FECHA_ENTRADA"),
                            resultSet.getDate("FECHA_SALIDA"),
                            resultSet.getDouble("VALOR"),
                            resultSet.getString("FORMA_PAGO")
                    );
                    reserva.setId(resultSet.getLong("ID"));

                    reservaList.add(reserva);
                }

                return reservaList;

            }
        } catch (SQLException err) {
            throw new RuntimeException(err);
        }
    }

    public int modificar(Reserva reserva) {
        final Connection con = new ConexionFactory().recuperaConexion();
        try (con) {
            final PreparedStatement statement = con.prepareStatement(
                    "UPDATE RESERVAS SET FECHA_ENTRADA=?," +
                            "FECHA_SALIDA=?," +
                            "VALOR=?," +
                            "FORMA_PAGO=? WHERE ID = ?"
            );
            try (statement) {
                statement.setDate(1,reserva.getFecha_entrante());
                statement.setDate(2,reserva.getFecha_salida());
                statement.setDouble(3,reserva.getValor());
                statement.setString(4,reserva.getForma_pago());
                statement.setLong(5,reserva.getId());
                statement.execute();

                return statement.getUpdateCount();
            }
        } catch (SQLException err) {
            throw new RuntimeException(err);
        }
    }

    public int eliminar(int id) {
        Connection con = new ConexionFactory().recuperaConexion();
        try(con) {
            final PreparedStatement statement = con.prepareStatement("DELETE FROM RESERVAS WHERE ID = ?");
            try(statement) {
                statement.setInt(1, id);
                statement.execute();
                return statement.getUpdateCount();
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
