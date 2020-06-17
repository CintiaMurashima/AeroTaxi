package com.company;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import  java.util.Calendar;
import java.util.Scanner;

public class Usuarios {

    private ArrayList<Usuario> usuarios;

    public Usuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuarios() {
    }


    public ArrayList<com.company.Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<com.company.Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public boolean usuarioCreado(String dni) {
        boolean creado = false;
        ArrayList<Usuario> usu=this.usuarios;
        for (Usuario com: usu) {
            creado = com.getDni().equals(dni);
        }
        return creado;
    }

    public Usuario traerUsuarioDNI(String dni) {
        Usuario encontrado=new Usuario();
        boolean creado=usuarioCreado(dni);
        if(creado){
            for (Usuario com: usuarios)
                if (com.getDni().equals(dni)) {
                    encontrado = com;
                }
        }else{
            System.out.println("Ingrese un D.N.I. valido");
        }
        return encontrado;
    }

    public Usuario traerUsuarioId(int pos) {
        Usuario encontrado=new Usuario();
        ArrayList<Usuario> usu=this.usuarios;
        if(usu.get(pos) !=null){
            encontrado= usu.get(pos);
        }else{
            System.out.println("Ingrese un valor valido");
        }
        return encontrado;
    }

    public String mostrarUsuarios(Usuarios usuarios){
        String usus=" ";
        int i=1;
        ArrayList<Usuario> usu=this.usuarios;
        for (Usuario com: usu) {
            usus += i +" "+com.toString() + '\n';
        }
       return usus;
    }

    public Usuario seleccionarUsuario(Usuarios usuarios){
        Usuario seleccinado=new Usuario();
        String usus=mostrarUsuarios(usuarios);
        Scanner teclado= new Scanner(System.in);
        System.out.println("Ingrese un usuario: ");
        System.out.println(usus);
        int pos= teclado.nextInt();
        seleccinado = traerUsuarioId(pos);
        return seleccinado;
    }


}

