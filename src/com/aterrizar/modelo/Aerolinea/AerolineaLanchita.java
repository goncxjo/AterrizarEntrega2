package com.aterrizar.modelo.Aerolinea;

import com.aterrizar.modelo.Asiento.Asiento;
import com.aterrizar.modelo.Usuario.Usuario;

import java.util.ArrayList;

public class AerolineaLanchita implements IAerolineaLanchita {
    private final double porcentaje = 0.15;

    @Override
    public ArrayList<ArrayList<String>> asientosDisponibles(String origen, String fechaSalida, String horaSalida, String destino, String fechaLlegada, String horaLlegada) {
        return null;
    }

    @Override
    public void comprar(String codigoAsiento) {

    }

    private double calcularPrecioAsiento(Usuario usuario, Asiento asiento) {
        return 0;
    }
}
