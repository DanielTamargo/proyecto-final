package com.ArabaMountainPartnership;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Junta {

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private List<Cargo> cargos = new ArrayList<>();


    public Junta(LocalDate fechaInicio, LocalDate fechaFin) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Junta() {
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

    public List<Cargo> getCargos() {
        return cargos;
    }

    public void addCargo(Cargo cargo) {
        cargos.add(cargo);
    }
}
