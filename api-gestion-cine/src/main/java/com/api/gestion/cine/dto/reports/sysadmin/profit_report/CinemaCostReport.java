package com.api.gestion.cine.dto.reports.sysadmin.profit_report;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CinemaCostReport {

    private int idCine;
    private String nombreCine;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private List<LocalDate> fechasModificacion;
    private BigDecimal[] costos;
    private BigDecimal costoTotal;

    public CinemaCostReport() {
    }

    public CinemaCostReport(int idCine, String nombreCine, List<LocalDate> fechasModificacion, BigDecimal[] costos) {
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

    public BigDecimal[] getCostos() {
        return costos;
    }

    public void setCostos(BigDecimal[] costos) {
        this.costos = costos;
    }

    public List<LocalDate> getFechasModificacion() {
        return fechasModificacion;
    }

    public void setFechasModificacion(List<LocalDate> fechasModificacion) {
        this.fechasModificacion = fechasModificacion;
    }

    public BigDecimal getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(BigDecimal costoTotal) {
        this.costoTotal = costoTotal;
    }

    public void calcularCostoTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (BigDecimal costo : costos) {
            total = total.add(costo);
        }
        this.costoTotal = total;
    }
}
