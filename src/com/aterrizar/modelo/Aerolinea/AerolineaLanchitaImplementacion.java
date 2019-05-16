package com.aterrizar.modelo.Aerolinea;

import com.aterrizar.exception.AsientoLanchitaNoDisponibleException;
import com.aterrizar.modelo.Asiento.*;
import com.aterrizar.modelo.Ubicacion.UbicacionCentro;
import com.aterrizar.modelo.Ubicacion.UbicacionPasillo;
import com.aterrizar.modelo.Ubicacion.UbicacionVentanilla;
import com.aterrizar.modelo.Usuario.Usuario;
import com.aterrizar.modelo.Vuelo.Vuelo;
import com.aterrizar.util.DateHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AerolineaLanchitaImplementacion extends AerolineaLanchita {

    public AerolineaLanchitaImplementacion() {
        this.codigoAerolinea = "AL";
        this.porcentajeImpuestos = 0.15f;
    }

    @Override
    public List<List<String>> asientosDisponibles(String origen, String fechaSalida, String horaSalida, String destino, String fechaLlegada, String horaLlegada) {
        List<Vuelo> query = this.vuelos;

        if(origen != null) {
            query = query
                    .stream()
                    .filter(vuelo -> vuelo.getOrigen().contains(origen))
                    .collect(Collectors.toList());
        }
        if(fechaSalida != null) {
            query = query
                    .stream()
                    .filter(vuelo -> vuelo.getFechaSalida().after(DateHelper.parseToDate(fechaSalida)))
                    .collect(Collectors.toList());
        }
        if(destino != null) {
            query = query
                    .stream()
                    .filter(x -> x.getDestino().contains(destino))
                    .collect(Collectors.toList());
        }
        if(fechaLlegada != null) {
            query = query
                    .stream()
                    .filter(vuelo -> vuelo.getFechaLlegada().before(DateHelper.parseToDate(fechaLlegada)))
                    .collect(Collectors.toList());
        }

        List<String> listaCodigoVuelos = query
                .stream()
                .map(v -> v.getCodigoVuelo())
                .collect(Collectors.toList());

        return this.asientos
                .stream()
                .filter(asiento -> asiento.getEstado().estaDisponible())
                .filter(asiento -> listaCodigoVuelos.contains(asiento.getVuelo().getCodigoVuelo()))
                .map(asiento -> this.getInformacion(asiento))
                .collect(Collectors.toList());
    }

    public void comprar(String codigoAsiento) throws AsientoLanchitaNoDisponibleException {
        String codigoVuelo = codigoAsiento.split("-")[0];

        Optional<Vuelo> vuelo = this.vuelos
                .stream()
                .filter(v -> v.getCodigoVuelo().contains(codigoVuelo))
                .findFirst();

        if(vuelo.isPresent()) {
            Optional<Asiento> asiento = this.asientos
                    .stream()
                    .filter(a -> a.getCodigoAsiento().contains(codigoAsiento))
                    .findFirst();

            if(asiento.isPresent()) {
                Asiento asientoComprado = asiento.get();
                if(asientoComprado.getEstado().estaDisponible()) {
                    asientoComprado.setEstado(new EstadoAsientoReservado()); // Seteamos el asiento como RESERVADO
                } else {
                    throw new AsientoLanchitaNoDisponibleException("El asiento ya se encuentra reservado");
                }
            } else {
                throw new AsientoLanchitaNoDisponibleException("El código de asiento no es válido");
            }
        } else {
            throw new AsientoLanchitaNoDisponibleException("El código de vuelo no es válido");
        }
    }

    @Override
    public List<Asiento> getAsientos() {
        return this.asientos
                .stream()
                .filter(asiento -> asiento.getEstado().estaDisponible())
                .map(asiento -> {
                    asiento.calcularPrecioTotal(this.porcentajeImpuestos);
                    return asiento;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Asiento> getSuperOfertas(Usuario usuario) {
        List<Asiento> superOfertas = new ArrayList();

        for (Asiento asiento : this.asientos) {
            if (usuario.puedeVerSuperOferta(asiento)) {
                superOfertas.add(asiento);
            }
        }

        return superOfertas;
    }

    /**
     * Se encarga de mapear los asientos al formato de salida que propone la Aerolinea Lanchita
     * ["EC0344-42","565.60","P","P","D"]
     * */
    private List<String> getInformacion(Asiento asiento) {
        ArrayList<String> asientoDisponible  = new ArrayList<>();
        asiento.calcularPrecioTotal(this.porcentajeImpuestos);

        asientoDisponible.add(asiento.getCodigoAsiento());
        asientoDisponible.add(Double.toString(asiento.getPrecioTotal()));
        asientoDisponible.add(getCodigo(asiento));
        asientoDisponible.add(getCodigoUbicacion(asiento));
        asientoDisponible.add(getEstado(asiento));

        return asientoDisponible;
    }

    private String getEstado(Asiento asiento) {
        String estadoAsiento;
        if(asiento.getEstado().estaDisponible()) {
            estadoAsiento = "D";
        } else {
            estadoAsiento = "R";
        }
        return estadoAsiento;
    }

    private String getCodigoUbicacion(Asiento asiento) {
        String ubicacionAsiento = "";
        if(asiento.getUbicacion() instanceof UbicacionVentanilla) {
            ubicacionAsiento = "V";
        } else if(asiento.getUbicacion() instanceof UbicacionCentro) {
            ubicacionAsiento = "C";
        } else if(asiento.getUbicacion() instanceof UbicacionPasillo) {
            ubicacionAsiento = "P";
        }
        return ubicacionAsiento;
    }

    private String getCodigo(Asiento asiento) {
        String tipoAsiento = "";
        if(asiento instanceof AsientoEjecutivo) {
            tipoAsiento = "E";
        } else if(asiento instanceof AsientoPrimeraClase) {
            tipoAsiento = "P";
        } else if(asiento instanceof AsientoTurista) {
            tipoAsiento = "T";
        }
        return tipoAsiento;
    }
}
