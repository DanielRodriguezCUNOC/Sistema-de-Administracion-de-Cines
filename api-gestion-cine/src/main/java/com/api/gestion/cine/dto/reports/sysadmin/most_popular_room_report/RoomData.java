package com.api.gestion.cine.dto.reports.sysadmin.most_popular_room_report;

public class RoomData {

    private int idSala;
    private String nombreSala;
    private String nombreUsuario;
    private int totalBoletosVendidos;

    public RoomData() {
    }

    public RoomData(int idSala, String nombreSala, String nombreUsuario, int totalBoletosVendidos) {
        this.idSala = idSala;
        this.nombreSala = nombreSala;
        this.nombreUsuario = nombreUsuario;
        this.totalBoletosVendidos = totalBoletosVendidos;
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

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getTotalBoletosVendidos() {
        return totalBoletosVendidos;
    }

    public void setTotalBoletosVendidos(int totalBoletosVendidos) {
        this.totalBoletosVendidos = totalBoletosVendidos;
    }
}
