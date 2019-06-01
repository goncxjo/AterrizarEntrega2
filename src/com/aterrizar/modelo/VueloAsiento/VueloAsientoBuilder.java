package com.aterrizar.modelo.VueloAsiento;

import com.aterrizar.enumerator.Destino;
import com.aterrizar.modelo.Asiento.Asiento;
import com.aterrizar.modelo.Asiento.EstadoAsiento;
import com.aterrizar.modelo.Ubicacion;

import java.util.Date;

public class VueloAsientoBuilder {
    VueloAsiento vueloAsiento;

    public VueloAsientoBuilder(String nombreAerolinea) {
        vueloAsiento = new VueloAsiento(nombreAerolinea);
    }

    public VueloAsientoBuilder agregarTipoAsiento(Asiento asiento) {
        vueloAsiento.setAsiento(asiento);
        return this;
    }

    public VueloAsientoBuilder agregarCodigoAsiento(String codigoAsiento) {
        vueloAsiento.getAsiento().setCodigoAsiento(codigoAsiento);
        return this;
    }

    public VueloAsientoBuilder agregarOrigen(Destino origen) {
        vueloAsiento.getVuelo().setOrigen(origen);
        return this;
    }

    public VueloAsientoBuilder agregarDestino(Destino  destino) {
        vueloAsiento.getVuelo().setDestino(destino);
        return this;
    }

    public VueloAsientoBuilder agregarFecha(Date fecha) {
        vueloAsiento.getVuelo().setFecha(fecha);
        return this;
    }

    public VueloAsientoBuilder agregarPrecio(double precio) {
        vueloAsiento.getAsiento().setPrecio(precio);
        return this;
    }

    public VueloAsientoBuilder agregarUbicacion(Ubicacion ubicacion) {
        vueloAsiento.getAsiento().setUbicacion(ubicacion);
        return this;
    }

    public VueloAsientoBuilder agregarEstadoAsiento(EstadoAsiento estadoAsiento) {
        vueloAsiento.getAsiento().setEstadoAsiento(estadoAsiento);
        return this;
    }

    public VueloAsiento build() {
        return vueloAsiento;
    }
}
