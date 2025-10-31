package com.api.gestion.cine.dto.reports.cinema_admin.most_liked_room_report;

public class LikedRoomData {

    private int idSala;
    private String nombreSala;
    private int cantidadValoraciones;
    private int promedioValoracion;
    private LikedData[] valoraciones;

    public LikedRoomData() {
    }

    public LikedRoomData(int idSala, String nombreSala, int cantidadValoraciones, int promedioValoracion,
            LikedData[] valoraciones) {
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

    public int getPromedioValoracion() {
        return promedioValoracion;
    }

    public void setPromedioValoracion(int promedioValoracion) {
        this.promedioValoracion = promedioValoracion;
    }

    public LikedData[] getValoraciones() {
        return valoraciones;
    }

    public void setValoraciones(LikedData[] valoraciones) {
        this.valoraciones = valoraciones;
    }

    public void obtenerPromedioValoracion() {
        if (valoraciones == null || valoraciones.length == 0) {
            this.promedioValoracion = 0;
            return;
        }

        int suma = 0;
        for (LikedData likedData : valoraciones) {
            suma += likedData.getValoracion();
        }
        this.promedioValoracion = suma / valoraciones.length;
    }

    public void calcularCantidadValoraciones() {
        if (valoraciones == null) {
            this.cantidadValoraciones = 0;
        } else {
            this.cantidadValoraciones = valoraciones.length;
        }
    }
}
