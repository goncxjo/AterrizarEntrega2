package com.aterrizar.modelo.Asiento;

import com.aterrizar.modelo.Ubicacion;

public class AsientoEjecutivo extends Asiento {

    public AsientoEjecutivo() {
    }

    public AsientoEjecutivo(String codigoAsiento, double precio, Ubicacion ubicacion, EstadoAsiento estadoAsiento) {
        super(codigoAsiento, precio, ubicacion, estadoAsiento);
    }
}
