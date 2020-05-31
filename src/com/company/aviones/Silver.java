package com.company.aviones;

import com.company.Propulsion;

public class Silver extends Avion{


    public Silver(double capacidadCombustible, double costoXkm, int capacidadMaxPax, double velocidadMax, Propulsion tipoPropulsion) {
        super(capacidadCombustible, costoXkm, capacidadMaxPax, velocidadMax, tipoPropulsion, true);
    }


}
