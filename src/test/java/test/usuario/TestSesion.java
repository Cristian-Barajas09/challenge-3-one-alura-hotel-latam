package test.usuario;

import com.latam.alura.hotel.controller.SessionController;
import com.latam.alura.hotel.dao.UsuarioDao;
import com.latam.alura.hotel.factory.ConexionFactory;
import com.latam.alura.hotel.model.Session;
import com.latam.alura.hotel.model.Usuario;
import com.latam.alura.hotel.utils.ValidatePasswords;

import java.sql.Connection;


public class TestSesion {
    public static void main(String[] args) {
        crearSesion();
    }

    public static void eliminarSesion(){
        SessionController sessionController = new SessionController();
        sessionController.eliminarSesion();
    }

    public static void crearSesion(){
        Session session = new Session(1L);

        SessionController sessionController = new SessionController();
        sessionController.guardarSession(session);

        Session session1 =  sessionController.obtenerSession();

        System.out.println(session1.getIdUsuario());
    }
    public static void crearUsuario(){
        Connection con = new ConexionFactory().recuperaConexion();
        UsuarioDao usuarioDao = new UsuarioDao(con);

        String password = ValidatePasswords.encryptPassword("031182");
        Usuario usuario = new Usuario("Juan","Barajas","JBarajas",password);

        usuarioDao.guardarUsuario(usuario);
        Usuario usuario1 = usuarioDao.obtenerUsuario("JBarajas");
    }
}
