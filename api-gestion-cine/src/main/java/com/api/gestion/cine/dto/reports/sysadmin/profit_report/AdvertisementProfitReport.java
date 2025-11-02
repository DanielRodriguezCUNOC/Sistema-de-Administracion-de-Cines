package com.api.gestion.cine.dto.reports.sysadmin.profit_report;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

public class AdvertisementProfitReport {

    private int id;
    private String nombre;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate fechaPago;
    private int montoPago;

    public AdvertisementProfitReport() {
    }

    public AdvertisementProfitReport(int id, String nombre, LocalDate fechaPago, int montoPago) {
        this.id = id;
        this.nombre = nombre;
        this.fechaPago = fechaPago;
        this.montoPago = montoPago;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public int getMontoPago() {
        return montoPago;
    }

    public void setMontoPago(int montoPago) {
        this.montoPago = montoPago;
    }

    public int getAmount() {
        return montoPago;
    }
}
