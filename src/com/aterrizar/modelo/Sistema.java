package com.aterrizar.modelo;

import com.aterrizar.exception.AsientoLanchitaNoDisponibleException;
import com.aterrizar.exception.AsientoNoDisponibleException;
import com.aterrizar.modelo.Aerolinea.Aerolinea;
import com.aterrizar.modelo.Aerolinea.AerolineaProxy;
import com.aterrizar.modelo.Asiento.Asiento;
import com.aterrizar.modelo.Usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class Sistema {
    AerolineaProxy aerolineaProxy;


    public void registrarUsuario(String nombre, String apellido, int DNI) {}

    public List<Asiento> buscarAsientos(FiltroVueloAsiento filtroVueloAsiento, Usuario usuario) {
        List<Asiento> vuelos = new ArrayList<>();
        vuelos.addAll(this.aerolineaProxy.buscarAsientos(filtroVueloAsiento));
        vuelos.addAll(this.aerolineaProxy.getSuperOfertas(usuario));

        return null;
    }

    public void comprar(Aerolinea aerolinea, Asiento asiento, Usuario usuario, FiltroVueloAsiento filtroVueloAsiento) throws AsientoNoDisponibleException {
        try {
            aerolinea.comprar(asiento.getVuelo().getCodigoVuelo());
            usuario.agregarVueloComprado(asiento);
            usuario.agregarVueloAlHistorial(filtroVueloAsiento);
        } catch (AsientoLanchitaNoDisponibleException e) {
            throw new AsientoNoDisponibleException("Aerolinea Lanchita: " + e.getMessage());
        }
    }

    public Sistema() {
        this.aerolineaProxy = new AerolineaProxy();
    }
}
