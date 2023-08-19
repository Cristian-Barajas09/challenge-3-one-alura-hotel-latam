package com.latam.alura.hotel.model;

import java.sql.Date;


public class Huesped {
    private Long id;
    private String nombre;
    private String apellido;
    private Date f_nacimiento;
    private String nacionalidad;
    private String telefono;

    private Long reservaId;

    public Huesped(String nombre, String apellido, Date f_nacimiento, String nacionalidad, String telefono,Long reservaId) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.f_nacimiento = f_nacimiento;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.reservaId = reservaId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getF_nacimiento() {
        return f_nacimiento;
    }

    public void setF_nacimiento(Date f_nacimiento) {
        this.f_nacimiento = f_nacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Long getReservaId() {
        return reservaId;
    }

    public void setReservaId(Long reservaId) {
        this.reservaId = reservaId;
    }

    @Override
    public String toString(){
        return String.format(
                "{id:%d,nombre:%s,apellido:%s,fecha_nacimiento:%s,nacionalidad:%s,telefono:%s  }",
                this.id,this.nombre,this.apellido,this.f_nacimiento,this.nacionalidad,this.telefono
        );
    }
}
