package com.aterrizar.modelo.Aerolinea;

import com.aterrizar.modelo.Vuelo.Vuelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de los métodos proporcionados por la Aerolina "Lanchita" (implements).
 * También hereda los métodos y propiedades de la clase abstracta "Aerolinea" (extends)
 * */
public class AerolineaLanchitaImplementacion extends Aerolinea implements AerolineaLanchita {

    public AerolineaLanchitaImplementacion() {
        this.porcentajeImpuestos = 0.15f;
    }

    // TODO: pendiente implementacion
    @Override
    public ArrayList<ArrayList<String>> asientosDisponibles(String origen, String fechaSalida, String horaSalida, String destino, String fechaLlegada, String horaLlegada) {
        /*
        List<Vuelo> query = this.vuelos;

        if(origen != null) {
            query = query.stream().filter(x -> x.getOrigen().contains(origen)).collect(Collectors.toList());
        }
        if(destino != null) {
            query = query.stream().filter(x -> x.getDestino().contains(destino)).collect(Collectors.toList());
        }
        */

        return null;
    }

    // TODO: pendiente implementacion
    @Override
    public void comprar(String codigoAsiento) {

    }
}
