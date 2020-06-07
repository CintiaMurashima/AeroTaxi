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

    public Date ingresarFecha(){

        Scanner teclado = new Scanner(System.in);
        String fechaTeclado;
        System.out.println("Introduzca la fecha con formato dd/mm/yyyy");
        fechaTeclado= teclado.nextLine();

        SimpleDateFormat formatearFecha = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = null; //creo el tipo fecha

        try{
            fecha = formatearFecha.parse(fechaTeclado); // parse convierte string en date
        } catch (Exception e) {
            System.out.println("Fecha invalida");
        }
        return fecha;

    }
    public Ruta obtenerRuta() {
        Ruta ruta = null;
        int i=0;
        String ciudad1;
        String ciudad2;
        System.out.println("Introduzca el origen");
        ciudad1= seleccionarCiudad();
        System.out.println("Introduzca el destino");
        ciudad2= seleccionarCiudad();
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

    public String seleccionarCiudad() {

        Scanner teclado = new Scanner(System.in);
        int seleccion;
        String ciudad= "";
        for (int i = 0; i < ciudades.size(); i++){
            //le sumo para mostrar a partir de 1 al usuario
            System.out.println(i+1+"-" + ciudades.get(i));
        }
        seleccion = teclado.nextInt();
        ciudad=ciudades.get(seleccion-1);
        return ciudad;
    }

    //luego valido los vuelos que hay con la cantidad de acompañantes que ingreso el usu
    public int acompanantes(){
        Scanner teclado = new Scanner(System.in);
        int acomp=0;
        System.out.println("Introduzca cantidad de acompañantes");
        acomp= teclado.nextInt();
        return acomp;
    }

    public ArrayList<Avion>dispoAvion(Date fecha){
        ArrayList<Avion>avionesOcupados= new ArrayList<>(); // Guardo los aviones que no estan disponibles en la fecha indicada
        ArrayList<Avion>avionesDesocupados= new ArrayList<>(); // ocupacion en relacion a la fecha
        boolean encontrado;
        int j;

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
        return avionesDesocupados;
    }

    public Avion seleccionarAvion(ArrayList<Avion> avionesDisponibles,int acompanantes){
        ///guardo los aviones disponibles c/capacidad de pasajeros
        ArrayList<Avion>avionesValidos=new ArrayList<>();
        Scanner teclado = new Scanner(System.in);
        int seleccion;
        Avion avionSeleccionado= null;

       for(int i = 0; i < avionesDisponibles.size(); i++){
           /// sumo 1 a acompañantes para tener en cuenta al usuario tambien
           if(acompanantes + 1< avionesDisponibles.get(i).getCapacidadMaxPax()){
               avionesValidos.add(avionesDisponibles.get(i));
           }
       }
       if(avionesValidos.isEmpty()){
           System.out.println("“No tenemos aviones disponibles con esa capacidad de pasajeros”.");
       }else {
           System.out.println("Seleccione un avion");
           for (int i = 0; i < avionesValidos.size(); i++) {
               System.out.println(i + 1 + avionesValidos.get(i).getNombre());//
           }
           seleccion = teclado.nextInt();
           avionSeleccionado=avionesValidos.get(seleccion-1);
       }
       return avionSeleccionado;
    }


// LLamo a las funciones anteriores y las uno con la validaciones correspondientes
    public void crearVuelo(){
        Scanner teclado = new Scanner(System.in);
        int seleccion;
        Date fecha=ingresarFecha();
        Ruta ruta=obtenerRuta();
        int acompanantes= acompanantes();
        ArrayList<Avion> avionesDisponibles= dispoAvion(fecha);
        Avion avionSeleccionado;
        Vuelo vuelo;

        if(!avionesDisponibles.isEmpty()){
            avionSeleccionado= seleccionarAvion(avionesDisponibles,acompanantes);
            if(avionSeleccionado!=null){
                vuelo=new Vuelo(fecha,ruta,acompanantes,avionSeleccionado);
                System.out.println("La tarifa del vuelo es:" + vuelo.getCosto());
                System.out.println("1- Si desea confirmar");
                System.out.println("2- Si desea cancelar");
                seleccion=teclado.nextInt();
                if(seleccion == 1){
                    vuelos.add(vuelo);
                }
            }

        }else{
            System.out.println("No hay aviones disponibles para la fecha");
        }


    }





}
