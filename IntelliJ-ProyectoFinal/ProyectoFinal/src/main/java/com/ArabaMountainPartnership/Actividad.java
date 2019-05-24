package com.ArabaMountainPartnership;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Actividad {

    private String codigo;
    private TipoActividad actividad;
    private TipoDificultad dificultad;
    private LocalDate fecha;
    private String descripcion;
    private double precio;
    private String motivoSuspension;
    private Socio organizador;
    private List<Socio> participantes = new ArrayList<>();

    //constructor para el método recogerActividadesOrganizadas de SocioBD ó actividadesOrganizadasFecha de ActividadBD
    public Actividad(String codigo, String tipo, Date fecha, String descripcion, String dificultad, double precio, String motivoSuspension, Socio socio) {
        this.codigo = codigo;
        TipoActividad ta;
        switch (tipo) {
            case "SALIDA AL MONTE":
                ta = TipoActividad.SALIDAALMONTE;
                break;
            case "ALBERGUE FIN DE SEMANA":
                ta = TipoActividad.ALBERGUEFINDESEMANA;
                break;
            case "REUNIÓN":
                ta = TipoActividad.REUNION;
                break;
            case "COMIDA":
                ta = TipoActividad.COMIDA;
                break;
            default:
                ta = TipoActividad.OTROS;
                break;
        }
        this.actividad = ta;
        String fechaStr = String.valueOf(fecha);
        this.fecha = LocalDate.parse(fechaStr);
        this.descripcion = descripcion;
        TipoDificultad td;
        switch (dificultad) {
            case "ALTA":
                td = TipoDificultad.ALTA;
                break;
            case "MEDIA":
                td = TipoDificultad.MEDIA;
                break;
            default:
                td = TipoDificultad.BAJA;
                break;
        }
        this.dificultad = td;
        this.precio = precio;
        this.motivoSuspension = motivoSuspension;
        this.organizador = socio;
    }

    public Actividad(String codigo, TipoActividad actividad, LocalDate fecha, TipoDificultad dificultad, String descripcion, double precio, Socio organizador) {
        this.codigo = codigo;
        this.actividad = actividad;
        this.fecha = fecha;
        this.dificultad = dificultad;
        this.descripcion = descripcion;
        this.precio = precio;
        this.organizador = organizador;
    }

    public Actividad() {
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
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
