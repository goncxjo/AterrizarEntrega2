package com.aterrizar.modelo.Aerolinea;

import com.aterrizar.exception.AsientoLanchitaNoDisponibleException;
import java.util.List;

public abstract class AerolineaLanchita extends Aerolinea {
    abstract List<List<String>> asientosDisponibles(String origen, String fechaSalida, String horaSalida, String destino, String fechaLlegada, String horaLlegada);
    public abstract void comprar(String codigoAsiento) throws AsientoLanchitaNoDisponibleException;
}
