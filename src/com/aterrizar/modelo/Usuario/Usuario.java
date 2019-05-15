package com.aterrizar.modelo.Usuario;

import com.aterrizar.modelo.Asiento.Asiento;
import com.aterrizar.modelo.Vuelo.RegistroVuelo;

import java.util.ArrayList;
import java.util.List;

public abstract class Usuario {
    protected String nombre;
    protected String apellido;
    protected int DNI;
    protected List<RegistroVuelo> historialAsientos;
    protected List<RegistroVuelo> asientosComprados;

    public Usuario(String nombre, String apellido, int DNI) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.DNI = DNI;
        this.historialAsientos = new ArrayList();
        this.asientosComprados = new ArrayList();
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

    public List<RegistroVuelo> getHistorialAsientos() {
        return this.historialAsientos;
    }

    public void agregarVueloAlHistorial(RegistroVuelo vuelo) { this.historialAsientos.add(vuelo); }

    public List<RegistroVuelo> getAsientosComprados() {
        return this.asientosComprados;
    }

    public void agregarVueloComprado(RegistroVuelo vuelo) { this.asientosComprados.add(vuelo); }

    public float getRecargo() { return 0; }

    public boolean puedeVerSuperOferta(Asiento asiento, double precioTotal) { return false; }
}
