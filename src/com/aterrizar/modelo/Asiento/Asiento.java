package com.aterrizar.modelo.Asiento;

import com.aterrizar.modelo.Ubicacion.Ubicacion;
import com.aterrizar.modelo.Vuelo.Vuelo;

public abstract class Asiento {
    protected String codigoVuelo;
    static protected int siguienteNro = 0;
    protected int nroAsiento;
    protected double precio;
    protected Ubicacion ubicacion;
    protected EstadoAsiento estadoAsiento;

    public Asiento(Vuelo vuelo, double precio, Ubicacion ubicacion, EstadoAsiento estadoAsiento) {
        this.siguienteNro += 1;
        this.nroAsiento = siguienteNro;
        this.codigoVuelo = vuelo.getCodigoVuelo();
        this.precio = precio;
        this.ubicacion = ubicacion;
        this.estadoAsiento = estadoAsiento;
    }

    public Asiento() {
        this.siguienteNro += 1;
    }

    public String getCodigoVuelo() {
        return codigoVuelo;
    }

    public String getCodigoAsiento() {
        return this.codigoVuelo + "-" + this.nroAsiento;
    }

    public double getPrecio() {
        return precio;
    }

    public double getImpuesto(double porcentajeImpuestos) {
        return this.precio * porcentajeImpuestos;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public EstadoAsiento getEstado() {
        return estadoAsiento;
    }

    public void setEstado(EstadoAsiento estadoAsiento) {
        this.estadoAsiento = estadoAsiento;
    }
}
