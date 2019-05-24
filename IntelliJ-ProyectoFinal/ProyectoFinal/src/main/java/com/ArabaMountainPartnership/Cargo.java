package com.ArabaMountainPartnership;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cargo {

    private TipoCargo tipo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Junta junta;
    private Socio socio;

    //constructor para cargosJuntaActiva
    public Cargo(String tipo, Date fechaFin, String codSoc) {
        switch (tipo) {
            case "PRESIDENTE":
                this.tipo = TipoCargo.PRESIDENTE;
                break;
            case "VICEPRESIDENTE":
                this.tipo = TipoCargo.VICEPRESIDENTE;
                break;
            case "SECRETARIO":
                this.tipo = TipoCargo.SECRETARIO;
                break;
            case "TESORERO":
                this.tipo = TipoCargo.TESORERO;
                break;
            default:
                this.tipo = TipoCargo.VOCAL;
                break;
        }
        String fechaFinStr = String.valueOf(fechaFin);
        this.fechaFin = LocalDate.parse(fechaFinStr);
        this.socio = SocioBD.socio(codSoc);
    }

    //constructor para CargoBD cargosBasic
    public Cargo(String tipo, String codSoc) {
        switch (tipo) {
            case "PRESIDENTE":
                this.tipo = TipoCargo.PRESIDENTE;
                break;
            case "VICEPRESIDENTE":
                this.tipo = TipoCargo.VICEPRESIDENTE;
                break;
            case "SECRETARIO":
                this.tipo = TipoCargo.SECRETARIO;
                break;
            case "TESORERO":
                this.tipo = TipoCargo.TESORERO;
                break;
            default:
                this.tipo = TipoCargo.VOCAL;
                break;
        }
        this.socio = SocioBD.socio(codSoc);
    }

    //constructor para CargoBD ó similares
    public Cargo(String tipo, Date fechaInicio, Date fechaFin) {
        switch (tipo) {
            case "PRESIDENTE":
                this.tipo = TipoCargo.PRESIDENTE;
                break;
            case "VICEPRESIDENTE":
                this.tipo = TipoCargo.VICEPRESIDENTE;
                break;
            case "SECRETARIO":
                this.tipo = TipoCargo.SECRETARIO;
                break;
            case "TESORERO":
                this.tipo = TipoCargo.TESORERO;
                break;
            default:
                this.tipo = TipoCargo.VOCAL;
                break;
        }
        String fechaInicioStr = String.valueOf(fechaInicio);
        this.fechaInicio = LocalDate.parse(fechaInicioStr);
        String fechaFinStr = String.valueOf(fechaFin);
        this.fechaFin = LocalDate.parse(fechaFinStr);
    }

    //constructor dando por hecho que puede que el cargo exista siempre y la junta se vaya reemplazando (usando setJunta)
    public Cargo(TipoCargo tipo, LocalDate fechaInicio, LocalDate fechaFin) {
        this.tipo = tipo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    //constructor dando por hecho que cada vez que se crea una junta se crean de nuevo los cargos
    public Cargo(TipoCargo tipo, LocalDate fechaInicio, LocalDate fechaFin, Junta junta) {
        this.tipo = tipo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.junta = junta;
    }

    public Cargo(TipoCargo tipo, LocalDate fechaInicio, LocalDate fechaFin, Junta junta, Socio socio) {
        this.tipo = tipo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.junta = junta;
        this.socio = socio;
    }

    public Cargo() {
    }

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public TipoCargo getTipo() {
        return tipo;
    }

    public void setTipo(TipoCargo tipo) {
        this.tipo = tipo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Junta getJunta() {
        return junta;
    }

    public void setJunta(Junta junta) {
        this.junta = junta;
    }
}



