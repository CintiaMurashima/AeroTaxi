package com.company.aviones;

public class Silver extends Avion{


    public Silver(String nombre, double capacidadCombustible, double costoXkm, int capacidadMaxPax, double velocidadMax, Propulsion tipoPropulsion) {
        super(nombre,capacidadCombustible, costoXkm, capacidadMaxPax, velocidadMax, tipoPropulsion, true,4000);
    }


}
