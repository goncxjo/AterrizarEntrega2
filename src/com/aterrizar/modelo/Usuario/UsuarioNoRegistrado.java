package com.aterrizar.modelo.Usuario;

import com.aterrizar.exception.TipoUsuarioNoDisponibleException;

public class UsuarioNoRegistrado extends Usuario {

    public UsuarioNoRegistrado() {
    }

    public UsuarioNoRegistrado(String nombre, String apellido, int DNI) {
        super(nombre, apellido, DNI);
    }

    public UsuarioNoRegistrado(Usuario usuario) {
        super(usuario);
    }

    @Override
    public float getRecargo() { return 20; }

    @Override
    public UsuarioEstandar actualizarTipo(Usuario nuevoTipoUsuario) throws TipoUsuarioNoDisponibleException {
        if(!nuevoTipoUsuario.getClass().equals(UsuarioEstandar.class)) {
            throw new TipoUsuarioNoDisponibleException("No es posible actualizar al tipo de usuario solicitado.");
        }
        return new UsuarioEstandar(this);
    }
}
