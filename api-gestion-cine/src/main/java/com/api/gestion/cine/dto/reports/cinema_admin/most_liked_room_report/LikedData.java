package com.api.gestion.cine.dto.reports.cinema_admin.most_liked_room_report;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

public class LikedData {
    private String nombreUsuario;
    private int valoracion;
    private LocalDate fechaValoracion;

    public LikedData() {
    }

    public LikedData(String nombreUsuario, int valoracion, LocalDate fechaValoracion) {
        this.nombreUsuario = nombreUsuario;
        this.valoracion = valoracion;
        this.fechaValoracion = fechaValoracion;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public LocalDate getFechaValoracion() {
        return fechaValoracion;
    }

    public void setFechaValoracion(LocalDate fechaValoracion) {
        this.fechaValoracion = fechaValoracion;
    }
}
