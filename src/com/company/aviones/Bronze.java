package com.company.aviones;

import com.company.Propulsion;

public class Bronze extends Avion {


    public Bronze(double capacidadCombustible, double costoXkm, int capacidadMaxPax, double velocidadMax, Propulsion tipoPropulsion) {
        super(capacidadCombustible, costoXkm, capacidadMaxPax, velocidadMax, tipoPropulsion, false);
    }


}
