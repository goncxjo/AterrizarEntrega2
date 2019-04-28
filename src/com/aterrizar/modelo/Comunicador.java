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
    private Aerolinea[] aerolineas;

    public Comunicador(Aerolinea[] aerolineas) {
        this.aerolineas = aerolineas;
    }

    /**
     * Este método itera por todas las aerolineas en aterrizar.com
     * en busca de vuelos disponibles
     * @param filtroVuelo los filtros proporcionados por el usuario
     * @param usuario el usuario que realizó la consulta
     * */
    public List<Vuelo> buscarVuelos(FiltroVuelo filtroVuelo, Usuario usuario) {
        List<Vuelo> vuelos = new ArrayList<>();

        for (Aerolinea item : aerolineas) {

            if(item instanceof AerolineaLanchitaImplementacion) { // Si la aerolinea es una instancia de "Lanchita", entonces buscar vuelos en "Lanchita"...
                vuelos = this.buscarVuelos(
                        (AerolineaLanchita)item // importante castear para que lo reconozca el método
                        , filtroVuelo
                        , usuario
                );
            } else if(item instanceof AerolineaFakeImplementacion) { // Si la aerolinea es una instancia de "Fake", entonces buscar vuelos en "Fake"...
                vuelos = this.buscarVuelos(
                        (AerolineaFakeImplementacion)item
                        , filtroVuelo
                        , usuario
                );
            }
        }

        return vuelos;
    }

    // TODO: implementar método
    /**
     * Busca los vuelos en Aerolineas Lanchita
     * @param aerolineaLanchita con esta instancia, puedo acceder a los métodos proporcionados por la aerolinea
     * @param filtroVuelo los filtros proporcionados por el usuario
     * @param usuario el usuario que realizó la consulta
     * */
    public List<Vuelo> buscarVuelos(AerolineaLanchita aerolineaLanchita, FiltroVuelo filtroVuelo, Usuario usuario) {
        List<Vuelo> vuelos = new ArrayList<>();
        return vuelos;
    }

    /**
     * Busca los vuelos en Aerolineas Fake (sólo para pruebas)
     * */
    public List<Vuelo> buscarVuelos(AerolineaFake aerolineaFake, FiltroVuelo filtroVuelo, Usuario usuario) {
        List<Vuelo> vuelos = new ArrayList<>();
        return vuelos;
    }



}
