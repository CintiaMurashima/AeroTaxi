package com.company.aviones;

import com.company.Propulsion;

public class Gold extends Avion {

    private boolean wifi;

    public Gold(double capacidadCombustible, double costoXkm, int capacidadMaxPax, double velocidadMax, Propulsion tipoPropulsion, boolean wifi) {
        super(capacidadCombustible, costoXkm, capacidadMaxPax, velocidadMax, tipoPropulsion, true);
        this.wifi=wifi;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }


}
