package com.latam.alura.hotel.dao;

import com.latam.alura.hotel.factory.ConexionFactory;
import com.latam.alura.hotel.model.Usuario;

import java.sql.*;


public class UsuarioDao {
    private final Connection con;

    public UsuarioDao(Connection con) {
        this.con = con;
    }

   public Usuario obtenerUsuario(String usuario){
       Usuario user = new Usuario();
       Connection con = new ConexionFactory().recuperaConexion();

       try(con){
           final PreparedStatement statement = con.prepareStatement(
                   "SELECT ID,NOMBRE,APELLIDO,USUARIO,PASSWORD FROM USERS WHERE usuario = ?"
           );
           try(statement){
               statement.setString(1,usuario);
               statement.execute();

               ResultSet resultSet = statement.getResultSet();

               while (resultSet.next()){
                    user.setId(resultSet.getLong("ID"));
                    user.setNombre(resultSet.getString("NOMBRE"));
                    user.setApellido(resultSet.getString("APELLIDO"));
                    user.setUsuario(resultSet.getString("USUARIO"));
                    user.setPassword(resultSet.getString("PASSWORD"));
               }


           }
       }catch (SQLException e){
           e.printStackTrace();
           throw new RuntimeException();
       }

       return user;
   }

    public void guardarUsuario(Usuario user){

       try(this.con) {
           this.con.setAutoCommit(false);
           final PreparedStatement statement = this.con.prepareStatement(
                   "INSERT INTO USERS (NOMBRE,APELLIDO,USUARIO,PASSWORD) VALUES (?,?,?,?)",
                   Statement.RETURN_GENERATED_KEYS
           );



           try(statement) {
               this.ejecutarRegistro(statement,user);
               con.commit();
           }
       }catch (SQLException err) {
           err.printStackTrace();
           throw new RuntimeException();
       }

    }

    private void ejecutarRegistro(PreparedStatement statement, Usuario user) throws SQLException {
        statement.setString(1,user.getNombre());
        statement.setString(2,user.getApellido());
        statement.setString(3,user.getUsuario());
        statement.setString(4,user.getPassword());

        statement.execute();

        final ResultSet resultSet = statement.getGeneratedKeys();

        try(resultSet){
            while (resultSet.next()){
                user.setId(resultSet.getLong(1));
                System.out.printf("el usuario del id %s fue insertado exitosamente%n",user);
            }
        }

    }

}
