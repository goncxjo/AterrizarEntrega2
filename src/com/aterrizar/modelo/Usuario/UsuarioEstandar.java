package com.aterrizar.modelo.Usuario;

public class UsuarioEstandar extends Usuario {

    public UsuarioEstandar(String nombre, String apellido, int DNI) {
        super(nombre, apellido, DNI);
    }

    public boolean puedeSerUsuarioVIP() {
        boolean esVIP = false;
        if(!this.asientosComprados.isEmpty()) {
            esVIP = this.asientosComprados.stream().mapToDouble(a -> a.getPrecioTotal() + this.getRecargo()).sum() > 100000;
        }
        return esVIP;
    }
}
