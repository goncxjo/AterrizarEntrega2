package com.aterrizar.modelo;

import com.aterrizar.modelo.Asiento.Asiento;
import com.aterrizar.modelo.Ubicacion.Ubicacion;
import com.aterrizar.modelo.Usuario.Usuario;
import com.aterrizar.modelo.Vuelo.FiltroVuelo;
import com.aterrizar.modelo.Vuelo.Vuelo;

import java.util.List;

public class Sistema {
    Comunicador comunicador;

    // TODO: pendiente implementacion
    public void registrarUsuario(String nombre, String apellido, int DNI) {}

    // TODO: pendiente implementacion
    public List<Vuelo> buscarVuelos(FiltroVuelo filtroVuelo, Usuario usuario) {
        return null;
    }

    // TODO: pendiente implementacion
    public void comprar(Vuelo vuelo, Asiento asiento, Usuario usuario) {}

    public Sistema() {
        this.comunicador = new Comunicador();
    }
}
