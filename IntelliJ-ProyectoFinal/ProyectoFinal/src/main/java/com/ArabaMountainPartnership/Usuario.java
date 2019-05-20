package com.ArabaMountainPartnership;

public class Usuario {

    private String nombre;
    private String contrasenya;
    private Socio socio;

    public Usuario(String nombre, String contrasenya, Socio socio) {
        this.nombre = nombre;
        this.contrasenya = contrasenya;
        this.socio = socio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }
}
