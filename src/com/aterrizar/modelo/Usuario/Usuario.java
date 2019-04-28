package com.aterrizar.modelo.Usuario;

import com.aterrizar.modelo.Vuelo.Vuelo;

import java.util.List;

public abstract class Usuario {
     protected String nombre;
     protected String apellido;
     protected int DNI;
     protected List<Vuelo> historialVuelos;

    public Usuario(String nombre, String apellido, int DNI) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.DNI = DNI;
        this.historialVuelos = historialVuelos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public List<Vuelo> getHistorialVuelos() {
        return historialVuelos;
    }

    public void setHistorialVuelos(List<Vuelo> historialVuelos) {
        this.historialVuelos = historialVuelos;
    }

    public double getRecargo() { return 0; }
}
