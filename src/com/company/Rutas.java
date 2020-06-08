package com.company;

import com.company.aviones.Avion;

import java.util.ArrayList;

public class Rutas {

    private ArrayList<Ruta> Rutas;
    public Rutas() {
    }

    public Rutas(ArrayList<Ruta> rutas) {
        Rutas = rutas;
    }

    public ArrayList<Ruta> getRutas() {
        return Rutas;
    }

    public void setRutas(ArrayList<Ruta> rutas) {
        Rutas = rutas;
    }
}
