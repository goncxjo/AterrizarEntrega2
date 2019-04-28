package com.aterrizar.modelo.Asiento;

import com.aterrizar.modelo.Ubicacion.Ubicacion;

public abstract class Asiento {
    protected double precio;
    protected double precioTotal;
    protected Ubicacion ubicacion;
    protected EstadoAsiento estadoAsiento;

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPrecioTotal() {
        return precioTotal;
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

    public void calcularPrecioTotal(float porcentajeImpuestos, float recargoUsuario) {
        this.precioTotal = this.precio + (this.precio * porcentajeImpuestos) + recargoUsuario;
    }

    public boolean esSuperOferta() { return false; }
}
