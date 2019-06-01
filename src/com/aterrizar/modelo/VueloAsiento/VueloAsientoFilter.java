package com.aterrizar.modelo.VueloAsiento;

import com.aterrizar.enumerator.Destino;
import com.aterrizar.modelo.Asiento.Asiento;
import com.aterrizar.modelo.Ubicacion;

public class VueloAsientoFilter {
    protected Destino origen;
    protected Destino destino;
    protected Asiento asiento;
    protected Ubicacion ubicacion;
    private String fecha;

    public VueloAsientoFilter(Destino origen, Destino destino, String fecha, Asiento asiento, Ubicacion ubicacion) {
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.asiento = asiento;
        this.ubicacion = ubicacion;
    }

    public VueloAsientoFilter() {
        this.origen = null;
        this.destino = null;
        this.fecha = null;
        this.asiento = null;
        this.ubicacion = null;
    }

    public Destino getOrigen() {
        return origen;
    }

    public Destino getDestino() {
        return destino;
    }

    public String getFecha() {
        return fecha;
    }

    public Asiento getAsiento() {
        return asiento;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }
}
