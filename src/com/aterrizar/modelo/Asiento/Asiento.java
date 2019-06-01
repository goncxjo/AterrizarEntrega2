package com.aterrizar.modelo.Asiento;

import com.aterrizar.modelo.Ubicacion;

public abstract class Asiento {
    protected String codigoAsiento;
    protected double precio;
    protected Ubicacion ubicacion;
    protected EstadoAsiento estadoAsiento;

    public Asiento() {
    }

    public Asiento(String codigoAsiento, double precio, Ubicacion ubicacion, EstadoAsiento estadoAsiento) {
        this.codigoAsiento = codigoAsiento;
        this.precio = precio;
        this.ubicacion = ubicacion;
        this.estadoAsiento = estadoAsiento;
    }

    public String getCodigoAsiento() {
        return codigoAsiento;
    }

    public void setCodigoAsiento(String codigoAsiento) {
        this.codigoAsiento = codigoAsiento;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public EstadoAsiento getEstadoAsiento() {
        return estadoAsiento;
    }

    public void setEstadoAsiento(EstadoAsiento estadoAsiento) {
        this.estadoAsiento = estadoAsiento;
    }
}
