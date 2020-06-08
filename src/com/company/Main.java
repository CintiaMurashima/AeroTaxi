package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
	AeroTaxi a=new AeroTaxi();
	Menu menu= new Menu();

	menu.mostrarMenuPrincial();

	a.crearVuelo();

    ///Archivos o= new Archivos();


	//a.crearVuelo();
    }
}
