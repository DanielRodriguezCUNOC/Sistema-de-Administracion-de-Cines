package com.api.gestion.cine.dto.reports.sysadmin.profit_report;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CinemaCostReport {

    private int idCine;
    private String nombreCine;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private List<LocalDate> fechasModificacion;
    private int[] costos;
    private int costoTotal;

    public CinemaCostReport() {
    }

    public CinemaCostReport(int idCine, String nombreCine, List<LocalDate> fechasModificacion, int[] costos) {
        this.idCine = idCine;
        this.nombreCine = nombreCine;
        this.fechasModificacion = fechasModificacion;
        this.costos = costos;
    }

    public int getIdCine() {
        return idCine;
    }

    public void setIdCine(int idCine) {
        this.idCine = idCine;
    }

    public String getNombreCine() {
        return nombreCine;
    }

    public void setNombreCine(String nombreCine) {
        this.nombreCine = nombreCine;
    }

    public int[] getCostos() {
        return costos;
    }

    public void setCostos(int[] costos) {
        this.costos = costos;
    }

    public List<LocalDate> getFechasModificacion() {
        return fechasModificacion;
    }

    public void setFechasModificacion(List<LocalDate> fechasModificacion) {
        this.fechasModificacion = fechasModificacion;
    }

    public int getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(int costoTotal) {
        this.costoTotal = costoTotal;
    }

    public void calcularCostoTotal() {
        int total = 0;
        for (int costo : costos) {
            total += costo;
        }
        this.costoTotal = total;
    }
}
