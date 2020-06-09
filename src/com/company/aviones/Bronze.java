package com.company.aviones;

public class Bronze extends Avion {

    public Bronze(String nombre, double capacidadCombustible, double costoXkm, int capacidadMaxPax,
                  double velocidadMax, Propulsion tipoPropulsion, double tarifaDelTipo) {
        super(nombre, capacidadCombustible, costoXkm, capacidadMaxPax, velocidadMax, tipoPropulsion, false,tarifaDelTipo);
    }

    public Bronze() {
        super();
    }





}
