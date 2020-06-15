package com.company;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import  java.util.Calendar;
public class Vuelos {

    private ArrayList<Vuelo> vuelos;
    private ArrayList<Usuario> usuarios;

    public Vuelos(ArrayList<Vuelo> vuelos) {
        this.vuelos = vuelos;
    }

    public Vuelos() {
    }

    public ArrayList<com.company.Vuelo> getVuelos() {
        return vuelos;
    }

    public void setVuelos(ArrayList<com.company.Vuelo> vuelos) {
        this.vuelos = vuelos;
    }


    public Vuelo traerVuelo(Date fecha,ArrayList<Vuelo> vuelos) {
        Vuelo traido = null;
        String vuelo=" ";
        for (Vuelo nuevo : vuelos) {
            if (nuevo.getFechaVuelo() == fecha) {
                traido=nuevo;
            }
        }
        return traido;
    }

    public String mostrarVuelosFecha(Date fecha,ArrayList<Vuelo> vuelos) {
        Vuelo traido = null;
        String vuelo=" ";

        for (Vuelo nuevo : vuelos) {
            if (nuevo.getFechaVuelo() == fecha) {
                vuelo +=nuevo.toString()+" " + '\n';
            }
        }
        return vuelo;
    }


    public ArrayList<Vuelo> traerVuelosUsu(String dni) {
        ArrayList<Vuelo> viajes = getVuelos();
        ArrayList<Vuelo> listado = new ArrayList<>();
        Vuelo traido = null;
        for (Vuelo nuevo : viajes) {
            if (nuevo.getDni() == dni) {
                listado.add(nuevo);
            }
        }
        return  listado;
    }

    public Date sumarUnDia(Date fecha){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_YEAR,1);
        return calendar.getTime();
    }

    public boolean validarCancelacion(Date fecha,Ruta recorrido){
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat formatearFecha = new SimpleDateFormat(pattern);
        Date fechaActual= new Date();
        Date fechaTraida=sumarUnDia(fecha);
        boolean cancel=false;

        if(fechaActual.compareTo(fechaTraida)==0){
            System.out.println("No se puede cancelar un vuelo con menos de 24hs de anticipacion");

        }else if(fechaActual.compareTo(fechaTraida)<0){
            cancel=true;
        }
        return cancel;
    }

    public String mostrarLosVuelosUsu(String dni){
        ArrayList<Vuelo> usuVuelos=traerVuelosUsu(dni);
        String vuelos="";
        for (Vuelo uno: usuVuelos) {
            vuelos+= uno.toString() + '\n';
        }
        return vuelos;
    }

    public double totalgastos(Vuelos usu){
        double total=0;
        for (Vuelo com: usu.getVuelos()) {
            total += com.calcularPrecio();
        }
        return total;
    }

}
