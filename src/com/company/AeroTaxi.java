package com.company;

import com.company.archivos.Archivos;
import com.company.aviones.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import rutas.Ciudades;
import rutas.Ruta;
import rutas.Rutas;
import vuelos.Vuelo;
import vuelos.Vuelos;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

public class AeroTaxi {

    private ArrayList<Avion> aviones= new ArrayList<>();
    private Usuarios usuarios=new Usuarios();
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
            Menu.clearScreen();
            System.out.println("No se pudo cargar la información de los com.company.archivos");
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
                    a.guardarArchivos();// genero los com.company.archivos nuevamente
                    leerArchivos();
                }catch(IOException e1){
                    System.out.println("No se pudieron generar los com.company.archivos");
                }
            }
        }
    }

    public void leerArchivos() throws IOException{


        ObjectMapper mapper = new ObjectMapper();
        /// Files.readString convierte archivo en String /// Paths.get es la ruta
        String jsonRutas = Files.readString(Paths.get("rutas.json"));
        //convierte el string json en objeto
        Rutas listaRutas = mapper.readValue(jsonRutas, Rutas.class);

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
        /// se crea para leer el archivo
        String jsonUsuarios = Files.readString(Paths.get("usuarios.json"));
        Usuarios listaUsuarios = mapper.readValue(jsonUsuarios, Usuarios.class);
        usuarios = listaUsuarios;
    }

    public Date ingresarFecha(){

        Scanner teclado = new Scanner(System.in);
        String pattern = "dd/MM/yyyy";
        Menu.clearScreen();
        System.out.println("Introduzca la fecha con formato dd/MM/yyyy");

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
                System.out.println("Introduzca la fecha con formato dd/MM/yyyy");
            }
        } while (fecha == null || (fecha.before(fechaActual)));

        return fecha;
    }
    public Ruta obtenerRuta() {
        Ruta ruta = null;
        int i=0;
        String ciudad1="";
        String ciudad2="";

        Menu.clearScreen();
        System.out.println("Introduzca el origen");
        ciudad1= seleccionarCiudad(null);

        do{
            if (ciudad1.equals(ciudad2)){
                System.out.println("No puede repetir ciudades");
            }
            System.out.println("Introduzca el destino");
            ciudad2= seleccionarCiudad(ciudad1);
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

    public String seleccionarCiudad(String filtroCiudad) {

        Scanner teclado = new Scanner(System.in);
        int seleccion=0;
        String ciudad= "";
        ArrayList<String> ciudadesFiltradas= new ArrayList<>();
        if(filtroCiudad == null){
            ciudadesFiltradas=ciudades;
        }else{

            for (int i=0; i < ciudades.size(); i++) {
                if ((ciudades.get(i) != (filtroCiudad))) {
                    ciudadesFiltradas.add(ciudades.get(i));
                }
            }
        }

        for (int i = 0; i < ciudadesFiltradas.size(); i++){
            //le sumo para mostrar a partir de 1 al usuario
            System.out.println(i+1+"-" + ciudadesFiltradas.get(i));
        }
        do {
            try {
                seleccion = teclado.nextInt();
                ciudad=ciudadesFiltradas.get(seleccion-1);

            } catch (InputMismatchException ime){
                System.out.println("El numero ingresado es incorrecto, vuelva a intentar");
                teclado.next();
            }
        } while (seleccion > ciudadesFiltradas.size());


        return ciudad;
    }

    //luego valido los vuelos que hay con la cantidad de acompañantes que ingreso el usu
    public int acompanantes(){
        Scanner teclado = new Scanner(System.in);
        int acomp=0;
        Menu.clearScreen();
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
        // Guardo los aviones que no estan disponibles en la fecha indicada
        ArrayList<Avion>avionesOcupados= new ArrayList<>();
        // ocupacion en relacion a la fecha
        ArrayList<Avion>avionesDesocupados= new ArrayList<>();
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

    public Avion    seleccionarAvion(ArrayList<Avion> avionesDisponibles,int acompanantes){
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
           Menu.clearScreen();
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
        Usuario usu=usuarios.seleccionarUsuario(this.usuarios);
        Date fecha= ingresarFecha();
        Ruta ruta=obtenerRuta();
        int acompanantes= acompanantes();
        ArrayList<Avion> avionesDisponibles= dispoAvion(fecha);
        Avion avionSeleccionado;
        Vuelo vuelo;
        if(!avionesDisponibles.isEmpty()){
            avionSeleccionado= seleccionarAvion(avionesDisponibles,acompanantes);
            if(avionSeleccionado!=null){

                Menu.clearScreen();
                vuelo=new Vuelo(fecha,ruta,acompanantes,avionSeleccionado,usu.getDni());

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
                        //guarda en com.company.archivos
                        guardarVuelos();
                        Menu.clearScreen();
                        System.out.println("El vuelo se guardo con exito");
                    } catch(IOException e) {
                        Menu.clearScreen();
                        System.out.println("No se pudo guardar el vuelo");
                    }
                }else {
                    System.out.println("El vuelo se cancelo con exito");
                }
            }

        }else{
            System.out.println("No hay aviones disponibles para la fecha");
        }
    }

    public Date sumarUnDia(Date fecha){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_YEAR,1);
        return calendar.getTime();
    }

    public boolean validarCancelacion(Date fecha){
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

    public void cancelar_vuelo() {
        Scanner teclado = new Scanner(System.in);
        int seleccion=0;
        int j = 0;
        if(vuelos.isEmpty()) {
            //Excepcion en caso de que el usuario no tenga vuelos
            System.out.println("El Usuario no cuenta con vuelos reservados\n");
        }else {
            System.out.println("Vuelos en los que el Usuario se encuentra registrado: \n");
            //Loop para printear los vuelos
            while (j < vuelos.size())
            {
                System.out.println(j + ") Fecha: " + vuelos.get(j).getFechaVuelo()
                        + ", Tipo de Avion: " + vuelos.get(j).getAvion()
                        + ", Costo del Vuelo: " + vuelos.get(j).getCosto()
                        + ", Recorrido: " + vuelos.get(j).getRecorrido()
                        + ", Acompañantes: " + vuelos.get(j).getAcompanante() + "\n");
                j++;
            }
            System.out.println("Ingrese el Numero de vuelo que desea cancelar. " +
                    "Si no desea cancelar ningun vuelo, seleccione 0\n");
            do {
                try {
                    ///Si el proximo no es un int o es menor a 0, entonces arroja un error
                    seleccion = teclado.nextInt();
                } catch (InputMismatchException ime){
                    System.out.println("Solo puedes insertar números de la lista o 0");
                    teclado.next();
                }
            } while (!(-1 < seleccion && seleccion < j));
            if(seleccion != 0){
                ///Si la seleccion es correcta y no es 0 en cuyo caso se sale de la funcion), remueve el vuelo y guarda los cambios
                if (validarCancelacion(vuelos.get(seleccion).getFechaVuelo())) {
                        vuelos.remove(seleccion);}
                }
            try{
                //guarda en archivos
                guardarVuelos();
                System.out.println("Vuelo cancelado exitosamente");
            } catch(IOException e) {
                System.out.println("No se pudo guardar la cancelacion");
            }
        }
    }

    public void guardarVuelos() throws IOException {
        File archivoVuelos = new File("vuelos.json");
        ObjectMapper mapper = new ObjectMapper();
        Vuelos listaVuelos= new Vuelos(vuelos);
        mapper.writeValue(archivoVuelos,listaVuelos);
    }

        public String mostrarUsuVuelo(Usuario usu ,Vuelos todos){
        String cadena="";
        cadena = usu.toString();
        cadena += todos.mostrarLosVuelosUsu(usu.getDni());
        return cadena;
    }



}
