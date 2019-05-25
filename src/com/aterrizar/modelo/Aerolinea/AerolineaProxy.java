package com.aterrizar.modelo.Aerolinea;

import com.aterrizar.exception.AsientoLanchitaNoDisponibleException;
import com.aterrizar.exception.AsientoNoDisponibleException;
import com.aterrizar.modelo.Asiento.Asiento;
import com.aterrizar.modelo.Ubicacion.Ubicacion;
import com.aterrizar.modelo.Usuario.Usuario;
import com.aterrizar.modelo.FiltroVueloAsiento;
import com.aterrizar.util.DateHelper;

import java.util.*;
import java.util.stream.Collectors;

public class AerolineaProxy extends Aerolinea {
    private AerolineaLanchita aerolineaLanchita;
    private Map<String, String> diccionarioDestinos = getDiccionarioDestinos();
	private List<Asiento> superOfertas = new ArrayList();

    public AerolineaProxy() {
        aerolineaLanchita = new AerolineaLanchitaImplementacion();
    }

    public AerolineaProxy(AerolineaLanchita aerolineaLanchita) {
        this.aerolineaLanchita = aerolineaLanchita;
    }

    private Map<String,String> getDiccionarioDestinos() {
        Map<String,String> destinos = new HashMap();

        destinos.put("BUE", "Buenos Aires");
        destinos.put("BAR", "Barcelona");
        destinos.put("LA", "Los Angeles");
        destinos.put("NY", "Nueva York");
        destinos.put("TOK", "Tokyo");
        destinos.put("MIA", "Miami");

        return destinos;
    }

    /**
     * Obtiene todos los asientos de todas las aerolineas disponibles en aterrizar por criterio del usuario.
     * @param filtroVueloAsiento criterio de busqueda por el usuario
     * */
    @Override
    public List<Asiento> buscarAsientos(FiltroVueloAsiento filtroVueloAsiento) {
        asientos.clear();

        asientos.addAll(aerolineaLanchita.getAsientos());

        return filtrarAsientos(filtroVueloAsiento);
    }

    /**
     * Obtiene las super ofertas de todas las aerolineas disponibles en aterrizar.
     * @param usuario es necesario para saber si puede ver o no super ofertas.
     * */
    @Override
    public List<Asiento> getSuperOfertas(Usuario usuario) {
        superOfertas.clear();

        superOfertas.addAll(aerolineaLanchita.getSuperOfertas(usuario));

        return superOfertas;
    }

    /**
     * Este método reserva un asiento segun la aerolina a la cual pertenezca.
     * */
    @Override
    public void comprar(String codigoAsiento) throws AsientoNoDisponibleException {
        String codigoAerolinea = codigoAsiento.split("/")[0];

        if(this.aerolineaLanchita.getCodigoAerolinea().contains(codigoAerolinea)) {
            try {
                this.aerolineaLanchita.comprar(codigoAsiento);
            } catch (AsientoLanchitaNoDisponibleException e) {
                throw new AsientoNoDisponibleException("Aerolinea Lanchita: " + e.getMessage());
            }
        } else {
            throw new AsientoNoDisponibleException("El asiento no existe");
        }
    }

    /**
     * Filtra los asientos según criterio del usuario
     * @return asientos filtrados.
     * */
    private List<Asiento> filtrarAsientos(FiltroVueloAsiento filtroVueloAsiento) {

        filtrarAsientosPorOrigen(diccionarioDestinos.get(filtroVueloAsiento.getOrigen()));
        filtrarAsientosPorDestino(diccionarioDestinos.get(filtroVueloAsiento.getDestino()));
        filtrarAsientosPorFechaSalida(filtroVueloAsiento.getFechaSalida());
        filtrarAsientosPorFechaLlegada(filtroVueloAsiento.getFechaLlegada());
        filtrarAsientosPorTipoAsiento(filtroVueloAsiento.getAsiento());
        filtrarAsientosPorTipoUbicacion(filtroVueloAsiento.getUbicacion());
        
        return asientos;
    }

    private void filtrarAsientosPorOrigen(String origen) {
        if(origen != null) {
            asientos = asientos
                    .stream()
                    .filter(a -> a.getVuelo().getOrigen().contains(origen))
                    .collect(Collectors.toList());
        }
    }

    private void filtrarAsientosPorDestino(String destino) {
        if(destino != null) {
            asientos = asientos
                    .stream()
                    .filter(a -> a.getVuelo().getDestino().contains(destino))
                    .collect(Collectors.toList());
        }
    }

    private void filtrarAsientosPorFechaSalida(String fechaSalida) {
        if(fechaSalida != null) {
            asientos = asientos
                    .stream()
                    .filter(a -> a.getVuelo().getFechaSalida().after(DateHelper.parseToDate(fechaSalida)))
                    .collect(Collectors.toList());
        }
    }

    private void filtrarAsientosPorFechaLlegada(String fechaLlegada) {
        if(fechaLlegada != null) {
        asientos = asientos
                .stream()
                .filter(a -> a.getVuelo().getFechaLlegada().before(DateHelper.parseToDate(fechaLlegada)))
                .collect(Collectors.toList());
        }
    }

    private void filtrarAsientosPorTipoUbicacion(Ubicacion ubicacion) {
        if(ubicacion != null) {
            asientos = asientos
                    .stream()
                    .filter(a -> a.getUbicacion().getClass().equals(ubicacion.getClass()))
                    .collect(Collectors.toList());
        }
    }

    private void filtrarAsientosPorTipoAsiento(Asiento asiento) {
        if(asiento != null) {
            asientos = asientos
                    .stream()
                    .filter(a -> a.getClass().equals(asiento.getClass()))
                    .collect(Collectors.toList());
        }
    }
}
