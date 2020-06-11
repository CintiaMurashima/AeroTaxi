package com.company;

import com.company.aviones.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

public class AeroTaxi {

    private ArrayList<Avion> aviones= new ArrayList<>();
    private ArrayList<Usuario> usuarios=new ArrayList<>();
    private ArrayList<Ruta> rutas = new ArrayList<>();
    private ArrayList<String> ciudades = new ArrayList<>();
    private ArrayList<Vuelo> vuelos = new ArrayList<>();



    public AeroTaxi() {
        Scanner teclado = new Scanner(System.in);
        int seleccion=0;
        Archivos a = new Archivos();
        try {
            leerArchivos();
        } catch (IOException e) {
            ///si no se puede ver el archivo , le pregunto al usuario si los quiere cargar por defecto
            System.out.println("No se pudo cargar la información de los archivos");
            System.out.println("¿Desea volver a generarla?");
            System.out.println("1-Si");
            System.out.println("2-No");
            do {
                try {
                    seleccion = teclado.nextInt();
                } catch (InputMismatchException ex) {
                    System.out.println("Debe seleccionar opcion 1 o 2");
                }
            } while (!(seleccion >=1 && seleccion <= 2));
            if(seleccion == 1){
                try{
                    a.guardarArchivos();// genero los archivos nuevamente
                    leerArchivos();
                }catch(IOException e1){
                    System.out.println("No se pudieron generar los archivos");
                }
            }
        }
    }

    public void leerArchivos() throws IOException{

        ObjectMapper mapper = new ObjectMapper();
        /// Files.readString convierte archivo en String /// Paths.get es la ruta
        String jsonRutas = Files.readString(Paths.get("rutas.json"));
        //
        Rutas listaRutas = mapper.readValue(jsonRutas, Rutas.class);
        ///
        rutas = listaRutas.getRutas();

        String jsonAviones = Files.readString(Paths.get("aviones.json"));
        Aviones listaAviones = mapper.readValue(jsonAviones, Aviones.class);
        aviones = listaAviones.getAviones();

        String jsonCiudades = Files.readString(Paths.get("ciudades.json"));
        Ciudades listaCiudades = mapper.readValue(jsonCiudades, Ciudades.class);
        ciudades = listaCiudades.getCiudades();

        String jsonVuelos = Files.readString(Paths.get("vuelos.json"));
        Vuelos listaVuelos = mapper.readValue(jsonVuelos, Vuelos.class);
        vuelos = listaVuelos.getVuelos();
    }

    public Date ingresarFecha(){

        Scanner teclado = new Scanner(System.in);
        String pattern = "yyyy-MM-dd";
        System.out.println("Introduzca la fecha con formato yyyy-MM-dd");

        SimpleDateFormat formatearFecha = new SimpleDateFormat(pattern);
        Date fechaActual= new Date();
        Date fecha = null; //creo el tipo fecha

        do {
            try {
                if(fecha != null && fecha.before(fechaActual))
                {
                 System.out.println(fechaActual);
                 System.out.println("Debe ingresar una fecha superior a la actual");
                }
                pattern= teclado.nextLine();
                fecha = formatearFecha.parse(pattern); // parse convierte string en date
            } catch (Exception e) {
                System.out.println("Fecha o formato invalido");
                System.out.println("Introduzca la fecha con formato yyyy-MM-dd");
            }
        } while (fecha == null || (fecha.before(fechaActual)));

        return fecha;
    }
    public Ruta obtenerRuta() {
        Ruta ruta = null;
        int i=0;
        String ciudad1="";
        String ciudad2="";

        System.out.println("Introduzca el origen");
        ciudad1= seleccionarCiudad();

        do{
            if (ciudad1.equals(ciudad2)){
                System.out.println("No puede repetir ciudades");
            }
            System.out.println("Introduzca el destino");
            ciudad2= seleccionarCiudad();
        } while (ciudad1.equals(ciudad2));

        /// Logica para encontrar la ruta
        while (i < rutas.size() && ruta == null ){
            if (ciudad1.equals(rutas.get(i).getCiudad1()) || ciudad1.equals(rutas.get(i).getCiudad2()) &&
                    ciudad2.equals(rutas.get(i).getCiudad1()) || ciudad2.equals(rutas.get(i).getCiudad2()))
            {
                ruta=rutas.get(i);
            }
            i++;
        }

        return ruta;
    }

