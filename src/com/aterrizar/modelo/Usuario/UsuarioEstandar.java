package com.aterrizar.modelo.Usuario;

import com.aterrizar.modelo.Asiento.Asiento;
import com.aterrizar.modelo.Asiento.AsientoEjecutivo;
import com.aterrizar.modelo.Asiento.AsientoPrimeraClase;

public class UsuarioEstandar extends Usuario {

    public UsuarioEstandar(String nombre, String apellido, int DNI) {
        super(nombre, apellido, DNI);
    }

    public boolean esUsuarioVIP() {
        boolean esVIP = false;
        if(!this.asientosComprados.isEmpty()) {
            esVIP = this.asientosComprados.stream().mapToDouble(a -> a.getPrecio()).sum() > 100000;
        }
        return esVIP;
    }

    public boolean puedeVerSuperOferta(Asiento asiento, double precioTotal) {
        boolean puedeVerSuperOferta = false;
        if(this.esUsuarioVIP()) {
            if((asiento instanceof AsientoPrimeraClase && precioTotal < 8000) || (asiento instanceof AsientoEjecutivo && precioTotal < 4000)) {
                puedeVerSuperOferta = true;
            }
        }
        return puedeVerSuperOferta;
    }
}
