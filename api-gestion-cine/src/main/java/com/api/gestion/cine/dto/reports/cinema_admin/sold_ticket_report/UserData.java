package com.api.gestion.cine.dto.reports.cinema_admin.sold_ticket_report;

import java.math.BigDecimal;
import java.time.LocalDate;

public class UserData {

    private int idUsuario;
    private String nombreUsuario;
    private int cantidadBoleto;
    private BigDecimal montoPago;
    private LocalDate fechaPago;

    public UserData() {
    }

    public UserData(int idUsuario, String nombreUsuario, int cantidadBoleto, BigDecimal montoPago,
            LocalDate fechaPago) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.cantidadBoleto = cantidadBoleto;
        this.montoPago = montoPago;
        this.fechaPago = fechaPago;
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

    public int getCantidadBoleto() {
        return cantidadBoleto;
    }

    public void setCantidadBoleto(int cantidadBoleto) {
        this.cantidadBoleto = cantidadBoleto;
    }

    public BigDecimal getMontoPago() {
        return montoPago;
    }

    public void setMontoPago(BigDecimal montoPago) {
        this.montoPago = montoPago;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

}
