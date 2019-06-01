package com.aterrizar.modelo.Asiento;

import com.aterrizar.modelo.Ubicacion;

public class AsientoTurista extends Asiento {
    public AsientoTurista() {
    }

    public AsientoTurista(String codigoAsiento, double precio, Ubicacion ubicacion, EstadoAsiento estadoAsiento) {
        super(codigoAsiento, precio, ubicacion, estadoAsiento);
    }
}
