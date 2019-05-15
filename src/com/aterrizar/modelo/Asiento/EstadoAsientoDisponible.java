package com.aterrizar.modelo.Asiento;

public class EstadoAsientoDisponible implements EstadoAsiento {

    @Override
    public boolean estaDisponible() {
        return true;
    }
}
