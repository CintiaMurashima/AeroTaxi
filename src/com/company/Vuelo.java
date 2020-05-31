package com.company;

import com.company.aviones.Avion;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Vuelo {

    private Date fechaVuelo;
    private Ruta recorrido;
    private int acompañante;
    private Avion avion;
    private double costo;

    public Vuelo(Date fechaVuelo, Ruta recorrido, int acompañante, Avion avion, double costo) {
        this.fechaVuelo = fechaVuelo;
        this.recorrido = recorrido;
        this.acompañante = acompañante;
        this.avion = avion;
        this.costo = costo;
    }

    public Date getFechaVuelo() {
        return fechaVuelo;
    }

    public void setFechaVuelo(Date fechaVuelo) {
        this.fechaVuelo = fechaVuelo;
    }

    public Ruta getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(Ruta recorrido) {
        this.recorrido = recorrido;
    }

    public int getAcompañante() {
        return acompañante;
    }

    public void setAcompañante(int acompañante) {
        this.acompañante = acompañante;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
}
