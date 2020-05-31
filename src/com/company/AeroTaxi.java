package com.company;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class AeroTaxi {

    ArrayList<Vuelo> vuelos= new ArrayList<>();
    ArrayList<Usuario> usuarios=new ArrayList<>();
    ArrayList<Ruta> rutas= new ArrayList<>();



    public void origenDestino(){

        Scanner teclado = new Scanner(System.in);
        String ciudadOrigen;
        String ciudadDestino;

        System.out.println("Introduzca el origen");
        ciudadOrigen= teclado.nextLine();
        System.out.println("Introduzca el destino");
        ciudadDestino= teclado.nextLine();

        // FALTA CONTENIDO 

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
