package com.latam.alura.hotel.model;

public class Valor {
    private float valor;
    private Long id;

    public Valor(){}
    public Valor(float valor) {
        this.valor = valor;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
