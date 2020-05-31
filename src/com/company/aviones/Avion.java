package com.company.aviones;

import com.company.Propulsion;

public class Avion {


    private double capacidadCombustible;
    private double costoXkm; //valor ronda entre los $150 y $300
    private int capacidadMaxPax;
    private double velocidadMax;//
    private Propulsion tipoPropulsion; //motor a reaccion ,helice, pistones
    private boolean catering;


    public Avion(double capacidadCombustible, double costoXkm, int capacidadMaxPax, double velocidadMax, Propulsion tipoPropulsion, boolean catering) {
        this.capacidadCombustible = capacidadCombustible;
        this.costoXkm = costoXkm;
        this.capacidadMaxPax = capacidadMaxPax;
        this.velocidadMax = velocidadMax;
        this.tipoPropulsion = tipoPropulsion;
        this.catering = catering;
    }

    public double getCapacidadCombustible() {
        return capacidadCombustible;
    }

    public void setCapacidadCombustible(double capacidadCombustible) {
        this.capacidadCombustible = capacidadCombustible;
    }

    public double getCostoXkm() {
        return costoXkm;
    }

    public void setCostoXkm(double costoXkm) {
        this.costoXkm = costoXkm;
    }

    public int getCapacidadMaxPax() {
        return capacidadMaxPax;
    }

    public void setCapacidadMaxPax(int capacidadMaxPax) {
        this.capacidadMaxPax = capacidadMaxPax;
    }

    public double getVelocidadMax() {
        return velocidadMax;
    }

    public void setVelocidadMax(double velocidadMax) {
        this.velocidadMax = velocidadMax;
    }

    public Propulsion getTipoPropulsion() {
        return tipoPropulsion;
    }

    public void setTipoPropulsion(Propulsion tipoPropulsion) {
        this.tipoPropulsion = tipoPropulsion;
    }

    public boolean isCatering() {
        return catering;
    }

    public void setCatering(boolean catering) {
        this.catering = catering;
    }


}
