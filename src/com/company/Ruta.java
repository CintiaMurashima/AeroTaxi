package com.company;

public class Ruta {

    private String ciudad1;
    private String ciudad2;
    private int distancia;

    public Ruta(String ciudad1, String ciudad2, int distancia) {
        this.ciudad1 = ciudad1;
        this.ciudad2 = ciudad2;
        this.distancia = distancia;
    }


    public String getCiudad1() {
        return ciudad1;
    }

    public void setCiudad1(String ciudad1) {
        this.ciudad1 = ciudad1;
    }

    public String getCiudad2() {
        return ciudad2;
    }

    public void setCiudad2(String ciudad2) {
        this.ciudad2 = ciudad2;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }
}
