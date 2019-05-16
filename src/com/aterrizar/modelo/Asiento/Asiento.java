package com.aterrizar.modelo.Asiento;

import com.aterrizar.modelo.Ubicacion.Ubicacion;
import com.aterrizar.modelo.Vuelo.Vuelo;

public abstract class Asiento {
    protected Vuelo vuelo;
    static protected int siguienteNro = 0;
    protected int nroAsiento;
    protected double precio;
    protected double precioTotal;
    protected Ubicacion ubicacion;
    protected EstadoAsiento estadoAsiento;

    public Asiento(Vuelo vuelo, double precio, Ubicacion ubicacion, EstadoAsiento estadoAsiento) {
        this.siguienteNro += 1;
        this.nroAsiento = siguienteNro;
        this.vuelo = vuelo;
        this.precio = precio;
        this.precioTotal = precio;
        this.ubicacion = ubicacion;
        this.estadoAsiento = estadoAsiento;
    }

    public Asiento() {
        this.siguienteNro += 1;
    }

    public Vuelo getVuelo() {
        return this.vuelo;
    }

    public String getCodigoAsiento() {
        return this.vuelo.getCodigoVuelo() + "-" + this.nroAsiento;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void calcularPrecioTotal(double porcentajeImpuestos) {
        this.precioTotal = this.precio + getImpuesto(porcentajeImpuestos);
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
