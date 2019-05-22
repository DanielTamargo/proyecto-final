package com.ArabaMountainPartnership;

public class Usuario {

    private String nombre;
    private String contrasenya;
    private String socio;
    // decidimos guardar solo el código del socio (como String) para luego mediante un getter
    // y una búsqueda con sql poder sacar y componer el socio si es necsario

    // constructor para la primera carga de UsuarioBD (primera carga del programa)
    public Usuario(String nombre, String contrasenya, String socio) {
        this.nombre = nombre;
        this.contrasenya = contrasenya;
        this.socio = socio;
    }

    public Usuario() {
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

    public String getSocio() {
        return socio;
    }

    public void setSocio(String socio) {
        this.socio = socio;
    }
}
