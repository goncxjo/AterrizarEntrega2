package com.aterrizar.modelo.Vuelo;

import com.aterrizar.modelo.Asiento.Asiento;
import com.aterrizar.modelo.Ubicacion.Ubicacion;

import java.util.Date;

public class RegistroVuelo {
    private String codigoVuelo;
    private double precio;
    private String origen;
    private String destino;
    private Asiento asiento;
    private Ubicacion ubicacion;
    private Date fechaSalida;
    private Date fechaLlegada;

    public boolean esSuperOferta() {
        return esSuperOferta;
    }

    public void setSuperOferta(boolean esSuperOferta) {
        this.esSuperOferta = esSuperOferta;
    }

    private boolean esSuperOferta;

    public RegistroVuelo(String codigoVuelo, double precio, String origen, String destino, Asiento asiento, Ubicacion ubicacion, Date fechaSalida, Date fechaLlegada, boolean esSuperOferta) {
        this.codigoVuelo = codigoVuelo;
        this.precio = precio;
        this.origen = origen;
        this.destino = destino;
        this.asiento = asiento;
        this.ubicacion = ubicacion;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.esSuperOferta = esSuperOferta;
    }

    public String getCodigoVuelo() {
        return codigoVuelo;
    }

    public void setCodigoVuelo(String codigoVuelo) {
        this.codigoVuelo = codigoVuelo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Asiento getAsiento() {
        return asiento;
    }

    public void setAsiento(Asiento asiento) {
        this.asiento = asiento;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Date getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(Date fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }
}
