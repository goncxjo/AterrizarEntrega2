package com.aterrizar.modelo.Usuario;

public class UsuarioNoRegistrado extends Usuario {

    public UsuarioNoRegistrado(String nombre, String apellido, int DNI) {
        super(nombre, apellido, DNI);
    }

    @Override
    public float getRecargo() { return 20; }
}
