package com.api.gestion.cine.dto.reports.cinema_admin.sold_ticket_report;

public class SoldTicketData {

    private int idBoleto;
    private String nombreSala;
    private UserData[] usuarios;

    public SoldTicketData() {
    }

    public int getIdBoleto() {
        return idBoleto;
    }

    public void setIdBoleto(int idBoleto) {
        this.idBoleto = idBoleto;
    }

    public String getNombreSala() {
        return nombreSala;
    }

    public void setNombreSala(String nombreSala) {
        this.nombreSala = nombreSala;
    }

    public UserData[] getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(UserData[] usuarios) {
        this.usuarios = usuarios;
    }

}
