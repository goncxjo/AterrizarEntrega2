package com.aterrizar.modelo.Aerolinea;

import com.aterrizar.enumerator.Aerolinea;
import com.aterrizar.exception.AsientoLanchitaNoDisponibleException;
import com.aterrizar.exception.AsientoNoDisponibleException;
import com.aterrizar.modelo.Asiento.*;
import com.aterrizar.modelo.Ubicacion;
import com.aterrizar.modelo.Usuario.Usuario;
import com.aterrizar.modelo.Vuelo;
import com.aterrizar.modelo.VueloAsiento.VueloAsientoFilter;
import com.aterrizar.modelo.VueloAsiento.VueloAsiento;
import com.aterrizar.modelo.VueloAsiento.VueloAsientoBuilder;
import com.aterrizar.util.DateHelper;

import java.util.*;
import java.util.stream.Collectors;

public class AerolineaProxy {
    private AerolineaLanchita aerolineaLanchita;
    private List<VueloAsiento> vueloAsientos = new ArrayList();

    public AerolineaProxy(AerolineaLanchita aerolineaLanchita) {
        this.aerolineaLanchita = aerolineaLanchita;
    }

    /**
     * Obtiene todos los vueloAsientos de todas las aerolineas disponibles en aterrizar por criterio del usuario.
     * @param filtro criterio de busqueda por el usuario
     * */
    public List<VueloAsiento> buscarAsientos(VueloAsientoFilter filtro, Usuario usuario) {
        vueloAsientos.clear();

        vueloAsientos.addAll(getAsientosLanchita(filtro, usuario));

        return vueloAsientos;
    }

    /**
     * Obtiene las super ofertas de todas las aerolineas disponibles en aterrizar.
     * @param usuario es necesario para saber si puede ver o no super ofertas.
     * */
    public List<VueloAsiento> getSuperOfertas(Usuario usuario) {
        List<VueloAsiento> superOfertas = new ArrayList();

        for (VueloAsiento vueloAsiento : this.vueloAsientos) {
            if (usuario.puedeVerSuperOferta(vueloAsiento.getAsiento())) {
                superOfertas.add(vueloAsiento);
            }
        }

        return superOfertas;
    }

    /**
     * Este método reserva un asiento segun la aerolina a la cual pertenezca.
     * */
    public void comprar(String codigoAsiento) throws AsientoNoDisponibleException {
        String codigoAerolinea = codigoAsiento.split(" ")[0];

        if(Aerolinea.Lanchita.equals(codigoAerolinea)) {
            try {
                this.aerolineaLanchita.comprar(codigoAsiento);
            } catch (AsientoLanchitaNoDisponibleException e) {
                throw new AsientoNoDisponibleException("Aerolinea Lanchita: " + e.getMessage());
            }
        } else {
            throw new AsientoNoDisponibleException("El asiento no existe");
        }
    }

    public List<VueloAsiento> getAsientosLanchita(VueloAsientoFilter filtro, Usuario usuario) {
        List<List<String>> asientosDisponibles = this.aerolineaLanchita.asientosDisponibles(
                filtro.getOrigen().name()
                , filtro.getFecha()
                , filtro.getDestino().name()
                , null
        );

        if(asientosDisponibles.isEmpty()) {
            return new ArrayList<>();
        } else {
            return asientosDisponibles
                    .stream()
                    .map(asiento -> generar(asiento, filtro, usuario))
                    .collect(Collectors.toList());
        }
    }

    private VueloAsiento generar(List<String> asiento, VueloAsientoFilter filtro, Usuario usuario) {
        /*
         * [0] el código de asiento (número de vuelo seguido por un guión y luego seguido por el número de asiento)
         * [1] el precio definido por la aerolínea para ese asiento
         * [2] la clase en la que se encuentra el asiento (turista, ejecutiva o primera clase)
         * [3] la ubicación del asiento en el avión (ventana, centro o pasillo)
         * [4] el estado del asiento (reservado o disponible, por el momento solo se reciben vueloAsientos disponibles)
         */
        return new VueloAsientoBuilder("Lanchita")
                .agregarTipoAsiento(descifrarTipoAsiento(asiento.get(2)))
                .agregarCodigoAsiento(asiento.get(0))
                .agregarPrecio(Double.parseDouble(asiento.get(1)) + usuario.getRecargo())
                .agregarUbicacion(descifrarUbicacion(asiento.get(3)))
                .agregarEstadoAsiento(descifrarEstadoAsiento(asiento.get(4)))
                .agregarOrigen(filtro.getOrigen())
                .agregarDestino(filtro.getDestino())
                .agregarFecha(DateHelper.parseToDate(filtro.getFecha()))
                .build();
    }

    private Asiento descifrarTipoAsiento(String codigoTipoAsiento) {

        switch (codigoTipoAsiento) {
            case "E":
                return new AsientoEjecutivo();
            case "P":
                return new AsientoPrimeraClase();
            case "T":
                return new AsientoTurista();
            default:
                return null;
        }
    }

    private EstadoAsiento descifrarEstadoAsiento(String codigoEstadoAsiento) {
        return codigoEstadoAsiento.equals("D") ? new EstadoAsientoDisponible() : new EstadoAsientoReservado();
    }

    private Ubicacion descifrarUbicacion(String codigoUbicacion) {
        switch (codigoUbicacion) {
            case "C":
                return Ubicacion.Centro;
            case "P":
                return Ubicacion.Pasillo;
            case "V":
                return Ubicacion.Ventanilla;
            default:
                return null;
        }
    }
}
