package com.aterrizar.modelo.Asiento;

import com.aterrizar.modelo.Usuario.Usuario;

public class Reservado implements Estado {
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
