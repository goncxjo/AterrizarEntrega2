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

    /**
     * Obtiene todos los asientos disponibles en aerolinea, según criterio del usuario.
     * Además, incluye las super ofertas disponibles para el usuario.
     * @param filtroVueloAsiento criterio de busqueda del usuario
     * @param usuario usuario que realiza la consulta
     * @return asientos filtrados + super ofertas
     * */
    public List<Asiento> buscarAsientos(FiltroVueloAsiento filtroVueloAsiento, Usuario usuario) {
        List<Asiento> asientos = new ArrayList<>();

        asientos.addAll(this.aerolineaProxy.buscarAsientos(filtroVueloAsiento));
        asientos.addAll(this.aerolineaProxy.getSuperOfertas(usuario));

        return asientos;
    }

    /**
     * Este método reserva un asiento,
     * agrega dicho asiento a los asientos comprados por el usuario y
     * actualiza el historial de búsqueda del usuario.
     * @param asiento asiento a comprar
     * @param usuario usuario que realiza la compra
     * @param filtroVueloAsiento criterio de busqueda del usuario
     * */
    public void comprarAsiento(Asiento asiento, Usuario usuario, FiltroVueloAsiento filtroVueloAsiento) throws AsientoNoDisponibleException {
        this.aerolineaProxy.comprar(asiento.getCodigoAsiento());
        usuario.agregarVueloComprado(asiento);
        usuario.agregarVueloAlHistorial(filtroVueloAsiento);
    }

    public Sistema() {
        this.aerolineaProxy = new AerolineaProxy();
    }
}
