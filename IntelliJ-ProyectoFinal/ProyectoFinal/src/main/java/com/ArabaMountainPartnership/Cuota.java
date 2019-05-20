package com.ArabaMountainPartnership;

import java.util.ArrayList;
import java.util.List;

public class Cuota {

    private double importe;
    private int anyoValidez;
    private List<Socio> socios = new ArrayList<>();

    public Cuota(double importe, int anyoValidez) {
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

    public int getAnyoValidez() {
        return anyoValidez;
    }

    public void setAnyoValidez(int anyoValidez) {
        this.anyoValidez = anyoValidez;
    }

}
