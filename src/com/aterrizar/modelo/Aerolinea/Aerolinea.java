package com.aterrizar.modelo.Aerolinea;

import com.aterrizar.exception.AsientoNoDisponibleException;
import com.aterrizar.modelo.Asiento.Asiento;
import com.aterrizar.modelo.Usuario.Usuario;
import com.aterrizar.modelo.FiltroVueloAsiento;
import com.aterrizar.modelo.Vuelo.Vuelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase pretende normalizar las propiedades @porcentajeImpuestos y @vuelos
 * para toda aerolinea que haya firmado con aterrizar.com
 * */
public abstract class Aerolinea {
    protected String codigoAerolinea;
    protected float porcentajeImpuestos;
    protected List<Vuelo> vuelos = new ArrayList();
    protected List<Asiento> asientos = new ArrayList();

    public String getCodigoAerolinea() {
        return codigoAerolinea;
    }

    public void setCodigoAerolinea(String codigoAerolinea) {
        this.codigoAerolinea = codigoAerolinea;
    }

    public List<Asiento> buscarAsientos(FiltroVueloAsiento filtroVueloAsiento) { return new ArrayList(); }

    public List<Asiento> getSuperOfertas(Usuario usuario) { return new ArrayList(); }

    public abstract void comprar(String codigoAsiento) throws AsientoNoDisponibleException;

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
