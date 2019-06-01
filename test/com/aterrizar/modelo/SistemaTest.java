package com.aterrizar.modelo;

import com.aterrizar.enumerator.Destino;
import com.aterrizar.exception.AsientoNoDisponibleException;
import com.aterrizar.modelo.Aerolinea.AerolineaLanchita;
import com.aterrizar.modelo.Aerolinea.AerolineaProxy;
import com.aterrizar.modelo.Asiento.*;
import com.aterrizar.modelo.Usuario.Usuario;
import com.aterrizar.modelo.Usuario.UsuarioNoRegistrado;
import com.aterrizar.modelo.VueloAsiento.VueloAsiento;
import com.aterrizar.modelo.VueloAsiento.VueloAsientoFilter;
import com.aterrizar.util.DateHelper;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class SistemaTest {
	private Sistema sistema;
	private AerolineaProxy aerolineaProxy;
	
	@Before
    public void generarVuelos() {
        AerolineaLanchita aerolineaLanchita = mock(AerolineaLanchita.class);

		when(aerolineaLanchita.asientosDisponibles(anyString(), anyString(), anyString(), anyString()))
				.thenReturn(Arrays.asList(
						Arrays.asList("LCH 344-42","1000.00","E","C","D")
						, Arrays.asList("LCH 344-46","400.00","T","V","D")
				));

        aerolineaProxy = new AerolineaProxy(aerolineaLanchita);
        sistema = new Sistema(aerolineaProxy);
    }

	@Test
	public void buscarAsientos_UnUsuarioBuscaAsientosYEncuentra() {
		Usuario usuario = new UsuarioNoRegistrado("Ricardo \"EL COMANDANTE\"", "Fort", 37422007);
		VueloAsientoFilter vueloAsientoFilter = new VueloAsientoFilter(
                Destino.BUE
                , Destino.MIA
                , "20190531"
                , new AsientoTurista()
                , null
        );

		List<VueloAsiento> vueloAsientos = sistema.buscarAsientos(vueloAsientoFilter, usuario);

		assertFalse(vueloAsientos.isEmpty());
	}

	@Test
	public void comprarAsiento_UnUsuarioCompraUnAsiento() throws AsientoNoDisponibleException {
		AerolineaLanchita aerolineaLanchita = mock(AerolineaLanchita.class);

		when(aerolineaLanchita.asientosDisponibles(anyString(), anyString(), anyString(), anyString()))
				.thenReturn(Arrays.asList(
						Arrays.asList("LCH 344-46","400.00","T","V","D")
				));

		Usuario usuario = new UsuarioNoRegistrado("Ricardo \"EL COMANDANTE\"", "Fort", 37422007);
		VueloAsientoFilter vueloAsientoFilter = new VueloAsientoFilter(
                Destino.BUE
                , Destino.MIA
				, "20190531"
                , new AsientoTurista()
                , Ubicacion.Ventanilla
        );

		VueloAsiento vueloAsiento = sistema.buscarAsientos(vueloAsientoFilter, usuario).get(0);
		this.sistema.comprarAsiento(vueloAsiento, usuario, vueloAsientoFilter);
		List<VueloAsiento> asientosLuegoDeComprar = sistema.buscarAsientos(vueloAsientoFilter, usuario);

		assertFalse("El usuario no ha podido comprar el asiento.", asientosLuegoDeComprar.contains(vueloAsiento));
	}
	
}
