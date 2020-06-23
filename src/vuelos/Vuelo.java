package vuelos;

import rutas.Ruta;
import com.company.aviones.Avion;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Vuelo {

    private Date fechaVuelo;
    private Ruta recorrido;
    private int acompanante;
    private Avion avion;
    private double costo;
    private String dni;

    public Vuelo(Date fechaVuelo, Ruta recorrido, int acompanante, Avion avion , String dni) {
        this.fechaVuelo = fechaVuelo;
        this.recorrido = recorrido;
        this.acompanante = acompanante;
        this.avion = avion;
        this.costo =calcularPrecio();
        this.dni=dni;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    String mostrarFecha(){
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    public double calcularPrecio(){
        double precioVuelo=0;
        precioVuelo=( avion.getCostoXkm() * recorrido.getDistancia())+ ((acompanante + 1 )* 3500) + avion.getTarifaDelTipo();
        return precioVuelo;
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd");

        String fechaFormateada = formatter.format(getFechaVuelo());
        return "Vuelo: " + '\n' +
                "Fecha: " + fechaFormateada +  '\n' +
                "Recorrido: " + recorrido  +'\n' +
                "Acompa"+'\u00f1'+"antes: " + acompanante +'\n' +
                "Avion: " + avion +'\n' +
                "Costo: " + costo +'\n' +
                "DNI del usuario: " + dni ;
    }

    
}
