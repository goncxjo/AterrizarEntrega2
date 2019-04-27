package com.aterrizar.modelo.Aerolinea;

import com.aterrizar.modelo.Vuelo.Vuelo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AerolineaLanchita implements IAerolineaLanchita {
    private final float porcentaje = 0.15F;
    private List<Vuelo> vuelos;

    public float getPorcentaje() { return porcentaje; }

    public List<Vuelo> getVuelos() { return vuelos; }

    public void agregarVuelo(Vuelo vuelo) {
        this.vuelos.add(vuelo);
    }



    @Override
    public ArrayList<ArrayList<String>> asientosDisponibles(String origen, String fechaSalida, String horaSalida, String destino, String fechaLlegada, String horaLlegada) {

        List<Vuelo> query = this.vuelos;

        if(origen != null) {
            query = query.stream().filter(x -> x.getOrigen().contains(origen)).collect(Collectors.toList());
        }
        if(destino != null) {
            query = query.stream().filter(x -> x.getDestino().contains(destino)).collect(Collectors.toList());
        }
        if(fechaSalida != null) {
            query = query.stream().filter(x -> x.get()).collect(Collectors.toList());
        }
        if(horaSalida != null) {
            query = query.stream().filter(x -> x.getDestino().contains(destino)).collect(Collectors.toList());
        }
        if(fechaLlegada != null) {
            query = query.stream().filter(x -> x.getDestino().contains(destino)).collect(Collectors.toList());
        }
        if(horaLlegada != null) {
            query = query.stream().filter(x -> x.getDestino().contains(destino)).collect(Collectors.toList());
        }
    }

    @Override
    public void comprar(String codigoAsiento) {

    }
}
