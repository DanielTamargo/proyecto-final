package com.ArabaMountainPartnership;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Esta clase guardará los datos de cada objeto Actividad.
 */
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

    /**
     * Constructor para el método recogerActividadesOrganizadas de SocioBD ó activiadesOrganizadasFecha de ActividadBD
     * @param codigo String
     * @param tipo String
     * @param fecha Date
     * @param descripcion String
     * @param dificultad String
     * @param precio double
     * @param motivoSuspension String
     * @param socio Socio
     *
     */
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

    /**
     * Constructor general
     * @param codigo String
     * @param actividad TipoActividad
     * @param fecha LocalDate
     * @param dificultad TipoDificultad
     * @param descripcion String
     * @param precio double
     * @param organizador Socio
     */
    public Actividad(String codigo, TipoActividad actividad, LocalDate fecha, TipoDificultad dificultad, String descripcion, double precio, Socio organizador) {
        this.codigo = codigo;
        this.actividad = actividad;
        this.fecha = fecha;
        this.dificultad = dificultad;
        this.descripcion = descripcion;
        this.precio = precio;
        this.organizador = organizador;
    }

    /**
     * Constructor vacío
     */
    public Actividad() {
    }

    @Override
    public String toString() {
        return "Cod: " + codigo + ". " + organizador;
    }

    /**
     * Getter de fecha
     * @return fecha
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Setter de fecha
     * @param fecha LocalDate
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * Método para añadir un participante a la lista participantes
     * @param socio Socio
     */
    public void addParticipante(Socio socio) {
        participantes.add(socio);
    }

    /**
     * Getter de la lista participantes
     * @return participantes
     */
    public List<Socio> getParticipantes() {
        return participantes;
    }

    /**
     * Getter de codigo
     * @return codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Getter de organizador
     * @return organizador
     */
    public Socio getOrganizador() {
        return organizador;
    }

    /**
     * Setter de organizador
     * @param organizador Socio
     */
    public void setOrganizador(Socio organizador) {
        this.organizador = organizador;
    }

    /**
     * Setter de codigo
     * @param codigo String
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Getter de actividad (TipoActividad)
     * @return actividad
     */
    public TipoActividad getActividad() {
        return actividad;
    }

    /**
     * Setter de actividad (TipoActividad)
     * @param actividad TipoActividad
     */
    public void setActividad(TipoActividad actividad) {
        this.actividad = actividad;
    }

    /**
     * Getter de dificultad (TipoDificultad)
     * @return dificultad
     */
    public TipoDificultad getDificultad() {
        return dificultad;
    }

    /**
     * Setter dificultad (TipoDificultad)
     * @param dificultad TipoDificultad
     */
    public void setDificultad(TipoDificultad dificultad) {
        this.dificultad = dificultad;
    }

    /**
     * Getter de descripcion
     * @return descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Setter de descripcion
     * @param descripcion String
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Getter de precio
     * @return precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Setter de precio
     * @param precio Double
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Getter de motivoSuspension
     * @return motivoSuspension
     */
    public String getMotivoSuspension() {
        return motivoSuspension;
    }

    /**
     * Setter de motivoSuspension
     * @param motivoSuspension String
     */
    public void setMotivoSuspension(String motivoSuspension) {
        this.motivoSuspension = motivoSuspension;
    }
}
