package com.api.gestion.cine.dto.reports.sysadmin.most_commented_room_report;

public class MostCommentedRoomResponseReportDTO {

    private RoomComment[] salasConComentarios;

    public MostCommentedRoomResponseReportDTO() {
    }

    public MostCommentedRoomResponseReportDTO(RoomComment[] salasConComentarios) {
        this.salasConComentarios = salasConComentarios;
    }

    public RoomComment[] getSalasConComentarios() {
        return salasConComentarios;
    }

    public void setSalasConComentarios(RoomComment[] salasConComentarios) {
        this.salasConComentarios = salasConComentarios;
    }
}
