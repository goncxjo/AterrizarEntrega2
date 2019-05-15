package com.aterrizar.modelo.Aerolinea;

import com.aterrizar.exception.AsientoLanchitaNoDisponibleException;
import com.aterrizar.exception.AsientoNoDisponibleException;
import com.aterrizar.modelo.Asiento.Asiento;
import com.aterrizar.modelo.Ubicacion.Ubicacion;
import com.aterrizar.modelo.Usuario.Usuario;
import com.aterrizar.modelo.Vuelo.FiltroVuelo;
import com.aterrizar.modelo.Vuelo.RegistroVuelo;
import com.aterrizar.modelo.Vuelo.Vuelo;

import java.util.*;
import java.util.stream.Collectors;

/**
 * El comunicador nos proporciona herramientas
 * para comunicarnos entre aterrizar.com y las aerolineas.
 * */
public class AerolineaProxy extends Aerolinea {
    private AerolineaLanchita aerolineaLanchita;
    private Map<String, String> diccionarioDestinos;

    public AerolineaProxy() {
        generarDiccionarioDestinos();
        this.aerolineaLanchita = new AerolineaLanchitaImplementacion();
    }

    public AerolineaProxy(AerolineaLanchita aerolineaLanchita) {
        generarDiccionarioDestinos();
        this.aerolineaLanchita = aerolineaLanchita;
    }

    private void generarDiccionarioDestinos() {
        this.diccionarioDestinos = new HashMap();
        diccionarioDestinos.put("BUE", "Buenos Aires");
        diccionarioDestinos.put("BAR", "Barcelona");
        diccionarioDestinos.put("LA", "Los Angeles");
        diccionarioDestinos.put("NY", "Nueva York");
        diccionarioDestinos.put("TOK", "Tokyo");
        diccionarioDestinos.put("MIA", "Miami");
    }

    /**
     * Este método obtiene los vuelos disponibles de todas las aerolineas en aterrizar.com
     * @param filtroVuelo los filtros proporcionados por el usuario
     * @param usuario el usuario que realizó la consulta
     * */
    public List<RegistroVuelo> buscarVuelos(FiltroVuelo filtroVuelo, Usuario usuario) {
        List<RegistroVuelo> vuelos = new ArrayList<>();

        vuelos.addAll(this.buscarVuelosLanchita(filtroVuelo, usuario));

        return filtrarAsientos(filtroVuelo, vuelos);
    }

    @Override
    public void comprar(RegistroVuelo vuelo, Usuario usuario) throws AsientoNoDisponibleException {
        try {
            this.aerolineaLanchita.comprar(vuelo.getCodigoVuelo());
            usuario.agregarVueloAlHistorial(vuelo);
        } catch (AsientoLanchitaNoDisponibleException e) {
            throw new AsientoNoDisponibleException("Aaerolinea Lanchita: " + e.getMessage());
        }
    }

    /**
     * Busca los vuelos en Aerolineas Lanchita
     * @param filtroVuelo los filtros proporcionados por el usuario
     * @param usuario el usuario que realizó la consulta
     * */
    public List<RegistroVuelo> buscarVuelosLanchita(FiltroVuelo filtroVuelo, Usuario usuario) {
        List<List<String>> asientosDisponibles = this.aerolineaLanchita.asientosDisponibles(
                diccionarioDestinos.get(filtroVuelo.getOrigen())
                , filtroVuelo.getFechaSalida()
                , null
                , diccionarioDestinos.get(filtroVuelo.getDestino())
                , filtroVuelo.getFechaLlegada()
                , null
        );
        return asientosDisponibles
                .stream()
                .map(asientoDisponible -> generarRegistroVuelo(asientoDisponible, usuario))
                .collect(Collectors.toList());
    }

    private RegistroVuelo generarRegistroVuelo(List<String> asientoDisponible, Usuario usuario) {
        String codigoAsiento = asientoDisponible.get(0);
        String codigoVuelo = codigoAsiento.split("-")[0];

        double precioTotal = Double.parseDouble(asientoDisponible.get(1)) + usuario.getRecargo();

        Vuelo vueloDelAsientoDisponible = this.aerolineaLanchita.getVuelos()
                .stream()
                .filter(x -> (x.getCodigoVuelo()).contains(codigoVuelo))
                .findFirst()
                .get();

        String origen = vueloDelAsientoDisponible.getOrigen();
        String destino = vueloDelAsientoDisponible.getDestino();
        Date fechaSalida = vueloDelAsientoDisponible.getFechaSalida();
        Date fechaLlegada = vueloDelAsientoDisponible.getFechaLlegada();

        Asiento asiento = this.aerolineaLanchita
                .getAsientos()
                .stream()
                .filter(a -> a.getCodigoAsiento().contains(codigoAsiento))
                .findFirst()
                .get();

        Ubicacion ubicacion = asiento.getUbicacion();

        return new RegistroVuelo(
                codigoVuelo
                , precioTotal
                , origen
                , destino
                , asiento
                , ubicacion
                , fechaSalida
                , fechaLlegada
                , usuario.puedeVerSuperOferta(asiento, precioTotal)
        );
    }

    private List<RegistroVuelo> filtrarAsientos(FiltroVuelo filtroVuelo, List<RegistroVuelo> vuelos) {
        List<RegistroVuelo> vuelosFiltrados = new ArrayList();

        Asiento asientoFiltrado = filtroVuelo.getAsiento();
        Ubicacion ubicacionFiltro = asientoFiltrado.getUbicacion();

        for (RegistroVuelo item : vuelos) {
            if(asientoFiltrado != null) {
                if(item.getAsiento().getClass() == asientoFiltrado.getClass()) {
                    vuelosFiltrados.add(item);
                }
            } else if(ubicacionFiltro != null) {
                if(item.getUbicacion().getClass() == ubicacionFiltro.getClass()) {
                    vuelosFiltrados.add(item);
                }
            }
        }

        return vuelosFiltrados;
    }
}
