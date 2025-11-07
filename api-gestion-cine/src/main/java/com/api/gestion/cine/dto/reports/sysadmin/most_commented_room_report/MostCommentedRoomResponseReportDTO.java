package com.api.gestion.cine.dto.reports.sysadmin.most_commented_room_report;

import java.util.List;

public class MostCommentedRoomResponseReportDTO {

    private List<RoomComment> salasConComentarios;

    public MostCommentedRoomResponseReportDTO() {
    }

    public MostCommentedRoomResponseReportDTO(List<RoomComment> salasConComentarios) {
        this.salasConComentarios = salasConComentarios;
    }

    public List<RoomComment> getSalasConComentarios() {
        return salasConComentarios;
    }

    public void setSalasConComentarios(List<RoomComment> salasConComentarios) {
        this.salasConComentarios = salasConComentarios;
    }
}
