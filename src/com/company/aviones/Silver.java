package com.company.aviones;

public class Silver extends Avion{


    public Silver(String nombre, double capacidadCombustible, double costoXkm, int capacidadMaxPax, double velocidadMax, Propulsion tipoPropulsion, double tarifaDelTipo) {
        super(nombre,capacidadCombustible, costoXkm, capacidadMaxPax, velocidadMax, tipoPropulsion, true,tarifaDelTipo);
    }
    public Silver(){
    }

    @Override
    public String toString() {
        return super.toString() + " , Categoria: Silver";
    }

}
