package com.api.gestion.cine.dto.reports.sysadmin;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

public class AdminPaymentAdBlockReport {
    private int idUsuario;
    private String nombreUsuario;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate fechaPago;
    private int montoPago;

    public AdminPaymentAdBlockReport() {
    }

    public AdminPaymentAdBlockReport(int idUsuario, String nombreUsuario, LocalDate fechaPago, int montoPago) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.fechaPago = fechaPago;
        this.montoPago = montoPago;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
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
