package com.company;

import com.company.archivos.Archivos;
import com.company.aviones.*;
import com.company.usuarios.Usuario;
import com.company.usuarios.Usuarios;
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
        Rutas listaRutas = mapper.readValue(jsonRutas, Rutas.class);//convierte el string json en objeto
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
                pattern= teclado.nextLine(); /// Limpia el buffer
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


        do {
            for (int i = 0; i < ciudadesFiltradas.size(); i++){
            //le sumo para mostrar a partir de 1 al usuario
            System.out.println(i+1+"-" + ciudadesFiltradas.get(i));
        }

            try {
                seleccion = teclado.nextInt();

            } catch (InputMismatchException ime){
                System.out.println("El numero ingresado es incorrecto, vuelva a intentar");
                teclado.next();
            }
        } while (seleccion > ciudadesFiltradas.size() || seleccion < 1);

            ciudad=ciudadesFiltradas.get(seleccion-1);


        return ciudad;
    }

    public void listarVuelosFecha(){
        Scanner teclado = new Scanner(System.in);
        String pattern = "dd/MM/yyyy";
        int j=0;
        int i=1;
        Menu.clearScreen();
        System.out.println("Introduzca la fecha con formato dd/MM/yyyy");

        SimpleDateFormat formatearFecha = new SimpleDateFormat(pattern);
        Date fechaActual= new Date();
        Date fecha = null; //creo el tipo fecha

        do {
            try {
                pattern= teclado.nextLine();///limpia teclado
                fecha = formatearFecha.parse(pattern); // parse convierte string en date
            } catch (Exception e) {
                System.out.println("Fecha o formato invalido");
                System.out.println("Introduzca la fecha con formato dd/MM/yyyy");
            }
        } while (fecha == null);
        while (j < vuelos.size()){
            if(vuelos.get(j).getFechaVuelo().equals(fecha)) {
               System.out.println(i +" "+ vuelos.get(j).toString()+'\n');
               i++;
            }
            j++;
        }
        if(i==1){
            System.out.println("No hay vuelos en esta fecha");
        }
        siguiente();
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

    public Avion seleccionarAvion(ArrayList<Avion> avionesDisponibles,int acompanantes) throws InterruptedException {
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
           Thread.sleep(3000) ;///pausa

       }else {
           Menu.clearScreen();
           System.out.println("Seleccione un avion");
           for (int i = 0; i < avionesValidos.size(); i++) {
               System.out.println(i+1 + " "+ avionesValidos.get(i).getNombre());//
           }
           do {
               try {
                   seleccion = teclado.nextInt();
                   if((seleccion <= 0) || seleccion > avionesValidos.size() ) {
                       System.out.println("El numero ingresado es incorrecto, vuelva a intentar");
                   }
               } catch (InputMismatchException ime){
                   System.out.println("Debera ingresar un numero");
                   teclado.next();
               }
           } while ((seleccion <= 0) || seleccion > avionesValidos.size());

           avionSeleccionado=avionesValidos.get(seleccion-1);
       }
       return avionSeleccionado;
    }


