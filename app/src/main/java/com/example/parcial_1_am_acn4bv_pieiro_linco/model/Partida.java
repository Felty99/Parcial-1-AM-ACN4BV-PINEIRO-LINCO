package com.example.parcial_1_am_acn4bv_pieiro_linco.model;

import java.sql.Date;
public class Partida {
    private String apodo;
    private String fecha;
    private long correctas;

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public long getCorrectas() {
        return correctas;
    }

    public void setCorrectas(long correctas) {
        this.correctas = correctas;
    }
}
