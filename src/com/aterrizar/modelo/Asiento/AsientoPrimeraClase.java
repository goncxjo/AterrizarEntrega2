package com.aterrizar.modelo.Asiento;

import com.aterrizar.modelo.Usuario.Usuario;

public class PrimeraClase extends Asiento {

    @Override
    public boolean esSuperOferta() { return getPrecioTotal() < 8000; }
}
