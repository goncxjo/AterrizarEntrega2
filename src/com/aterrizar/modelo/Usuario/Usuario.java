package com.aterrizar.modelo.Usuario;

import com.aterrizar.exception.TipoUsuarioNoDisponibleException;
import com.aterrizar.modelo.Asiento.Asiento;
import com.aterrizar.modelo.FiltroVueloAsiento;

import java.util.ArrayList;
import java.util.List;

public abstract class Usuario {
    protected String nombre;
    protected String apellido;
    protected int DNI;
    protected List<FiltroVueloAsiento> historialAsientos;
    protected List<Asiento> asientosComprados;

    public Usuario(String nombre, String apellido, int DNI) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.DNI = DNI;
        this.historialAsientos = new ArrayList();
        this.asientosComprados = new ArrayList();
    }

    public Usuario(Usuario usuario) {
        this.nombre = usuario.getNombre();
        this.apellido = usuario.getApellido();
        this.DNI = usuario.getDNI();
        this.historialAsientos = usuario.getHistorialAsientos();
        this.asientosComprados = usuario.getAsientosComprados();
    }

    public Usuario() {}

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

    public List<FiltroVueloAsiento> getHistorialAsientos() {
        return this.historialAsientos;
    }

    public void agregarVueloAlHistorial(FiltroVueloAsiento vuelo) { this.historialAsientos.add(vuelo); }

    public List<Asiento> getAsientosComprados() { return this.asientosComprados; }

    public void agregarVueloComprado(Asiento asiento) { this.asientosComprados.add(asiento); }

    public float getRecargo() { return 0; }

    public boolean puedeVerSuperOferta(Asiento asiento) { return false; }

    public Usuario actualizarTipo(Usuario nuevoUsuario) throws TipoUsuarioNoDisponibleException {
        throw new TipoUsuarioNoDisponibleException("No existe el usuario solicitado");
    }
}



