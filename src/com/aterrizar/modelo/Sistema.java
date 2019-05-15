package com.aterrizar.modelo;

import com.aterrizar.modelo.Aerolinea.Aerolinea;
import com.aterrizar.modelo.Aerolinea.AerolineaProxy;
import com.aterrizar.modelo.Asiento.Asiento;
import com.aterrizar.modelo.Usuario.Usuario;
import com.aterrizar.modelo.Vuelo.FiltroVuelo;
import com.aterrizar.modelo.Vuelo.RegistroVuelo;
import com.aterrizar.modelo.Vuelo.Vuelo;

import java.util.ArrayList;
import java.util.List;

public class Sistema {
    AerolineaProxy aerolineaProxy;

    // TODO: pendiente implementacion
    public void registrarUsuario(String nombre, String apellido, int DNI) {}

    public List<RegistroVuelo> buscarVuelos(FiltroVuelo filtroVuelo, Usuario usuario) {
        Aerolinea aerolinea = new AerolineaProxy();

        List<RegistroVuelo> vuelos = new ArrayList<>();
        vuelos.addAll(
                aerolinea.buscarVuelos(filtroVuelo, usuario)
        );

        return null;
    }

    // TODO: pendiente implementacion
    public void comprar(Vuelo vuelo, Asiento asiento, Usuario usuario) {}

    public Sistema() {
        this.aerolineaProxy = new AerolineaProxy();
    }
}
