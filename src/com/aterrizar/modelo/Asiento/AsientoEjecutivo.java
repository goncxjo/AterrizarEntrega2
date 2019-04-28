package com.aterrizar.modelo.Asiento;

import com.aterrizar.modelo.Usuario.Usuario;

public class AsientoEjecutivo extends Asiento {

    @Override
    public boolean esSuperOferta() { return getPrecioTotal() < 4000; }
}
