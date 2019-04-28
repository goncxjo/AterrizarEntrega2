package com.aterrizar.modelo.Asiento;

import com.aterrizar.modelo.Usuario.Usuario;

public class AsientoPrimeraClase extends Asiento {

    @Override
    public boolean esSuperOferta() { return getPrecioTotal() < 8000; }
}
