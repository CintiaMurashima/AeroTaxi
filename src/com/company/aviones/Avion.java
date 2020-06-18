package com.company.aviones;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Objects;

/// Esto aclara en el archivo el subtipo de cada clase
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Bronze.class, name = "Bronze"),

        @JsonSubTypes.Type(value = Gold.class, name = "Gold"),

        @JsonSubTypes.Type(value = Silver.class, name = "Silver") }
)
public abstract class Avion {

    private String nombre;
    private double capacidadCombustible;
    private double costoXkm; //valor ronda entre los $150 y $300
    private int capacidadMaxPax;
    private double velocidadMax;//
    private Propulsion tipoPropulsion; //motor a reaccion ,helice, pistones
    private boolean catering;
    private double tarifaDelTipo;

    public Avion(String nombre,double capacidadCombustible, double costoXkm, int capacidadMaxPax,
                 double velocidadMax, Propulsion tipoPropulsion, boolean catering, double tarifaDelTipo) {
        this.nombre=nombre;
        this.capacidadCombustible = capacidadCombustible;
        this.costoXkm = costoXkm;
        this.capacidadMaxPax = capacidadMaxPax;
        this.velocidadMax = velocidadMax;
        this.tipoPropulsion = tipoPropulsion;
        this.catering = catering;
        this.tarifaDelTipo= tarifaDelTipo;
    }

    public Avion() {
    }


    public double getTarifaDelTipo() {
        return tarifaDelTipo;
    }

    public void setTarifaDelTipo(double tarifaDelTipo) {
        this.tarifaDelTipo = tarifaDelTipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    /// compara los vuelos para mostrar los desocupados con el criterio de nombre
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avion avion = (Avion) o;
        return nombre.equals(avion.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    @Override
    public String toString() {
        return "Avion" +
                "Nombre='" + nombre + '\'' +
                "apacidadMaxPax= " + capacidadMaxPax +
                "Catering= " + catering +
                "TarifaDelTipo= " + tarifaDelTipo;
    }
}
