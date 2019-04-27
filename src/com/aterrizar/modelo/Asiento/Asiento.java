package com.aterrizar.modelo.Asiento;

import com.aterrizar.modelo.Ubicacion.IUbicacion;
import com.aterrizar.modelo.Usuario.Usuario;

public abstract class Asiento {
    private double precio;
    private boolean reservado = false;
    private IUbicacion ubicacion;

    public boolean estaReservado() {
        return reservado;
    }

    public void setReservado(boolean reservado) {
        this.reservado = reservado;
    }

    public IUbicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(IUbicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public double getPrecioTotal(float porcentaje, Usuario usuario) {
        return this.precio + (this.precio * porcentaje) + usuario.getRecargo();
    }

    public boolean esSuperOferta(float porcentaje, Usuario usuario) { return false; }
}
