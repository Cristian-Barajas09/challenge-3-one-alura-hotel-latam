package test.usuario;

import com.latam.alura.hotel.dao.UsuarioDao;
import com.latam.alura.hotel.factory.ConexionFactory;
import com.latam.alura.hotel.model.Usuario;
import com.latam.alura.hotel.utils.ValidatePasswords;

import java.sql.Connection;


public class TestCrearUsuario {
    public static void main(String[] args) {

        Connection con = new ConexionFactory().recuperaConexion();
        String password = ValidatePasswords.encryptPassword("300804");

        Usuario usuarioTest = new Usuario("Cristian","Barajas","CristianB19",password);




        UsuarioDao usuarioDao = new UsuarioDao(con);

        usuarioDao.guardarUsuario(usuarioTest);


    }
}
