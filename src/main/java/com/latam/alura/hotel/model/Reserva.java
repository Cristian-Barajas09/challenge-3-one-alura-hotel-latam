package com.latam.alura.hotel.model;




import java.sql.Date;


public class Reserva {
    private Long id;
    private Date fecha_entrante;
    private Date fecha_salida;
    private double valor;
    private String forma_pago;

    public Reserva( Date fecha_entrante, Date fecha_salida, double valor, String forma_pago) {

        this.fecha_entrante = fecha_entrante;
        this.fecha_salida = fecha_salida;
        this.valor = valor;
        this.forma_pago = forma_pago;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha_entrante() {
        return fecha_entrante;
    }

    public void setFecha_entrante(Date fecha_entrante) {
        this.fecha_entrante = fecha_entrante;
    }

    public Date getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(Date fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getForma_pago() {
        return forma_pago;
    }

    public void setForma_pago(String forma_pago) {
        this.forma_pago = forma_pago;
    }
}
