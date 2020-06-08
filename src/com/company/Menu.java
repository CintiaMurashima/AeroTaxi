package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu{

    public void mostrarMenuPrincial(){
        Scanner teclado = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario

        while(!salir) {

            System.out.println("1. Iniciar sesion");
            System.out.println("2. Registrarse");
            System.out.println("3. Volver al menu anterior");

            try {

                System.out.println("Escribe una de las opciones");
                opcion = teclado.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.println("Has seleccionado Iniciar sesion");
                        // si esta validado
                        menuCreacionVuelo();

                        break;
                    case 2:
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

    public void menuCreacionVuelo() {

        Scanner teclado = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario

        System.out.println("1. Reservar vuelo");
        System.out.println("2. Cancelar vuelo");
        System.out.println("3. Salir");
        while (!salir) {
            try {

                System.out.println("Escribe una de las opciones");
                opcion = teclado.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.println("Reservar Vuelo");

                        break;
                    case 2:
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



