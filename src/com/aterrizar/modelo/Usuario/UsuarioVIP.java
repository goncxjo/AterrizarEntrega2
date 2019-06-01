package com.aterrizar.modelo.Usuario;

import com.aterrizar.exception.TipoUsuarioNoDisponibleException;
import com.aterrizar.modelo.Asiento.Asiento;
import com.aterrizar.modelo.Asiento.AsientoEjecutivo;
import com.aterrizar.modelo.Asiento.AsientoPrimeraClase;

public class UsuarioVIP extends Usuario {

    public UsuarioVIP() {
    }

    public UsuarioVIP(String nombre, String apellido, int DNI) {
        super(nombre, apellido, DNI);
    }

    public UsuarioVIP(Usuario usuario) {
        super(usuario);
    }

    public boolean puedeVerSuperOferta(Asiento asiento) {
        boolean puedeVerSuperOferta = false;

        if((asiento instanceof AsientoPrimeraClase && asiento.getPrecio() + this.getRecargo() < 8000) || (asiento instanceof AsientoEjecutivo && asiento.getPrecio() + this.getRecargo() < 4000)) {
            puedeVerSuperOferta = true;
        }

        return puedeVerSuperOferta;
    }

    @Override
    public UsuarioEstandar actualizarTipo(Usuario nuevoTipoUsuario) throws TipoUsuarioNoDisponibleException {
        UsuarioEstandar usuario = new UsuarioEstandar(this);

        if(!nuevoTipoUsuario.getClass().equals(UsuarioEstandar.class)) {
            throw new TipoUsuarioNoDisponibleException("No es posible actualizar al tipo de usuario solicitado.");
        } else if(usuario.puedeSerUsuarioVIP()) {
            throw new TipoUsuarioNoDisponibleException("No usuario no puede ser degredado a Estandar");
        }

        return usuario;
    }
}
