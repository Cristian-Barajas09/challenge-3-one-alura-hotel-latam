package com.latam.alura.hotel.dao;

import com.latam.alura.hotel.factory.ConexionFactory;
import com.latam.alura.hotel.model.Huesped;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    /*modeloHuesped.addColumn("Número de Huesped");
        modeloHuesped.addColumn("Nombre");
        modeloHuesped.addColumn("Apellido");
        modeloHuesped.addColumn("Fecha de Nacimiento");
        modeloHuesped.addColumn("Nacionalidad");
        modeloHuesped.addColumn("Telefono");
        modeloHuesped.addColumn("Número de Reserva");*/

    public List<Huesped> obtenerHuespedes() {
        List<Huesped> huespedList = new ArrayList<>();
        final Connection con  = new ConexionFactory().recuperaConexion();
        try(con) {
            PreparedStatement statement = con.prepareStatement(
                    "SELECT " +
                            "ID," +
                            "NOMBRE," +
                            "APELLIDO," +
                            "F_NACIMIENTO," +
                            "NACIONALIDAD," +
                            "TELEFONO," +
                            "ID_RESERVA " +
                            "FROM HUESPEDES"
            );
            try(statement) {
                statement.execute();
                ResultSet resultSet = statement.getResultSet();

                while(resultSet.next()){
                    Huesped huesped = new Huesped(

                            resultSet.getString("NOMBRE"),
                            resultSet.getString("APELLIDO"),
                            resultSet.getDate("F_NACIMIENTO"),
                            resultSet.getString("NACIONALIDAD"),
                            resultSet.getString("TELEFONO")
                    );
                    huesped.setId(resultSet.getLong("ID"));
                    huesped.setReservaId(resultSet.getLong("ID_RESERVA"));
                    huespedList.add(huesped);
                }
                return huespedList;
            }


        }catch (SQLException err ) {
            throw new RuntimeException();
        }
    }

    public List<Huesped> obtenerPorApellido(String apellido) {
        List<Huesped> huespedList = new ArrayList<>();
        final Connection con  = new ConexionFactory().recuperaConexion();
        try(con) {
            PreparedStatement statement = con.prepareStatement(
                    "SELECT " +
                            "ID," +
                            "NOMBRE," +
                            "APELLIDO," +
                            "F_NACIMIENTO," +
                            "NACIONALIDAD," +
                            "TELEFONO," +
                            "ID_RESERVA " +
                            "FROM HUESPEDES WHERE APELLIDO = ?"
            );
            try(statement) {
                statement.setString(1,apellido);
                statement.execute();
                ResultSet resultSet = statement.getResultSet();
                while(resultSet.next()){
                    Huesped huesped = new Huesped(

                            resultSet.getString("NOMBRE"),
                            resultSet.getString("APELLIDO"),
                            resultSet.getDate("F_NACIMIENTO"),
                            resultSet.getString("NACIONALIDAD"),
                            resultSet.getString("TELEFONO")
                    );
                    huesped.setId(resultSet.getLong("ID"));
                    huesped.setReservaId(resultSet.getLong("ID_RESERVA"));
                    huespedList.add(huesped);
                }
                return huespedList;
            }


        }catch (SQLException err ) {

            throw new RuntimeException();
        }
    }

    public List<Huesped> obtenerPorReserva(int text) {
        List<Huesped> huespedList = new ArrayList<>();
        final Connection con  = new ConexionFactory().recuperaConexion();
        try(con) {
            PreparedStatement statement = con.prepareStatement(
                    "SELECT " +
                            "ID," +
                            "NOMBRE," +
                            "APELLIDO," +
                            "F_NACIMIENTO," +
                            "NACIONALIDAD," +
                            "TELEFONO," +
                            "ID_RESERVA " +
                            "FROM HUESPEDES WHERE ID_RESERVA = ?"
            );
            try(statement) {
                statement.setInt(1,text);
                statement.execute();
                ResultSet resultSet = statement.getResultSet();
                while(resultSet.next()){
                    Huesped huesped = new Huesped(

                            resultSet.getString("NOMBRE"),
                            resultSet.getString("APELLIDO"),
                            resultSet.getDate("F_NACIMIENTO"),
                            resultSet.getString("NACIONALIDAD"),
                            resultSet.getString("TELEFONO")
                    );
                    huesped.setId(resultSet.getLong("ID"));
                    huesped.setReservaId(resultSet.getLong("ID_RESERVA"));
                    huespedList.add(huesped);
                }
                return huespedList;
            }


        }catch (SQLException err ) {

            throw new RuntimeException();
        }
    }

    public int modificar(Huesped huesped) {
        final Connection con = new ConexionFactory().recuperaConexion();
        try (con) {
            final PreparedStatement statement = con.prepareStatement(
                    "UPDATE HUESPEDES SET " +
                            "NOMBRE=?," +
                            "APELLIDO=?," +
                            "F_NACIMIENTO=?," +
                            "NACIONALIDAD=?," +
                            "TELEFONO=?," +
                            "ID_RESERVA=? " +
                            "  WHERE ID = ?"
            );
            try (statement) {
                statement.setString(1,huesped.getNombre());
                statement.setString(2,huesped.getApellido());
                statement.setDate(3,huesped.getF_nacimiento());
                statement.setString(4,huesped.getNacionalidad());
                statement.setString(5,huesped.getTelefono());
                statement.setLong(6,huesped.getReservaId());
                statement.setLong(7,huesped.getId());

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
            final PreparedStatement statement = con.prepareStatement("DELETE FROM HUESPEDES WHERE ID = ?");
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
