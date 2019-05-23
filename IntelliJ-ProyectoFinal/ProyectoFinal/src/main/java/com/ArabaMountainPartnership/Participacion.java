package com.ArabaMountainPartnership;

public class Participacion {

    private Socio socio;
    private Actividad actividad;

    public Participacion(Socio socio, Actividad actividad) {
        this.socio = socio;
        this.actividad = actividad;
    }

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }
}
