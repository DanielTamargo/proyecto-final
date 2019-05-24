package com.ArabaMountainPartnership;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cuota {

    private double importe;
    private LocalDate anyoValidez;
    private List<Socio> socios = new ArrayList<>();

    //constructor CuotaBD
    public Cuota(double importe, Date anyoValidez) {
        this.importe = importe;
        String anyoValidezStr = String.valueOf(anyoValidez);
        this.anyoValidez = LocalDate.parse(anyoValidezStr);
    }

    //constructor para las cuotas generadas desde el programa
    public Cuota(double importe, LocalDate anyoValidez) {
        this.importe = importe;
        this.anyoValidez = anyoValidez;
    }

    public Cuota() {
    }

    public void addSocio(Socio socio) {
       socios.add(socio);
    }

    public List<Socio> getSocios() {
        return socios;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public LocalDate getAnyoValidez() {
        return anyoValidez;
    }

    public void setAnyoValidez(LocalDate anyoValidez) {
        this.anyoValidez = anyoValidez;
    }

}
