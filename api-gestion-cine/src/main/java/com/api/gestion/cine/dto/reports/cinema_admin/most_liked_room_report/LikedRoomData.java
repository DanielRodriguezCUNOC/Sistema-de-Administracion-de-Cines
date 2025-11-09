package com.api.gestion.cine.dto.reports.cinema_admin.most_liked_room_report;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class LikedRoomData {

    private int idSala;
    private String nombreSala;
    private int cantidadValoraciones;
    private BigDecimal promedioValoracion;
    private List<LikedData> valoraciones;

    public LikedRoomData() {
    }

    public LikedRoomData(int idSala, String nombreSala, int cantidadValoraciones, BigDecimal promedioValoracion,
            List<LikedData> valoraciones) {
        this.idSala = idSala;
        this.nombreSala = nombreSala;
        this.cantidadValoraciones = cantidadValoraciones;
        this.promedioValoracion = promedioValoracion;
        this.valoraciones = valoraciones;
    }

    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public String getNombreSala() {
        return nombreSala;
    }

    public void setNombreSala(String nombreSala) {
        this.nombreSala = nombreSala;
    }

    public int getCantidadValoraciones() {
        return cantidadValoraciones;
    }

    public void setCantidadValoraciones(int cantidadValoraciones) {
        this.cantidadValoraciones = cantidadValoraciones;
    }

    public BigDecimal getPromedioValoracion() {
        return promedioValoracion;
    }

    public void setPromedioValoracion(BigDecimal promedioValoracion) {
        this.promedioValoracion = promedioValoracion;
    }

    public List<LikedData> getValoraciones() {
        return valoraciones;
    }

    public void setValoraciones(List<LikedData> valoraciones) {
        this.valoraciones = valoraciones;
    }

    public void obtenerPromedioValoracion() {
        if (valoraciones == null || valoraciones.isEmpty()) {
            this.promedioValoracion = BigDecimal.ZERO;
            return;
        }

        int suma = 0;
        for (LikedData likedData : valoraciones) {
            suma += likedData.getValoracion();
        }
        this.promedioValoracion = BigDecimal.valueOf(suma).divide(BigDecimal.valueOf(valoraciones.size()), 2,
                RoundingMode.HALF_UP);
    }

    public void calcularCantidadValoraciones() {
        if (valoraciones == null) {
            this.cantidadValoraciones = 0;
        } else {
            this.cantidadValoraciones = valoraciones.size();
        }
    }
}
