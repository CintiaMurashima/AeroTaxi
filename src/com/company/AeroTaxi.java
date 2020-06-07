package com.company;

import com.company.aviones.Avion;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class AeroTaxi {

    private ArrayList<Vuelo> vuelos= new ArrayList<>();
    private ArrayList<Avion> aviones= new ArrayList<>();
    private ArrayList<Usuario> usuarios=new ArrayList<>();
    private ArrayList<Ruta> rutas= new ArrayList<>();
    private ArrayList<String> ciudades = new ArrayList<>();



    public AeroTaxi(){
        //creacion de rutas
        Ruta ruta1=new Ruta("Buenos Aires","Cordoba",695);
        Ruta ruta2=new Ruta("Buenos Aires","Santiago",1400);
        Ruta ruta3=new Ruta("Buenos Aires","Montevideo",950);
        Ruta ruta4=new Ruta("Cordoba","Montevideo",1190);
        Ruta ruta5=new Ruta("Cordoba","Santiago",1050);
        Ruta ruta6=new Ruta("Montevideo","Santiago",2100);

        ciudades.add("Buenos Aires");
        ciudades.add("Cordoba");
        ciudades.add("Santiago");
        ciudades.add("Montevideo");

    }

    public Avion seleccionarAvion(Date fecha){
        ArrayList<Avion>avionesOcupados= new ArrayList<>(); // Guardo los aviones que no estan disponibles en la fecha indicada
        ArrayList<Avion>avionesDesocupados= new ArrayList<>();
        boolean encontrado=false;
        int j;
        Scanner teclado = new Scanner(System.in);
        int seleccion;

       for(int i=0; i < vuelos.size(); i++){
           if(vuelos.get(i).getFechaVuelo().equals(fecha)){
               avionesOcupados.add(vuelos.get(i).getAvion());
           }
       }
       for(int i=0; i < aviones.size(); i++){
           j=0;
           encontrado=false;
           while(j < avionesOcupados.size() && !encontrado){
               if(avionesOcupados.get(j).equals(aviones.get(i))){
                   encontrado=true;
               }
               j++;
           }
           if(encontrado){
               avionesDesocupados.add(aviones.get(j));
           }
       }
       System.out.println("Seleccione un avion");
       for(int i=0; i < avionesDesocupados.size();i++){
           System.out.println(i+1 + avionesDesocupados.get(i).getNombre());//
       }
        seleccion = teclado.nextInt();
       return avionesDesocupados.get(seleccion-1);
    }


    public void origenDestino(){
        String ciudadOrigen;
        String ciudadDestino;
        System.out.println("Introduzca el origen");
        ciudadOrigen= seleccionarCiudad();
        System.out.println("Introduzca el destino");
        ciudadDestino= seleccionarCiudad();
    }

    public String seleccionarCiudad() {

        Scanner teclado = new Scanner(System.in);
        int seleccion;
        String ciudad= "";
        for (int i = 0; i < ciudades.size(); i++){
            System.out.println(i+1+"-" + ciudades.get(i));
        }
       seleccion = teclado.nextInt();
        return ciudad;
    }

    public Ruta obtenerRuta(String ciudad1, String ciudad2) {
        Ruta ruta = null;
        int i=0;
        if(!ciudad1.equals(ciudad2)) {
        while (i < rutas.size() && ruta!= null ){
            if (ciudad1.equals(rutas.get(i).getCiudad1()) || ciudad1.equals(rutas.get(i).getCiudad2()) &&
                ciudad2.equals(rutas.get(i).getCiudad1()) || ciudad2.equals(rutas.get(i).getCiudad2()))
            {
                ruta=rutas.get(i);
            }
            i++;
        }}
        return ruta;
    }


    public int acompañantes(){
        Scanner teclado = new Scanner(System.in);
        int acomp=0;
        System.out.println("Introduzca cantidad de acompañantes");
        acomp= teclado.nextInt();
        return acomp;
    }


    public void crearVuelo(){

        ingresarFecha();

    }



    public Date ingresarFecha(){

        System.out.println("Introduzca la fecha con formato dd/mm/yyyy");
        Scanner teclado = new Scanner(System.in);
        String fechaTeclado= teclado.nextLine();

        SimpleDateFormat formatearFecha = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = null; //creo el tipo fecha

        try{
            fecha = formatearFecha.parse(fechaTeclado); // parse convierte string en date
        } catch (Exception e) {
            System.out.println("Fecha invalida");
        }

        return fecha;

    }


}
