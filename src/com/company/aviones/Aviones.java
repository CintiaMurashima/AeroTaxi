package com.company.aviones;

import java.util.ArrayList;
import java.util.List;

public class Aviones {
    private ArrayList<Avion> aviones;
    public Aviones() {
    }

    public Aviones(ArrayList<Avion> aviones) {
        this.aviones = aviones;
    }

    public ArrayList<Avion> getAviones() {
        return aviones;
    }

    public void setAviones(ArrayList<Avion> aviones) {
        this.aviones = aviones;
    }
}
