package com.latam.alura.hotel.dao;



import com.latam.alura.hotel.factory.ConexionFactory;
import com.latam.alura.hotel.model.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SessionDao {
    private final Connection con;

    public SessionDao(Connection con) {
        this.con = con;
    }

    public void guardarSession(Session session){
        try(this.con) {
            this.con.setAutoCommit(false);
            PreparedStatement statement = this.con.prepareStatement(
                    "INSERT INTO SESSION (id_usuario) VALUES (?)"
            );
            try(statement){
                statement.setLong(1,session.getIdUsuario());
                statement.execute();

                this.con.commit();
            }
        } catch (SQLException err) {
            throw new RuntimeException();
        }
    }

    public Session obtenerSession(){
        Session session = new Session();
        Connection con = new ConexionFactory().recuperaConexion();
        try(con) {
            con.setAutoCommit(false);
            PreparedStatement statement = con.prepareStatement("SELECT ID_USUARIO,CREATE_AT FROM SESSION");

            try(statement){

                statement.execute();

                ResultSet resultSet = statement.getResultSet();

                while (resultSet.next()){

                    session.setIdUsuario(resultSet.getLong("ID_USUARIO"));

                    session.setCreateSession(resultSet.getDate("CREATE_AT"));


                }

            }


        } catch (SQLException err) {
            throw new RuntimeException();
        }
        return session;
    }

    public int eliminarSession() {
        final Connection con = new ConexionFactory().recuperaConexion();

        try (con) {
            PreparedStatement statement = con.prepareStatement(
                    "DELETE FROM SESSION"
            );
            try(statement) {
                statement.execute();
                return statement.getUpdateCount();
            }


        }catch (SQLException err ){
            throw new RuntimeException();
        }

    }
}
