package com.aterrizar.modelo.VueloAsiento;

import com.aterrizar.enumerator.Aerolinea;
import com.aterrizar.modelo.Asiento.Asiento;
import com.aterrizar.modelo.Vuelo;

public class VueloAsiento {
    private Aerolinea aerolinea;
    private Vuelo vuelo;
    private Asiento asiento;

    public VueloAsiento(String nombreAerolinea) {
        this.aerolinea = Aerolinea.valueOf(nombreAerolinea);
        this.vuelo = new Vuelo();
    }

    public Aerolinea getAerolinea() {
        return aerolinea;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public Asiento getAsiento() {
        return asiento;
    }

    public void setAsiento(Asiento asiento) {
        this.asiento = asiento;
    }
}