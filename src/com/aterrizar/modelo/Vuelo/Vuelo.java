package com.aterrizar.modelo.Vuelo;

import java.util.Date;

public class Vuelo {
    private String codigoAerolinea;
    static private int siguienteNro = 0;
    private int nroVuelo;
    private String origen;
    private String destino;
    private Date fechaSalida;
    private Date fechaLlegada;

    public Vuelo(String codigoAerolinea, String origen, String destino, Date fechaSalida, Date fechaLlegada) {
        this.siguienteNro += 1;
        this.codigoAerolinea = codigoAerolinea;
        this.nroVuelo = siguienteNro;
        this.origen = origen;
        this.destino = destino;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
    }

    public String getCodigoAerolinea() {
        return codigoAerolinea;
    }

    public Vuelo() {
        this.siguienteNro += 1;
    }

    public String getCodigoVuelo() { return this.codigoAerolinea + "/" + this.nroVuelo; }

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