    public String seleccionarCiudad() {

        Scanner teclado = new Scanner(System.in);
        int seleccion=0;
        String ciudad= "";
        for (int i = 0; i < ciudades.size(); i++){
            //le sumo para mostrar a partir de 1 al usuario
            System.out.println(i+1+"-" + ciudades.get(i));
        }
        do {
            try {
                seleccion = teclado.nextInt();
                ciudad=ciudades.get(seleccion-1);

            } catch (InputMismatchException ime){
                System.out.println("El numero ingresado es incorrecto, vuelva a intentar");
                teclado.next();
            }
        } while (seleccion > ciudades.size());


        return ciudad;
    }

    //luego valido los vuelos que hay con la cantidad de acompañantes que ingreso el usu
    public int acompanantes(){
        Scanner teclado = new Scanner(System.in);
        int acomp=0;

        System.out.println("Introduzca cantidad de acompañantes");
        do {
            try {
                acomp = teclado.nextInt();
            } catch (InputMismatchException ime){
                System.out.println("El numero ingresado es incorrecto, vuelva a intentar");
               teclado.next();
            }
        } while (!(acomp >=0));

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
            if(!encontrado){
                avionesDesocupados.add(aviones.get(i));
            }
        }
        return avionesDesocupados;
    }

    public Avion seleccionarAvion(ArrayList<Avion> avionesDisponibles,int acompanantes){
        ///guardo los aviones disponibles c/capacidad de pasajeros
        ArrayList<Avion>avionesValidos=new ArrayList<>();
        Scanner teclado = new Scanner(System.in);
        int seleccion=0;
        Avion avionSeleccionado= null;

       for(int i = 0; i < avionesDisponibles.size(); i++){
           /// sumo 1 a acompañantes para tener en cuenta al usuario tambien
           if(acompanantes + 1< avionesDisponibles.get(i).getCapacidadMaxPax()){
               avionesValidos.add(avionesDisponibles.get(i));
           }
       }
       if(avionesValidos.isEmpty()){
           System.out.println("No tenemos aviones disponibles con esa capacidad de pasajeros");

       }else {
           System.out.println("Seleccione un avion");
           for (int i = 0; i < avionesValidos.size(); i++) {
               System.out.println(i + 1 + avionesValidos.get(i).getNombre());//
           }
           do {
               try {
                   if(seleccion > avionesValidos.size()){
                       System.out.println("El numero ingresado es incorrecto, vuelva a intentar");
                   }
                   seleccion = teclado.nextInt();
               } catch (InputMismatchException ime){
                   System.out.println("Debera ingresar un numero");
                   teclado.next();
               }
           } while (!(seleccion <= avionesValidos.size()) || seleccion > avionesValidos.size());

           avionSeleccionado=avionesValidos.get(seleccion-1);
       }
       return avionSeleccionado;
    }


// LLamo a las funciones anteriores y las uno con la validaciones correspondientes
    public void crearVuelo() {
        Scanner teclado = new Scanner(System.in);
        int seleccion=0;
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

                do {
                    try {
                        seleccion = teclado.nextInt();
                    } catch (InputMismatchException ime){
                        System.out.println("Solo puedes insertar números de las opciones dadas");
                        teclado.next();
                    }
                } while (!(seleccion > 0 && seleccion < 3));

                if(seleccion == 1){
                    try{
                        vuelos.add(vuelo);
                        //guarda en archivos
                        guardarVuelos();
                    } catch(IOException e) {
                        System.out.println("No se pudo guardar el vuelo");
                    }
                }
            }

        }else{
            System.out.println("No hay aviones disponibles para la fecha");
        }
    }
    public void guardarVuelos() throws IOException {
        File archivoVuelos = new File("vuelos.json");
        ObjectMapper mapper = new ObjectMapper();
        Vuelos listaVuelos= new Vuelos(vuelos);
        mapper.writeValue(archivoVuelos,listaVuelos);
    }


}
