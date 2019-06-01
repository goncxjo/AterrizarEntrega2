package com.aterrizar.modelo.Aerolinea;

import com.aterrizar.enumerator.Destino;
import com.aterrizar.exception.AsientoLanchitaNoDisponibleException;
import com.aterrizar.exception.AsientoNoDisponibleException;
import com.aterrizar.modelo.Asiento.Asiento;
import com.aterrizar.modelo.Asiento.AsientoTurista;
import com.aterrizar.modelo.Ubicacion;
import com.aterrizar.modelo.Usuario.Usuario;
import com.aterrizar.modelo.Usuario.UsuarioEstandar;
import com.aterrizar.modelo.Usuario.UsuarioNoRegistrado;
import com.aterrizar.modelo.VueloAsiento.VueloAsiento;
import com.aterrizar.modelo.VueloAsiento.VueloAsientoFilter;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class AerolineaProxyTest {
    private AerolineaProxy aerolineaProxy;

    @Test
    public void buscarVuelos_UsuarioEstandar_BuenosAiresBarcelona_TieneVuelosDisponibles() {
        AerolineaLanchita aerolineaLanchita = mock(AerolineaLanchita.class);

        when(aerolineaLanchita.asientosDisponibles(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(Arrays.asList(
                        Arrays.asList("LCH 344-42","1000.00","E","C","D")
                        , Arrays.asList("LCH 344-46","400.00","T","V","D")
                ));

        aerolineaProxy = new AerolineaProxy(aerolineaLanchita);

        VueloAsientoFilter filtro = new VueloAsientoFilter(
                Destino.BUE
                , Destino.BAR
                , "20190510"
                , null
                , null
        );

        Usuario usuario = new UsuarioNoRegistrado("Ricardo \"EL COMANDANTE\"", "Fort)", 37422007);

        List<VueloAsiento> vueloAsientos = aerolineaProxy.buscarAsientos(filtro, usuario);

        assertFalse(vueloAsientos.isEmpty());
    }

    @Test
    public void buscarVuelos_UsuarioEstandar_BuenosAiesTokio_NoTieneVuelosDisponibles() {
        AerolineaLanchita aerolineaLanchita = mock(AerolineaLanchita.class);

        when(aerolineaLanchita.asientosDisponibles(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(Arrays.asList());

        aerolineaProxy = new AerolineaProxy(aerolineaLanchita);

        VueloAsientoFilter filtro = new VueloAsientoFilter(
                Destino.BUE
                , Destino.TOK
                , "20190510"
                , new AsientoTurista()
                , Ubicacion.Ventanilla
        );

        Usuario usuario = new UsuarioNoRegistrado("Ricardo \"EL COMANDANTE\"", "Fort)", 37422007);

        List<VueloAsiento> vueloAsientos = aerolineaProxy.buscarAsientos(filtro, usuario);

        assertTrue(vueloAsientos.isEmpty());
    }

    @Test
    public void buscarVuelos_UsuarioNoRegistrado_AsientoQueVale100_TieneRecargo() {
        AerolineaLanchita aerolineaLanchita = mock(AerolineaLanchita.class);

        when(aerolineaLanchita.asientosDisponibles(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(Arrays.asList(
                        Arrays.asList("LCH 622-12","115.00","T","V","D")
                ));

        aerolineaProxy = new AerolineaProxy(aerolineaLanchita);

        VueloAsientoFilter filtro = new VueloAsientoFilter(
                Destino.BUE
                , Destino.MIA
                , "20190513"
                , new AsientoTurista()
                , Ubicacion.Ventanilla
        );
        Usuario usuario = new UsuarioNoRegistrado("Ricardo \"EL COMANDANTE\"", "Fort)", 37422007);

        List<VueloAsiento> vueloAsientos = aerolineaProxy.buscarAsientos(filtro, usuario);
        Double precioTotal = Double.valueOf(Math.round(vueloAsientos.get(0).getAsiento().getPrecio()));

        // precio asiento = 100
        // impuestos asiento = 15
        // recargo = 20
        // TOTAL = 135
        System.out.println(precioTotal);
        assertTrue("El asiento no tiene recargo", precioTotal.equals(135.00));
    }

    @Test
    public void comprar_UsuarioEstandar_ReservaUnAsientoDisponible() throws AsientoNoDisponibleException {
        String codigoAsiento = "LCH 622-12";

        AerolineaLanchita aerolineaLanchita = mock(AerolineaLanchita.class);

        when(aerolineaLanchita.asientosDisponibles(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(Arrays.asList(
                        Arrays.asList(codigoAsiento,"1000.00","T","V","D")
                ));

        doAnswer(invocationOnMock -> {
            when(aerolineaLanchita.asientosDisponibles(anyString(), anyString(), anyString(), anyString()))
                    .thenAnswer(i -> Arrays.asList(Arrays.asList(invocationOnMock.getArguments()[0],"1000.00","T","V","R")));
            this.aerolineaProxy = new AerolineaProxy(aerolineaLanchita);
            return null;
        }).when(aerolineaLanchita).comprar(codigoAsiento);

        aerolineaProxy = new AerolineaProxy(aerolineaLanchita);

        VueloAsientoFilter filtro = new VueloAsientoFilter(
                Destino.BUE
                , Destino.MIA
                , "20190513"
                , new AsientoTurista()
                , Ubicacion.Ventanilla
        );
        Usuario usuario = new UsuarioEstandar("Ricardo \"EL COMANDANTE\"", "Fort)", 37422007);

        List<VueloAsiento> vueloAsientosAntesDeComprar = aerolineaProxy.buscarAsientos(filtro, usuario);
        this.aerolineaProxy.comprar(codigoAsiento);
        List<VueloAsiento> vueloAsientosDespuesDeComprar = aerolineaProxy.buscarAsientos(filtro, usuario);

        Asiento asientoAntesDeComprar = vueloAsientosAntesDeComprar.get(0).getAsiento();
        Asiento asientoDespuesDeComprar = vueloAsientosDespuesDeComprar.get(0).getAsiento();

        assertTrue("El asiento está disponible", asientoAntesDeComprar.getEstadoAsiento().estaDisponible() != asientoDespuesDeComprar.getEstadoAsiento().estaDisponible());
    }

    @Test
    public void comprar_UsuarioEstandar_ReservaUnAsientoDisponibleYSeEliminaDelVuelo() throws AsientoNoDisponibleException {
        String codigoAsiento = "LCH 622-12";

        AerolineaLanchita aerolineaLanchita = mock(AerolineaLanchita.class);

        when(aerolineaLanchita.asientosDisponibles(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(Arrays.asList(
                        Arrays.asList(codigoAsiento,"1000.00","T","V","D")
                ));

        doAnswer(invocationOnMock -> {
            when(aerolineaLanchita.asientosDisponibles(anyString(), anyString(), anyString(), anyString()))
                    .thenAnswer(i -> Arrays.asList());
            this.aerolineaProxy = new AerolineaProxy(aerolineaLanchita);
            return null;
        }).when(aerolineaLanchita).comprar(codigoAsiento);

        aerolineaProxy = new AerolineaProxy(aerolineaLanchita);

        VueloAsientoFilter filtro = new VueloAsientoFilter(
                Destino.BUE
                , Destino.MIA
                , "20190513"
                , new AsientoTurista()
                , Ubicacion.Ventanilla
        );
        Usuario usuario = new UsuarioEstandar("Ricardo \"EL COMANDANTE\"", "Fort)", 37422007);

        List<VueloAsiento> vueloAsientosAntesDeComprar = aerolineaProxy.buscarAsientos(filtro, usuario);
        this.aerolineaProxy.comprar(codigoAsiento);
        List<VueloAsiento> vueloAsientosDespuesDeComprar = aerolineaProxy.buscarAsientos(filtro, usuario);

        assertTrue("El asiento aún existe", !vueloAsientosAntesDeComprar.isEmpty() && vueloAsientosDespuesDeComprar.isEmpty());
    }

    @Test(expected = AsientoNoDisponibleException.class)
    public void comprar_UsuarioEstandar_NoPuedeComprarUnAsientoReservado() throws AsientoNoDisponibleException {
        AerolineaLanchita aerolineaLanchita = mock(AerolineaLanchita.class);
        doThrow(new AsientoLanchitaNoDisponibleException("El asiento ya se encuentra reservado")).when(aerolineaLanchita).comprar(anyString());
        aerolineaProxy = new AerolineaProxy(aerolineaLanchita);

        this.aerolineaProxy.comprar("LCH 622-12");
    }
}
