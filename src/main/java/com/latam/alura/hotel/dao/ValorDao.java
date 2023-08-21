package com.latam.alura.hotel.dao;

import com.latam.alura.hotel.factory.ConexionFactory;
import com.latam.alura.hotel.model.Valor;

import java.sql.*;

public class ValorDao {


    public void guardar(Valor valor) {
        Connection con = new ConexionFactory().recuperaConexion();
        try(con) {
            con.setAutoCommit(false);
            final PreparedStatement statement = con.prepareStatement(
                    "INSERT INTO PRECIO_RESERVAS (precio) VALUES (?)",
                    Statement.RETURN_GENERATED_KEYS
            );



            try(statement) {
                this.ejecutarRegistro(statement,valor);
                con.commit();
            }
        }catch (SQLException err) {

            throw new RuntimeException(err);
        }
    }
    private void ejecutarRegistro(PreparedStatement statement,Valor valor) throws SQLException {
        statement.setFloat(1,valor.getValor());
        statement.execute();

        final ResultSet resultSet =  statement.getGeneratedKeys();

        try(resultSet){
            while (resultSet.next()) {
                valor.setId(resultSet.getLong(1));

            }
        }


    }

    public Valor obtener(){
        Valor valor = new Valor();
        Connection con = new ConexionFactory().recuperaConexion();
        try(con) {

            final PreparedStatement statement = con.prepareStatement(
                    "SELECT ID,PRECIO FROM PRECIO_RESERVAS ORDER BY ID DESC LIMIT 1"
            );



            try(statement) {
                statement.execute();

                ResultSet resultSet = statement.getResultSet();

                try(resultSet){
                    while (resultSet.next()) {
                        valor.setId(resultSet.getLong("ID"));
                        valor.setValor(resultSet.getFloat("PRECIO"));
                    }
                }
                return valor;
            }
        }catch (SQLException err) {

            throw new RuntimeException(err);
        }
    }



}
