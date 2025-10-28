package com.api.gestion.cine.dto.reports.sysadmin;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

public class CinemaCostReport {

    private int idCine;
    private String nombreCine;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate fechaPago;
    private int[] costoCine;

    public CinemaCostReport() {
    }

    public CinemaCostReport(int idCine, String nombreCine, int[] costoCine) {
        this.idCine = idCine;
        this.nombreCine = nombreCine;
        this.costoCine = costoCine;
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
        return costoCine;
    }

    public void setCostoTotal(int[] costoCine) {
        this.costoCine = costoCine;
    }
}
