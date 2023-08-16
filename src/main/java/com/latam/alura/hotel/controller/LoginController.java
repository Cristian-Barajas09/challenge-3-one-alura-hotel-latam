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

    public void setSession(Usuario user){
        this.usuarioDao.guardarSession(user);
    }
}
