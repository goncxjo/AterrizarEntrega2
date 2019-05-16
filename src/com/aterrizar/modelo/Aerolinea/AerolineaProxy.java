package com.aterrizar.modelo.Aerolinea;

import com.aterrizar.exception.AsientoNoDisponibleException;
import com.aterrizar.modelo.Asiento.Asiento;
import com.aterrizar.modelo.Ubicacion.Ubicacion;
import com.aterrizar.modelo.Usuario.Usuario;
import com.aterrizar.modelo.FiltroVueloAsiento;
import com.aterrizar.util.DateHelper;

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
        aerolineaLanchita = new AerolineaLanchitaImplementacion();
    }

    public AerolineaProxy(AerolineaLanchita aerolineaLanchita) {
        generarDiccionarioDestinos();
        this.aerolineaLanchita = aerolineaLanchita;
    }

    private void generarDiccionarioDestinos() {
        diccionarioDestinos = new HashMap();
        diccionarioDestinos.put("BUE", "Buenos Aires");
        diccionarioDestinos.put("BAR", "Barcelona");
        diccionarioDestinos.put("LA", "Los Angeles");
        diccionarioDestinos.put("NY", "Nueva York");
        diccionarioDestinos.put("TOK", "Tokyo");
        diccionarioDestinos.put("MIA", "Miami");
    }

    @Override
    public List<Asiento> buscarAsientos(FiltroVueloAsiento filtroVueloAsiento) {
        asientos.clear();

        asientos.addAll(getAsientosConImpuestos(aerolineaLanchita));
        //asientos.addAll(getAsientosConImpuestos(aerolineaFake));

        return filtrarAsientos(filtroVueloAsiento);
    }

    public List<Asiento> getSuperOfertas(Usuario usuario) {
        List<Asiento> superOfertas = new ArrayList();
        List<Asiento> asientosSinFiltros = buscarAsientos(new FiltroVueloAsiento());

        for (Asiento asiento : asientosSinFiltros) {
            if (usuario.puedeVerSuperOferta(asiento)) {
                superOfertas.add(asiento);
            }
        }

        return superOfertas;
    }

    @Override
    public void comprar(String codigoAsiento) throws AsientoNoDisponibleException {

    }

    private List<Asiento> getAsientosConImpuestos(Aerolinea aerolinea) {
        return aerolinea
                .getAsientos()
                .stream()
                .map(a -> sumarImpuestosAsientos(a, aerolinea))
                .collect(Collectors.toList());
    }

    private Asiento sumarImpuestosAsientos(Asiento asiento, Aerolinea aerolinea) {
        asiento.calcularPrecioTotal(aerolinea.porcentajeImpuestos);
        return asiento;
    }

    private List<Asiento> filtrarAsientos(FiltroVueloAsiento filtroVueloAsiento) {
        filtrarAsientosPorOrigen(diccionarioDestinos.get(filtroVueloAsiento.getOrigen()));
        filtrarAsientosPorDestino(diccionarioDestinos.get(filtroVueloAsiento.getDestino()));
        filtrarAsientosPorFechaSalida(filtroVueloAsiento.getFechaSalida());
        filtrarAsientosPorFechaLlegada(filtroVueloAsiento.getFechaLlegada());
        filtrarAsientosPorAsiento(filtroVueloAsiento.getAsiento());
        filtrarAsientosPorUbicacion(filtroVueloAsiento.getUbicacion());
        
        return asientos;
    }

    private void filtrarAsientosPorUbicacion(Ubicacion ubicacion) {
        if(ubicacion != null) {
            asientos = asientos
                    .stream()
                    .filter(a -> a.getUbicacion().getClass() == ubicacion.getClass())
                    .collect(Collectors.toList());
        }
    }

    private void filtrarAsientosPorAsiento(Asiento asiento) {
        if(asiento != null) {
            asientos = asientos
                    .stream()
                    .filter(a -> a.getClass() == asiento.getClass())
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
}
