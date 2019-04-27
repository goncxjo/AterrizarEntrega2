package com.aterrizar.modelo.Asiento;

import com.aterrizar.modelo.Usuario.Usuario;

public class Ejecutivo extends Asiento {

    @Override
    public boolean esSuperOferta(float porcentaje, Usuario usuario) { return getPrecioTotal(porcentaje, usuario) < 4000; }
}
