package com.api.gestion.cine.dto.reports.sysadmin.profit_report;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

public class CinemaCostReport {

    private int idCine;
    private String nombreCine;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate fechaModificacion;
    private int[] costos;

    public CinemaCostReport() {
    }

    public CinemaCostReport(int idCine, String nombreCine, LocalDate fechaModificacion, int[] costos) {
        this.idCine = idCine;
        this.nombreCine = nombreCine;
        this.fechaModificacion = fechaModificacion;
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

    public int[] getCostoTotal() {
        return costos;
    }

    public void setCostoTotal(int[] costos) {
        this.costos = costos;
    }

    public LocalDate getFechaPago() {
        return fechaModificacion;
    }

    public void setFechaPago(LocalDate fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
}
