package com.aterrizar.modelo.Web;

import com.aterrizar.modelo.Vuelo.Vuelo;

import java.util.ArrayList;

public interface IWebAterrizar {
    void registrarUsuario(String nombre, String apellido, String DNI);
    ArrayList<Vuelo> buscarVuelo(String origen, String destino, String fechaSalida, String fechaLlegada);
    void comprarPasaje(Vuelo vuelo);
}
