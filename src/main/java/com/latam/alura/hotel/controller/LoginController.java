package com.latam.alura.hotel.controller;

import com.latam.alura.hotel.dao.UsuarioDao;
import com.latam.alura.hotel.factory.ConexionFactory;
import com.latam.alura.hotel.model.Usuario;




public class LoginController {
    private UsuarioDao usuarioDao;

    public LoginController(){
        this.usuarioDao = new UsuarioDao(new ConexionFactory().recuperaConexion());
    }
    public Usuario obtenerUsuario(String usuario){
        return this.usuarioDao.obtenerUsuario(usuario);
    }


    public void guardar(Usuario usuario1) {

        this.usuarioDao.guardarUsuario(usuario1);

    }

    public Usuario obtenerUsuarioPorId(Long idUsuario) {
        return this.usuarioDao.obtenerUsuarioPorId(idUsuario);
    }

    public int actualizar(Usuario usuario) {
        return this.usuarioDao.actualizar(usuario);
    }
    public int eliminar(Long id){
        return this.usuarioDao.delete(id);
    }
}
