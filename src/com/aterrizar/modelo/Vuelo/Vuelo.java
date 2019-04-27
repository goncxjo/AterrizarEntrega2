package com.aterrizar.modelo.Vuelo;

import com.aterrizar.modelo.Asiento.Asiento;

import java.util.Date;
import java.util.List;

public class Vuelo {
    private String numeroVuelo = "AL0";
    private String origen;
    private String destino;
    private Date fechaSalida;
    private Date fechaLlegada;
    private List<Asiento> asientos;

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(Date fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public List<Asiento> getAsientos() {
        return asientos;
    }

    public void setAsientos(List<Asiento> asientos) {
        this.asientos = asientos;
    }

    public void agregarAsiento(Asiento asiento) {
        this.asientos.add(asiento);
    }
}
