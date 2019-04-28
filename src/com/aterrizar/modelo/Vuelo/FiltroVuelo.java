package com.aterrizar.modelo.Vuelo;

import com.aterrizar.modelo.Asiento.Asiento;
import com.aterrizar.modelo.Ubicacion.Ubicacion;

import java.util.Date;
import java.util.List;

public class FiltroVuelo {
    private String origen;
    private String destino;
    private Date fechaSalida;
    private Date fechaLlegada;
    private Asiento asiento;
    private Ubicacion ubicacion;

    public FiltroVuelo(String origen, String destino, Date fechaSalida, Date fechaLlegada, Asiento asiento, Ubicacion ubicacion) {
        this.origen = origen;
        this.destino = destino;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.asiento = asiento;
        this.ubicacion = ubicacion;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public Date getFechaLlegada() {
        return fechaLlegada;
    }

    public Asiento getAsiento() {
        return asiento;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }
}
