package com.aterrizar.modelo.Usuario;

import com.aterrizar.modelo.Vuelo.Vuelo;

import java.util.List;

public abstract class Usuario {
     private String nombre;
     private String apellido;
     private String DNI;
     private List<Vuelo> historialVuelos;

     public String getNombre() {
         return nombre;
     }
     public String getApellido() {
        return apellido;
     }
     public String getDNI() {
        return DNI;
     }

     public double getRecargo() { return 0; }
}
