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
    private List<Socio> socios = new ArrayList<>();

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
        this.fechaInicio = fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        this.fechaFin = fechaFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
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

    public Cargo() {
    }

    public void addSocio(Socio socio) {
        socios.add(socio);
    }

    public List<Socio> getSocios() {
        return socios;
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



