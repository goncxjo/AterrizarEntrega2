package com.aterrizar.modelo;

import com.aterrizar.exception.AsientoNoDisponibleException;
import com.aterrizar.modelo.Aerolinea.AerolineaLanchitaImplementacion;
import com.aterrizar.modelo.Aerolinea.AerolineaProxy;
import com.aterrizar.modelo.Asiento.*;
import com.aterrizar.modelo.Ubicacion.UbicacionCentro;
import com.aterrizar.modelo.Ubicacion.UbicacionPasillo;
import com.aterrizar.modelo.Ubicacion.UbicacionVentanilla;
import com.aterrizar.modelo.Usuario.Usuario;
import com.aterrizar.modelo.Usuario.UsuarioNoRegistrado;
import com.aterrizar.modelo.Vuelo.Vuelo;
import com.aterrizar.util.DateHelper;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class SistemaTest {
	private Sistema sistema;
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
        sistema = new Sistema(aerolineaProxy);
    }
    
	@Test
	public void buscarAsientos_UnUsuarioBuscaAsientosYEncuentra() {
		Usuario usuario = new UsuarioNoRegistrado("Ricardo \"EL COMANDANTE\"", "Fort", 37422007);
		FiltroVueloAsiento filtroVueloAsiento = new FiltroVueloAsiento(
                "BUE"
                , "MIA"
                , null
                , null
                , new AsientoTurista()
                , null
        );
		
		List<Asiento> asientos = sistema.buscarAsientos(filtroVueloAsiento, usuario);
		
		assertFalse(asientos.isEmpty());
	}
	
	@Test
	public void comprarAsiento_UnUsuarioCompraUnAsiento() throws AsientoNoDisponibleException {
		Usuario usuario = new UsuarioNoRegistrado("Ricardo \"EL COMANDANTE\"", "Fort", 37422007);
		FiltroVueloAsiento filtroVueloAsiento = new FiltroVueloAsiento(
                "BUE"
                , "MIA"
                , null
                , null
                , new AsientoTurista()
                , new UbicacionVentanilla()
        );
		
		Asiento asiento = sistema.buscarAsientos(filtroVueloAsiento, usuario).get(0);		
		this.sistema.comprarAsiento(asiento, usuario, filtroVueloAsiento);		
		List<Asiento> asientosLuegoDeComprar = sistema.buscarAsientos(filtroVueloAsiento, usuario);
        
		assertFalse("El usuario no ha podido comprar el asiento.", asientosLuegoDeComprar.contains(asiento));
	}
	
}
