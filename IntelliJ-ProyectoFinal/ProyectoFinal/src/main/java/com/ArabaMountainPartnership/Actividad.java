package com.ArabaMountainPartnership;

import java.util.ArrayList;
import java.util.List;

public class Actividad {

    private String codigo;
    private TipoActividad actividad;
    private TipoDificultad dificultad;
    private String descripcion;
    private double precio;
    private String motivoSuspension;
    private Socio organizador;
    private List<Socio> participantes = new ArrayList<>();

    public Actividad(String codigo, TipoActividad actividad, TipoDificultad dificultad, String descripcion, double precio, Socio organizador) {
        this.codigo = codigo;
        this.actividad = actividad;
        this.dificultad = dificultad;
        this.descripcion = descripcion;
        this.precio = precio;
        this.organizador = organizador;
    }

    public Actividad() {
    }

    public void addParticipante(Socio socio) {
        participantes.add(socio);
    }

    public List<Socio> getParticipantes() {
        return participantes;
    }

    public String getCodigo() {
        return codigo;
    }

    public Socio getOrganizador() {
        return organizador;
    }

    public void setOrganizador(Socio organizador) {
        this.organizador = organizador;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public TipoActividad getActividad() {
        return actividad;
    }

    public void setActividad(TipoActividad actividad) {
        this.actividad = actividad;
    }

    public TipoDificultad getDificultad() {
        return dificultad;
    }

    public void setDificultad(TipoDificultad dificultad) {
        this.dificultad = dificultad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getMotivoSuspension() {
        return motivoSuspension;
    }

    public void setMotivoSuspension(String motivoSuspension) {
        this.motivoSuspension = motivoSuspension;
    }
}
