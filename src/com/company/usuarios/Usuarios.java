package com.company.usuarios;

import com.company.Menu;

import java.util.*;

public class Usuarios {

    private ArrayList<Usuario> usuarios;

    public Usuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuarios() {
    }


    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
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

    public void mostrarUsuarios(){
        int j=0;
        while (j<usuarios.size()){
            System.out.println(j+1 + " "+usuarios.get(j).getNombre() +", "+usuarios.get(j).getApellido() );
            j++;
        }
    }

    public Usuario seleccionarUsuario(){
        Usuario seleccinado=new Usuario();
        int pos=0;
        boolean validacion=false;
        Scanner teclado= new Scanner(System.in);
        System.out.println("Ingrese un usuario: " + '\n');
        do{
            mostrarUsuarios();
            try {
                teclado= new Scanner(System.in);
                pos = teclado.nextInt();
                pos = pos - 1;
                if (pos < usuarios.size() && pos >= 0) {
                    seleccinado = traerUsuarioId(pos);
                    validacion = true;
                } else {
                    Menu.clearScreen();
                    System.out.println("Ingrese un usuario valido");
                }
            }catch(InputMismatchException e) {
                Menu.clearScreen();
                System.out.println("Ingresar un numero");
            }
        } while(!validacion);
        return seleccinado;
    }


}

