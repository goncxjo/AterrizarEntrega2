package com.aterrizar.modelo.Aerolinea;

import java.util.ArrayList;

public interface IAerolineaLanchita {
    ArrayList<ArrayList<String>> asientosDisponibles(String origen, String fechaSalida, String horaSalida, String destino, String fechaLlegada, String horaLlegada);
    void comprar(String codigoAsiento);
}
