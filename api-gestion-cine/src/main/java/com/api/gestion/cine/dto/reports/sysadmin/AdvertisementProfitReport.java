package com.api.gestion.cine.dto.reports.sysadmin;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

public class AdvertisementProfitReport {

    private int idAnuncio;
    private String nombreAnuncio;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate fechaPago;
    private int montoPago;

    public AdvertisementProfitReport() {
    }

    public AdvertisementProfitReport(int idAnuncio, String nombreAnuncio, LocalDate fechaPago, int montoPago) {
        this.idAnuncio = idAnuncio;
        this.nombreAnuncio = nombreAnuncio;
        this.fechaPago = fechaPago;
        this.montoPago = montoPago;
    }

    public int getIdAnuncio() {
        return idAnuncio;
    }

    public void setIdAnuncio(int idAnuncio) {
        this.idAnuncio = idAnuncio;
    }

    public String getNombreAnuncio() {
        return nombreAnuncio;
    }

    public void setNombreAnuncio(String nombreAnuncio) {
        this.nombreAnuncio = nombreAnuncio;
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
