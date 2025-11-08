package com.api.gestion.cine.dto.reports.cinema_admin.sold_ticket_report;

import java.util.List;

public class SoldTicketData {

    private int idSala;
    private String nombreSala;
    private List<UserData> usuarios;

    public SoldTicketData() {
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

    public List<UserData> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UserData> usuarios) {
        this.usuarios = usuarios;
    }

}
