package com.api.gestion.cine.dto.reports.sysadmin.most_commented_room_report;

public class RoomComment {

    private int idSala;
    private String nombreSala;
    private String nombreUsuario;
    private String comentario;
    private int totalComentarios;

    public RoomComment() {
    }

    public RoomComment(int idSala, String nombreSala, String nombreUsuario, String comentario, int totalComentarios) {
        this.idSala = idSala;
        this.nombreSala = nombreSala;
        this.nombreUsuario = nombreUsuario;
        this.comentario = comentario;
        this.totalComentarios = totalComentarios;
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

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getTotalComentarios() {
        return totalComentarios;
    }

    public void setTotalComentarios(int totalComentarios) {
        this.totalComentarios = totalComentarios;
    }

}
