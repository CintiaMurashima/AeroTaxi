package com.company;

import java.io.File;
import java.util.ArrayList;

public class Vuelos {
    private ArrayList<Vuelo> vuelos;

    public Vuelos(ArrayList<Vuelo> vuelos) {
        this.vuelos = vuelos;
    }

    public Vuelos() {
    }

    public ArrayList<com.company.Vuelo> getVuelos() {
        return vuelos;
    }

    public void setVuelos(ArrayList<com.company.Vuelo> vuelos) {
        this.vuelos = vuelos;
    }
}
