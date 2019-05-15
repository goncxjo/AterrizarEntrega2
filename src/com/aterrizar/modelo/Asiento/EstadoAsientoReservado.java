package com.aterrizar.modelo.Asiento;

public class EstadoAsientoReservado implements EstadoAsiento {
    @Override
    public boolean estaDisponible() {
        return false;
    }
}
