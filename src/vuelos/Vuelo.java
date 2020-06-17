package vuelos;

import rutas.Ruta;
import com.company.aviones.Avion;

import java.util.Date;

public class Vuelo {

    private Date fechaVuelo;
    private Ruta recorrido;
    private int acompanante;
    private Avion avion;
    private double costo;

    public Vuelo(Date fechaVuelo, Ruta recorrido, int acompanante, Avion avion) {
        this.fechaVuelo = fechaVuelo;
        this.recorrido = recorrido;
        this.acompanante = acompanante;
        this.avion = avion;
        this.costo =calcularPrecio();
    }

    public Vuelo() {
    }

    public Date getFechaVuelo() {
        return fechaVuelo;
    }

    public void setFechaVuelo(Date fechaVuelo) {
        this.fechaVuelo = fechaVuelo;
    }

    public Ruta getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(Ruta recorrido) {
        this.recorrido = recorrido;
    }

    public int getAcompanante() {
        return acompanante;
    }

    public void setAcompanante(int acompanante) {
        this.acompanante = acompanante;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double calcularPrecio(){
        double precioVuelo=0;
        precioVuelo=( avion.getCostoXkm() * recorrido.getDistancia())+ ((acompanante + 1 )* 3500) + avion.getTarifaDelTipo();
        return precioVuelo;
    }
    
}
