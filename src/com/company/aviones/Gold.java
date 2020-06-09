package com.company.aviones;

public class Gold extends Avion {

    private boolean wifi;

    public Gold(String nombre,double capacidadCombustible, double costoXkm, int capacidadMaxPax,
                double velocidadMax, Propulsion tipoPropulsion, boolean wifi, double tarifaDelTipo) {
        super(nombre,capacidadCombustible, costoXkm, capacidadMaxPax, velocidadMax, tipoPropulsion, true,tarifaDelTipo);
        this.wifi=wifi;
    }

    public Gold(){
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }


}
