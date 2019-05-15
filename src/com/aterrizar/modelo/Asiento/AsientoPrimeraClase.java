package com.aterrizar.modelo.Asiento;

import com.aterrizar.modelo.Ubicacion.Ubicacion;
import com.aterrizar.modelo.Vuelo.Vuelo;

public class AsientoPrimeraClase extends Asiento {
    public AsientoPrimeraClase(Vuelo vuelo, double precio, Ubicacion ubicacion, EstadoAsiento estadoAsiento) {
        super(vuelo, precio, ubicacion, estadoAsiento);
    }

    public AsientoPrimeraClase() {
    }
}


