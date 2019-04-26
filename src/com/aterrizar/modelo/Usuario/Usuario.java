package com.aterrizar.modelo.Usuario;

public abstract class Usuario {
     private String nombre;
     private String apellido;
     private String DNI;

     public String getNombre() {
         return nombre;
     }

     public String getApellido() {
        return apellido;
     }

     public String getDNI() {
        return DNI;
     }
}
