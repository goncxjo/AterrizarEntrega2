package com.aterrizar.modelo;

import com.aterrizar.modelo.Asiento.AsientoTurista;
import com.aterrizar.modelo.Ubicacion.UbicacionVentanilla;
import com.aterrizar.modelo.Usuario.UsuarioEstandar;
import com.aterrizar.modelo.Usuario.Usuario;
import com.aterrizar.modelo.Vuelo.FiltroVuelo;
import com.aterrizar.modelo.Vuelo.Vuelo;
import org.junit.Test;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

@SuppressWarnings("Deprecated")
public class ComunicadorTest {

    @Test
    public void buscarVuelos() {
        Comunicador comunicador = new Comunicador();

        FiltroVuelo filtroVuelo = new FiltroVuelo(
                "BUE",
                "BAR",
                new Date(1019, Calendar.MAY, 15),
                new Date(1019, Calendar.MAY, 25),
                new AsientoTurista(),
                new UbicacionVentanilla()
        );
        Usuario usuario = new UsuarioEstandar("Sofia", "Dusseldorf", 37422007);

        List<Vuelo> vuelos = comunicador.buscarVuelos(filtroVuelo, usuario);

        assertTrue(vuelos.isEmpty()); // TODO: Como todavía no se implementaron los métodos, la lista siempre estará vacía
    }

}