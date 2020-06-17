package com.company;

import javafx.scene.control.ProgressIndicator;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {


    // la declaro startic para llamarla desde aerotaxi
    public static void clearScreen(){
        for(int i=0; i < 80 * 300;i++){
            System.out.println("\b");
        }
    }


    public void mostrarMenuPrincial(AeroTaxi aeroTaxi) {
        Scanner teclado = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario


        while(!salir) {

            System.out.println("1. Iniciar sesion");
            System.out.println("2. Registrarse");
            System.out.println("3. Salir");

            try {

                System.out.println("Escribe una de las opciones");
                opcion = teclado.nextInt();

                switch (opcion) {
                    case 1:

                        clearScreen();
                        System.out.println("Has seleccionado Iniciar sesion");
                        // si esta validado
                        menuCreacionVuelo(aeroTaxi);
                        break;
                    case 2:
                        clearScreen();
                        System.out.println("Has seleccionado Registrarse");

                        break;
                    case 3:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo opcion entre  1 y 3");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                opcion = teclado.nextInt();        }
        }

}

    public void menuCreacionVuelo(AeroTaxi aeroTaxi) {

        Scanner teclado = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario


        while (!salir) {

            System.out.println("1. Reservar vuelo");
            System.out.println("2. Cancelar vuelo");
            System.out.println("3. Volver al menu anterior");
            try {
                System.out.println("Escribe una de las opciones");
                opcion = teclado.nextInt();

                switch (opcion) {
                    case 1:
                        clearScreen();
                        System.out.println("Reservar Vuelo");
                        aeroTaxi.crearVuelo();
                        break;
                    case 2:
                        clearScreen();
                        System.out.println("Cancelar vuelo");
                        break;
                    case 3:
                        salir = true;
                        break;
                    default:

                        System.out.println("Solo opcion entre  1 y 3");
                }
            } catch (InputMismatchException e) {

                System.out.println("Debes insertar un número");
                opcion = teclado.nextInt();
            }
        }
    }

}



