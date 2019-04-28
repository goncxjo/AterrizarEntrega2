package com.aterrizar.modelo.Aerolinea;

import com.aterrizar.modelo.Vuelo.Vuelo;

import java.util.List;

/**
 * Esta clase pretende normalizar las propiedades @porcentajeImpuestos y @vuelos
 * para toda aerolinea que haya firmado con aterrizar.com
 * */
public abstract class Aerolinea {
    protected float porcentajeImpuestos;
    protected List<Vuelo> vuelos;

    public float getPorcentajeImpuestos() {
        return porcentajeImpuestos;
    }

    public List<Vuelo> getVuelos() {
        return vuelos;
    }

    public void agregarVuelo(Vuelo vuelo) {
        this.vuelos.add(vuelo);
    }
}
