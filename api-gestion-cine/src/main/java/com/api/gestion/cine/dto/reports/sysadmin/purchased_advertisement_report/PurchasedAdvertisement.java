package com.api.gestion.cine.dto.reports.sysadmin.purchased_advertisement_report;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

public class PurchasedAdvertisement {
    private int idAnuncio;
    private String nombreAnuncio;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate fechaPago;
    private BigDecimal montoPago;
    private String tipoAnuncio;

    public PurchasedAdvertisement() {
    }

    public PurchasedAdvertisement(int idAnuncio, String nombreAnuncio, String tipoAnuncio, LocalDate fechaPago,
            BigDecimal montoPago) {
        this.idAnuncio = idAnuncio;
        this.nombreAnuncio = nombreAnuncio;
        this.fechaPago = fechaPago;
        this.montoPago = montoPago;
        this.tipoAnuncio = tipoAnuncio;
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

    public BigDecimal getMontoPago() {
        return montoPago;
    }

    public void setMontoPago(BigDecimal montoPago) {
        this.montoPago = montoPago;
    }

    public String getTipoAnuncio() {
        return tipoAnuncio;
    }

    public void setTipoAnuncio(String tipoAnuncio) {
        this.tipoAnuncio = tipoAnuncio;
    }

}