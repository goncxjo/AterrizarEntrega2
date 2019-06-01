package com.aterrizar.modelo;

import com.aterrizar.enumerator.Destino;

import java.util.Date;

public class Vuelo {
    private int nroVuelo;
    private Destino origen;
    private Destino destino;
    private Date fecha;

    public int getNroVuelo() {
        return nroVuelo;
    }

    public void setNroVuelo(int nroVuelo) {
        this.nroVuelo = nroVuelo;
    }

    public Destino getOrigen() {
        return origen;
    }

    public void setOrigen(Destino origen) {
        this.origen = origen;
    }

    public Destino getDestino() {
        return destino;
    }

    public void setDestino(Destino destino) {
        this.destino = destino;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha= fecha;
    }
}
