package com.company;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu{


    // la declaro startic para llamarla desde aerotaxi
    public static void clearScreen(){
        for(int i=0; i < 80 * 300;i++){
            System.out.println("\b");
        }
    }


    public void menuCreacionVuelo(AeroTaxi aeroTaxi) {

        Scanner teclado = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario


        while (!salir) {
            clearScreen();
            System.out.println("\n----- Bienvenido a AeroTaxi -----\n");
            System.out.println("1. Reservar vuelo");
            System.out.println("2. Cancelar vuelo");
            System.out.println("3. Listar vuelos");
            System.out.println("4. Listar usuarios");
            System.out.println("5. Salir");
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
                        aeroTaxi.cancelar_vuelo();

                        break;

                    case 3:
                        clearScreen();
                        System.out.println("Listar vuelos");
                        aeroTaxi.seleccionarMostrarVuelos();

                        break;

                    case 4:
                        clearScreen();
                        System.out.println("Listar usuarios");
                        aeroTaxi.mostrarUsuarios_con_costo();

                        break;
                    case 5:
                        salir=true;
                        break;
                    default:
                        System.out.println("Solo opcion entre  1 y 5");
                }
            } catch (InputMismatchException | InterruptedException e) {

                System.out.println("Debes insertar un número");
                opcion = teclado.nextInt();
            }
        }
    }

}



