package com.aterrizar.modelo;

import com.aterrizar.modelo.Aerolinea.*;
import com.aterrizar.modelo.Usuario.Usuario;
import com.aterrizar.modelo.Vuelo.FiltroVuelo;
import com.aterrizar.modelo.Vuelo.Vuelo;

import java.util.ArrayList;
import java.util.List;

/**
 * El comunicador nos proporciona herramientas
 * para comunicarnos entre aterrizar.com y las aerolineas.
 * */
public class Comunicador {
    private AerolineaLanchita aerolineaLanchita;
    private AerolineaFake aerolineaFake;

    // TODO: implementar método
    /**
     * Busca los vuelos en Aerolineas Lanchita
     * @param filtroVuelo los filtros proporcionados por el usuario
     * @param usuario el usuario que realizó la consulta
     * */
    public List<Vuelo> buscarVuelosLanchita(FiltroVuelo filtroVuelo, Usuario usuario) {
        List<Vuelo> vuelos = new ArrayList<>();

        if(aerolineaLanchita == null) {
            aerolineaLanchita = new AerolineaLanchitaImplementacion();
        }

        return vuelos;
    }

    /**
     * Busca los vuelos en Aerolineas Fake (sólo para pruebas)
     * */
    public List<Vuelo> buscarVuelosFake(FiltroVuelo filtroVuelo, Usuario usuario) {
        List<Vuelo> vuelos = new ArrayList<>();

        if(aerolineaFake == null) {
            aerolineaFake = new AerolineaFakeImplementacion();
        }

        return vuelos;
    }

    /**
     * Este método obtiene los vuelos disponibles de todas las aerolineas en aterrizar.com
     * @param filtroVuelo los filtros proporcionados por el usuario
     * @param usuario el usuario que realizó la consulta
     * */
    public List<Vuelo> buscarVuelos(FiltroVuelo filtroVuelo, Usuario usuario) {
        List<Vuelo> vuelos = new ArrayList<>();

        vuelos.addAll(this.buscarVuelosLanchita(filtroVuelo, usuario));
        vuelos.addAll(this.buscarVuelosFake(filtroVuelo, usuario));

        return vuelos;
    }
}
