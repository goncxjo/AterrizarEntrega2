package com.aterrizar.modelo.Aerolinea;

import com.aterrizar.exception.AsientoNoDisponibleException;
import com.aterrizar.modelo.Asiento.Asiento;
import com.aterrizar.modelo.Usuario.Usuario;
import com.aterrizar.modelo.Vuelo.FiltroVuelo;
import com.aterrizar.modelo.Vuelo.RegistroVuelo;
import com.aterrizar.modelo.Vuelo.Vuelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase pretende normalizar las propiedades @porcentajeImpuestos y @vuelos
 * para toda aerolinea que haya firmado con aterrizar.com
 * */
public abstract class Aerolinea {
    protected float porcentajeImpuestos;
    protected List<Vuelo> vuelos = new ArrayList();
    protected List<Asiento> asientos = new ArrayList();

    public List<RegistroVuelo> buscarVuelos(FiltroVuelo filtroVuelo, Usuario usuario) { return new ArrayList(); }

    public void comprar(RegistroVuelo vuelo, Usuario usuario) throws AsientoNoDisponibleException {}

    public float getPorcentajeImpuestos() {
        return porcentajeImpuestos;
    }

    public List<Vuelo> getVuelos() {
        return vuelos;
    }

    public void agregarVuelo(Vuelo vuelo) {
        this.vuelos.add(vuelo);
    }

    public List<Asiento> getAsientos() { return asientos; }

    public void agregarAsiento(Asiento asiento) { this.asientos.add(asiento); }
}
