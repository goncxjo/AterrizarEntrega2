package com.aterrizar.modelo.Usuario;

import com.aterrizar.exception.TipoUsuarioNoDisponibleException;

public class UsuarioEstandar extends Usuario {

    public UsuarioEstandar() {
    }

    public UsuarioEstandar(String nombre, String apellido, int DNI) {
        super(nombre, apellido, DNI);
    }

    public UsuarioEstandar(Usuario usuario) {
        super(usuario);
    }

    public boolean puedeSerUsuarioVIP() {
        boolean esVIP = false;
        if(!this.asientosComprados.isEmpty()) {
            esVIP = this.asientosComprados.stream().mapToDouble(va -> va.getAsiento().getPrecio() + this.getRecargo()).sum() > 100000;
        }
        return esVIP;
    }

    @Override
    public UsuarioVIP actualizarTipo(Usuario nuevoTipoUsuario) throws TipoUsuarioNoDisponibleException {

        if(!nuevoTipoUsuario.getClass().equals(UsuarioVIP.class)) {
            throw new TipoUsuarioNoDisponibleException("No es posible actualizar al tipo de usuario solicitado.");
        } else if(!puedeSerUsuarioVIP()) {
            throw new TipoUsuarioNoDisponibleException("No usuario no cumple con las condiciones para ser VIP");
        }

        return new UsuarioVIP(this);
    }
}
