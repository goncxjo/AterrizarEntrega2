package com.aterrizar.enumerator;

public enum Aerolinea {
    Lanchita("Aerolinea Lanchita", "LCH");

    private String codigo;

    Aerolinea(String nombre, String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public boolean equals(String codigo) {
        return this.codigo.equals(codigo);
    }
}
