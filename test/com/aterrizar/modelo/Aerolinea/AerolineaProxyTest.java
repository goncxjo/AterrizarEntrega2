package com.aterrizar.modelo.Aerolinea;

import com.aterrizar.exception.AsientoNoDisponibleException;
import com.aterrizar.modelo.Asiento.*;
import com.aterrizar.modelo.FiltroVueloAsiento;
import com.aterrizar.modelo.Ubicacion.UbicacionCentro;
import com.aterrizar.modelo.Ubicacion.UbicacionPasillo;
import com.aterrizar.modelo.Ubicacion.UbicacionVentanilla;
import com.aterrizar.modelo.Usuario.Usuario;
import com.aterrizar.modelo.Usuario.UsuarioNoRegistrado;
import com.aterrizar.modelo.Vuelo.Vuelo;
import com.aterrizar.util.DateHelper;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

@SuppressWarnings("Deprecated")
public class AerolineaProxyTest {
    private AerolineaProxy aerolineaProxy;

    @Before
    public void generarVuelos() {
        AerolineaLanchitaImplementacion aerolineaLanchita = new AerolineaLanchitaImplementacion();

        Vuelo vuelo1 = new Vuelo(
                "AL"
                ,"Buenos Aires"
                , "Miami"
                , DateHelper.parseToDate("13/05/2019")
                , DateHelper.parseToDate("15/05/2019")
        );
        aerolineaLanchita.agregarVuelo(vuelo1);

        aerolineaLanchita.agregarAsiento(new AsientoTurista(vuelo1, 100, new UbicacionVentanilla(), new EstadoAsientoDisponible()));
        aerolineaLanchita.agregarAsiento(new AsientoTurista(vuelo1, 100, new UbicacionPasillo(), new EstadoAsientoReservado()));
        aerolineaLanchita.agregarAsiento(new AsientoPrimeraClase(vuelo1, 300, new UbicacionCentro(), new EstadoAsientoDisponible()));
        aerolineaLanchita.agregarAsiento(new AsientoPrimeraClase(vuelo1, 200, new UbicacionPasillo(), new EstadoAsientoDisponible()));


        Vuelo vuelo2 = new Vuelo(
                "AL"
                ,"Buenos Aires"
                , "Barcelona"
                , DateHelper.parseToDate("17/05/2019")
                , DateHelper.parseToDate("20/05/2019")
        );

        aerolineaLanchita.agregarVuelo(vuelo2);

        aerolineaLanchita.agregarAsiento(new AsientoEjecutivo(vuelo2, 1000, new UbicacionCentro(), new EstadoAsientoDisponible()));
        aerolineaLanchita.agregarAsiento(new AsientoEjecutivo(vuelo2, 1000, new UbicacionCentro(), new EstadoAsientoReservado()));
        aerolineaLanchita.agregarAsiento(new AsientoTurista(vuelo2, 400, new UbicacionVentanilla(), new EstadoAsientoDisponible()));
        aerolineaLanchita.agregarAsiento(new AsientoPrimeraClase(vuelo2, 500, new UbicacionPasillo(), new EstadoAsientoDisponible()));

        aerolineaProxy = new AerolineaProxy(aerolineaLanchita);
    }

    @Test
    public void buscarVuelos_UsuarioEstandar_BuenosAiesBarcelona_TieneVuelosDisponibles() {
        FiltroVueloAsiento filtroVueloAsiento = new FiltroVueloAsiento(
                null
                , null
                , null
                , null
                , new AsientoTurista()
                , null
        );
        List<Asiento> asientos = aerolineaProxy.buscarAsientos(filtroVueloAsiento);

        assertFalse(asientos.isEmpty());
    }

    @Test
    public void buscarVuelos_UsuarioEstandar_BuenosAiesTokio_NoTieneVuelosDisponibles() {
        FiltroVueloAsiento filtroVueloAsiento = new FiltroVueloAsiento(
                "BUE"
                , "TOK"
                , null
                , null
                , new AsientoTurista()
                , new UbicacionVentanilla()
        );
        List<Asiento> asientos = aerolineaProxy.buscarAsientos(filtroVueloAsiento);

        assertTrue(asientos.isEmpty());
    }


    @Test
    public void buscarVuelos_UsuarioEstandar_NoTieneVuelosDisponibles() {
        FiltroVueloAsiento filtroVueloAsiento = new FiltroVueloAsiento(
                "BUE"
                , "TOK"
                , null
                , null
                , new AsientoTurista()
                , new UbicacionVentanilla()
        );
        List<Asiento> asientos = aerolineaProxy.buscarAsientos(filtroVueloAsiento);

        assertTrue(asientos.isEmpty());
    }

    @Test
    public void buscarVuelos_UsuarioNoRegistrado_AsientoQueVale100_TieneRecargo() {
        FiltroVueloAsiento filtroVueloAsiento = new FiltroVueloAsiento(
                "BUE"
                , "MIA"
                , null
                , null
                , new AsientoTurista()
                , new UbicacionVentanilla()
        );
        Usuario usuario = new UsuarioNoRegistrado("Ricardo \"EL COMANDANTE\"", "Fort)", 37422007);

        List<Asiento> asientos = aerolineaProxy.buscarAsientos(filtroVueloAsiento);

        // precio asiento = 100
        // impuestos asiento = 15
        // recargo = 20
        // TOTAL = 135
        assertEquals("El asiento no tiene recargo", 135, Math.round(asientos.get(0).getPrecioTotal() + usuario.getRecargo()));
    }

    @Test
    public void comprar_UsuarioEstandar_ReservaUnAsientoDisponible() throws AsientoNoDisponibleException {
        FiltroVueloAsiento filtroVueloAsiento = new FiltroVueloAsiento(
                "BUE"
                , "MIA"
                , null
                , null
                , new AsientoTurista()
                , new UbicacionVentanilla()
        );
        List<Asiento> asientos = aerolineaProxy.buscarAsientos(filtroVueloAsiento);
        Asiento asiento = asientos.get(0);
        this.aerolineaProxy.comprar(asiento.getCodigoAsiento());

        assertFalse("No se ha reservado el asiento", asiento.getEstado().estaDisponible());
    }

    @Test
    public void comprar_UsuarioEstandar_ReservaUnAsientoDisponibleYSeEliminaDelVuelo() throws AsientoNoDisponibleException {
        FiltroVueloAsiento filtroVueloAsiento = new FiltroVueloAsiento(
                "BUE"
                , "MIA"
                , null
                , null
                , new AsientoTurista()
                , new UbicacionVentanilla()
        );
        List<Asiento> asientos = aerolineaProxy.buscarAsientos(filtroVueloAsiento);
        Asiento asiento = asientos.get(0);
        this.aerolineaProxy.comprar(asiento.getCodigoAsiento());

        List<Asiento> asientosRegistradosDespuesDeComprar = aerolineaProxy.buscarAsientos(filtroVueloAsiento);
        assertFalse("No se ha eliminado el asiento", asientosRegistradosDespuesDeComprar.contains(asiento));
    }
}
