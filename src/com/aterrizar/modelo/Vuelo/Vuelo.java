package com.aterrizar.modelo.Vuelo;

import java.util.Date;

public class Vuelo {
    private String nombreVuelo = "AL0";
    static private int siguienteNro = 0;
    private int nroVuelo;
    private String origen;
    private String destino;
    private Date fechaSalida;
    private Date fechaLlegada;

    public Vuelo(String origen, String destino, Date fechaSalida, Date fechaLlegada) {
        this.siguienteNro += 1;
        this.nroVuelo = siguienteNro;
        this.origen = origen;
        this.destino = destino;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
    }

    public Vuelo() {
        this.siguienteNro += 1;
    }

    public String getCodigoVuelo() { return this.nombreVuelo + this.nroVuelo; }

    public String getOrigen() { return origen; }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public String getDestino() {
        return destino;
    }

    public Date getFechaLlegada() {
        return fechaLlegada;
    }
}