// LLamo a las funciones anteriores y las uno con la validaciones correspondientes
    public void crearVuelo() throws InterruptedException {
        Scanner teclado = new Scanner(System.in);
        int seleccion=0;
        Usuario usu=usuarios.seleccionarUsuario();
        System.out.println(usu);
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
                        if(!(seleccion > 0 && seleccion < 3)){
                          System.out.println("Solo puedes insertar números de las opciones dadas");
                        }

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
                        siguiente();

                    } catch(IOException e) {
                        Menu.clearScreen();
                        System.out.println("No se pudo guardar el vuelo");
                        siguiente();
                    }
                }else {
                    System.out.println("El vuelo se cancelo con exito");
                    siguiente();
                }
            }

        }else{
            System.out.println("No hay aviones disponibles para la fecha");
        }
    }

    public boolean validarCancelacion(Date fecha) {
        Date fechaActual = new Date();
        boolean cancel = false;

        if (fechaActual.after(fecha) || fechaActual.equals(fecha)) {
            System.out.println("No se puede cancelar un vuelo con menos de 24hs de anticipacion");
        } else {
            cancel = true;
        }
        return cancel;
    }

    public double calcular_costo(String DNI)
    {
        double total = 0;
        int j = 0;
        if(vuelos.isEmpty()) {
            //Excepcion en caso de que el usuario no tenga vuelos
        }else {
            while (j < vuelos.size())
            {
                if(vuelos.get(j).getDni().equals(DNI)) {
                    total = total + vuelos.get(j).getCosto();
                }
                j++;
            }
        }
        return total;
    }

    public int get_mejor_avion (String DNI)
    {
        int avion = 0; /// 0 = No vuelos, 1 Es igual a Bronze, 2 es igual a Silver, 3 es igual a Gold
        int j = 0;
        if(vuelos.isEmpty()) {
            //Excepcion en caso de que el usuario no tenga vuelos
        }else {
            while (j < vuelos.size())
            {
                if(vuelos.get(j).getDni().equals(DNI)) {
                    if (vuelos.get(j).getAvion() instanceof Bronze) {
                        if(avion < 1){
                            avion = 1;
                        }

                    } else if (vuelos.get(j).getAvion() instanceof Silver) {
                        if(avion < 2){
                            avion = 2;
                        }

                    }else if (vuelos.get(j).getAvion() instanceof Gold) {
                        if(avion < 3){
                            avion = 3;
                        }
                    }
                }
                j++;
            }
        }
        return avion;
    }

    public void mostrarUsuarios_con_costo(){
        double costo = 0;
        int tipo_avion = 0;
        Scanner teclado=new Scanner(System.in);
        int pos=0;
        ArrayList <Usuario> temp = new ArrayList<>();
        temp = usuarios.getUsuarios();
        int i = 0;
        if(vuelos.isEmpty()) {
            System.out.println("No hay Usuarios para Mostrar\n");
        }else {
            usuarios.mostrarUsuarios();
            try {
                pos=teclado.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Solo puede ingresar numeros");
            }
            if(pos > 0 && pos<=temp.size()){
                System.out.println(temp.get(pos-1).toString());
                costo = calcular_costo(temp.get(pos-1).getDni());
                System.out.println("Costo total de los viajes del usuario:  " + costo );
                tipo_avion = get_mejor_avion(temp.get(pos-1).getDni());
                if (tipo_avion == 1) {
                    System.out.println("El mejor Avion en el que ha viajado este Usuario es:  Bronze\n");
                } else if (tipo_avion == 2) {
                    System.out.println("El mejor Avion en el que ha viajado este Usuario es:  Silver\n");
                } else if (tipo_avion == 3) {
                    System.out.println("El mejor Avion en el que ha viajado este Usuario es:  Gold\n");
                } else {
                    System.out.println("Este usuario no tiene vuelos cargados\n");
                }
            }else {
                System.out.println("El usuario no existe\n");
            }
            siguiente();
        }
    }

    public void siguiente(){
        Scanner nextt=new Scanner(System.in);
        String next;
        next=nextt.nextLine();
    }

    public LinkedHashMap<Integer, Vuelo> filtrarVuelos(String dni) {
        LinkedHashMap<Integer, Vuelo> vuelosPorUsuario = new LinkedHashMap<>();
        for (int i=0; i<vuelos.size(); i++) {
            if (vuelos.get(i).getDni().equals(dni)) {
                vuelosPorUsuario.put(i, vuelos.get(i));
            }
        }
        return vuelosPorUsuario;
    }

    public void cancelar_vuelo() {
        Scanner teclado = new Scanner(System.in);
        int seleccion=0;
        int j = 0;
        int indice = 0;
        Usuario usuario=usuarios.seleccionarUsuario();
        // crea un map ordenado con los vuelos del usuario, la key es la posicion en el arreglo principal de vuelos
        // el valor es el vuelo
        LinkedHashMap<Integer, Vuelo> vuelosPorUsuario = filtrarVuelos(usuario.getDni());
        // crea un array de las keys para poder listarlos con un numero que va a ingresar el usuario
        // y relacionarlo con la key. Ej opcion 2 = key 31
        ArrayList<Integer> claves = new ArrayList<>( vuelosPorUsuario.keySet() );
        if(vuelos.isEmpty()) {
            //Excepcion en caso de que el usuario no tenga vuelos
            System.out.println("El Usuario no cuenta con vuelos reservados\n");
        }else {
            System.out.println("Vuelos en los que el Usuario se encuentra registrado: \n");
            //Loop para printear los vuelos
            for (int i=0; i<claves.size(); i++) {
                System.out.println((i+1) + " - " + vuelosPorUsuario.get(claves.get(i)) + "\n");
            }
            System.out.println("\n\nIngrese el Numero de vuelo que desea cancelar. " +
                    "Si no desea cancelar ningun vuelo, seleccione 0\n");
            do {
                try {
                    ///Si el proximo no es un int o es menor a 0, entonces arroja un error
                    seleccion = teclado.nextInt();

                } catch (InputMismatchException ime){
                    System.out.println("Solo puedes insertar números de la lista o 0");
                    teclado.next();
                }
            } while (seleccion < 0 || seleccion > claves.size());
            if(seleccion != 0){
                indice = claves.get(seleccion - 1);
                ///Si la seleccion es correcta y no es 0 en cuyo caso se sale de la funcion, remueve el vuelo y guarda los cambios
                if (validarCancelacion(vuelos.get(indice).getFechaVuelo())) {
                    vuelos.remove(indice);
                    try{
                        //guarda en archivos
                        guardarVuelos();
                        System.out.println("Vuelo cancelado exitosamente");
                    } catch(IOException e) {
                        System.out.println("No se pudo guardar la cancelacion");
                    }
                }
               siguiente();
            }
        }
    }


    public void listarVuelos(){
        int j=0;

        while (j < vuelos.size()){
            System.out.println(j+1 +" "+ vuelos.get(j).toString()+ '\n'+ '\n');
            j++;
        }
        siguiente();
    }

    public void guardarVuelos() throws IOException {
        File archivoVuelos = new File("vuelos.json");
        ObjectMapper mapper = new ObjectMapper();
        Vuelos listaVuelos= new Vuelos(vuelos);
        mapper.writeValue(archivoVuelos,listaVuelos);
    }


    public void seleccionarMostrarVuelos(){
        int opcion=0;
        Menu.clearScreen();
        Scanner teclado= new Scanner(System.in);
        System.out.println("1- Listar todos los vuelos");
        System.out.println("2- Listar vuelos por fecha");

        try{
            System.out.println("Escribe una de las opciones");
            opcion = teclado.nextInt();

            switch (opcion){
                case 1:
                    Menu.clearScreen();
                    listarVuelos();

                    break;
                case 2:
                    Menu.clearScreen();
                    listarVuelosFecha();
                    break;
            }

        } catch (InputMismatchException e) {

            System.out.println("Debes insertar un número");
            opcion = teclado.nextInt();
        }

    }


}
