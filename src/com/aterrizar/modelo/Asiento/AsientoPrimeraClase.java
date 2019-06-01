package com.aterrizar.modelo.Asiento;

import com.aterrizar.modelo.Ubicacion;

public class AsientoPrimeraClase extends Asiento {

    public AsientoPrimeraClase() {
    }

    public AsientoPrimeraClase(String codigoAsiento, double precio, Ubicacion ubicacion, EstadoAsiento estadoAsiento) {
        super(codigoAsiento, precio, ubicacion, estadoAsiento);
    }
}


