package com.company.usuarios;


public class Usuario {

    private String nombre;
    private String apellido;
    private String dni;
    private int edad;

    public Usuario(String nombre, String apellido, String dni, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.edad = edad;
    }

    public Usuario(){
        this.nombre = "nombre";
        this.apellido = "apellido";
        this.dni = "00.000.000";
        this.edad = 00;
    }

    public int getEdad() {
        return edad;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }


    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre  + '\n'+
                "Apellido: " + apellido  +'\n'+
                "DNI: " + dni  +'\n'+
                "Edad: " + edad;
    }
}
