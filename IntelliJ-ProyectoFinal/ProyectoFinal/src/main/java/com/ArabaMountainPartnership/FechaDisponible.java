package com.ArabaMountainPartnership;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class FechaDisponible {

    private LocalDate fecha;
    private Socio socio;

    public FechaDisponible(LocalDate fecha, Socio socio) {
        this.fecha = fecha;
        this.socio = socio;
    }

    public FechaDisponible(Date fecha, Socio socio) {
        this.fecha = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        this.socio = socio;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

}
