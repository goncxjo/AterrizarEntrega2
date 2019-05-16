package com.aterrizar.modelo.Usuario;

import com.aterrizar.modelo.Asiento.Asiento;
import com.aterrizar.modelo.Asiento.AsientoEjecutivo;
import com.aterrizar.modelo.Asiento.AsientoPrimeraClase;

public class UsuarioVIP extends Usuario {
    public UsuarioVIP(String nombre, String apellido, int DNI) {
        super(nombre, apellido, DNI);
    }


    public boolean puedeVerSuperOferta(Asiento asiento) {
        boolean puedeVerSuperOferta = false;

        if((asiento instanceof AsientoPrimeraClase && asiento.getPrecioTotal() + this.getRecargo() < 8000) || (asiento instanceof AsientoEjecutivo && asiento.getPrecioTotal() + this.getRecargo() < 4000)) {
            puedeVerSuperOferta = true;
        }

        return puedeVerSuperOferta;
    }
}
