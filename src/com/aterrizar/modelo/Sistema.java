package com.aterrizar.modelo;

import com.aterrizar.exception.AsientoNoDisponibleException;
import com.aterrizar.modelo.Aerolinea.AerolineaProxy;
import com.aterrizar.modelo.Asiento.Asiento;
import com.aterrizar.modelo.Usuario.Usuario;
import com.aterrizar.modelo.VueloAsiento.VueloAsiento;
import com.aterrizar.modelo.VueloAsiento.VueloAsientoFilter;

import java.util.ArrayList;
import java.util.List;

public class Sistema {
    AerolineaProxy aerolineaProxy;
    
    public Sistema(AerolineaProxy aerolineaProxy) {
    	this.aerolineaProxy = aerolineaProxy;
    }

    public void registrarUsuario(String nombre, String apellido, int DNI) {}

    /**
     * Obtiene todos los asientos disponibles en aerolinea, segun criterio del usuario.
     * Ademas, incluye las super ofertas disponibles para el usuario.
     * @param filtro criterio de busqueda del usuario
     * @param usuario usuario que realiza la consulta
     * @return asientos filtrados + super ofertas
     * */
    public List<VueloAsiento> buscarAsientos(VueloAsientoFilter filtro, Usuario usuario) {
        List<VueloAsiento> asientos = new ArrayList<>();

        asientos.addAll(this.aerolineaProxy.buscarAsientos(filtro, usuario));
        asientos.addAll(this.aerolineaProxy.getSuperOfertas(usuario));

        return asientos;
    }

    /**
     * Este metodo reserva un asiento,
     * agrega dicho asiento a los asientos comprados por el usuario y
     * actualiza el historial de busqueda del usuario.
     * @param vueloAsiento asiento a comprar
     * @param usuario usuario que realiza la compra
     * @param vueloAsientoFilter criterio de busqueda del usuario
     * */
    public void comprarAsiento(VueloAsiento vueloAsiento, Usuario usuario, VueloAsientoFilter vueloAsientoFilter) throws AsientoNoDisponibleException {
        this.aerolineaProxy.comprar(vueloAsiento.getAsiento().getCodigoAsiento());
        usuario.agregarVueloComprado(vueloAsiento);
        usuario.agregarVueloAlHistorial(vueloAsientoFilter);
    }
    
}
