package com.api.gestion.cine.dto.reports.sysadmin.most_commented_room_report;

public class MostCommentedRoomReponseReportDTO {

    private RoomComment[] salasConComentarios;

    public MostCommentedRoomReponseReportDTO() {
    }

    public MostCommentedRoomReponseReportDTO(RoomComment[] salasConComentarios) {
        this.salasConComentarios = salasConComentarios;
    }

    public RoomComment[] getSalasConComentarios() {
        return salasConComentarios;
    }

    public void setSalasConComentarios(RoomComment[] salasConComentarios) {
        this.salasConComentarios = salasConComentarios;
    }
}
