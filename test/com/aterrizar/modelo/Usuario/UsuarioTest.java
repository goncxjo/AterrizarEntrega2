package com.aterrizar.modelo.Usuario;

import com.aterrizar.enumerator.Destino;
import com.aterrizar.exception.TipoUsuarioNoDisponibleException;
import com.aterrizar.modelo.Asiento.AsientoEjecutivo;
import com.aterrizar.modelo.Asiento.EstadoAsientoDisponible;
import com.aterrizar.modelo.Ubicacion;
import com.aterrizar.modelo.Vuelo;
import com.aterrizar.modelo.VueloAsiento.VueloAsiento;
import com.aterrizar.modelo.VueloAsiento.VueloAsientoBuilder;
import com.aterrizar.util.DateHelper;
import org.junit.Test;

import static org.junit.Assert.*;

public class UsuarioTest {

    @Test
    public void actualizarTipo_UsuarioNoRegistrado_SeVuelve_UsuarioEstandar() throws TipoUsuarioNoDisponibleException {
        Usuario usuario = new UsuarioNoRegistrado("Ricardo \"EL COMANDANTE\"", "Fort", 37422007);
        usuario = usuario.actualizarTipo(new UsuarioEstandar());
        assertEquals("No es usuario Estandar", UsuarioEstandar.class, usuario.getClass());
    }

    @Test(expected = TipoUsuarioNoDisponibleException.class)
    public void actualizarTipo_UsuarioEstandar_NoPuedeSer_UsuarioVIP() throws TipoUsuarioNoDisponibleException {
        Usuario usuario = new UsuarioEstandar("Ricardo \"EL COMANDANTE\"", "Fort", 37422007);
        usuario.actualizarTipo(new UsuarioVIP());
    }

    @Test
    public void actualizarTipo_UsuarioEstandar_Puede_Y_Se_Vuelve_UsuarioVIP() throws TipoUsuarioNoDisponibleException {
        Usuario usuario = new UsuarioEstandar("Ricardo \"EL COMANDANTE\"", "Fort", 37422007);

        usuario = usuario.actualizarTipo(new UsuarioVIP());

        assertEquals("No es usuario VIP", UsuarioVIP.class, usuario.getClass());
    }

    @Test(expected = TipoUsuarioNoDisponibleException.class)
    public void actualizarTipo_UsuarioVIP_NoPuedeSer_UsuarioEstandar() throws TipoUsuarioNoDisponibleException {
        Usuario usuario = new UsuarioEstandar("Ricardo \"EL COMANDANTE\"", "Fort", 37422007);

        VueloAsiento vueloAsiento = new VueloAsientoBuilder("Lanchita")
                .agregarTipoAsiento(new AsientoEjecutivo())
                .agregarCodigoAsiento("LCH 005-40")
                .agregarPrecio(50000)
                .agregarUbicacion(Ubicacion.Centro)
                .agregarEstadoAsiento(new EstadoAsientoDisponible())
                .agregarFecha(DateHelper.parseToDate("13/05/2019"))
                .agregarOrigen(Destino.BUE)
                .agregarDestino(Destino.MIA)
                .build();

        usuario.agregarVueloComprado(vueloAsiento);
        usuario.agregarVueloComprado(vueloAsiento);
        usuario.agregarVueloComprado(vueloAsiento);

        usuario.actualizarTipo(new UsuarioEstandar());
    }

    @Test
    public void actualizarTipo_UsuarioVIP_SeVuelve_UsuarioEstandar() throws TipoUsuarioNoDisponibleException {
        Usuario usuario = new UsuarioVIP("Ricardo \"EL COMANDANTE\"", "Fort", 37422007);
        usuario = usuario.actualizarTipo(new UsuarioEstandar());

        assertEquals("No es usuario Estandar", UsuarioEstandar.class, usuario.getClass());
    }
}
