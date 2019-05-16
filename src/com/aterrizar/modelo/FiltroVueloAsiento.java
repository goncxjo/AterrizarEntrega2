package com.aterrizar.modelo;

import com.aterrizar.modelo.Asiento.Asiento;
import com.aterrizar.modelo.Ubicacion.Ubicacion;

public class FiltroVueloAsiento {
    protected String origen;
    protected String destino;
    protected Asiento asiento;
    protected Ubicacion ubicacion;
    private String fechaSalida;
    private String fechaLlegada;

    public FiltroVueloAsiento(String origen, String destino, String fechaSalida, String fechaLlegada, Asiento asiento, Ubicacion ubicacion) {
        this.origen = origen;
        this.destino = destino;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.asiento = asiento;
        this.ubicacion = ubicacion;
    }

    public FiltroVueloAsiento() {
        this.origen = null;
        this.destino = null;
        this.fechaSalida = null;
        this.fechaLlegada = null;
        this.asiento = null;
        this.ubicacion = null;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public String getFechaLlegada() {
        return fechaLlegada;
    }

    public Asiento getAsiento() {
        return asiento;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }
}
