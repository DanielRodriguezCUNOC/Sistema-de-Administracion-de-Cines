package com.api.gestion.cine.dto.reports.cinema_admin.commented_room_report;

import java.util.List;

import com.api.gestion.cine.dto.reports.sysadmin.most_commented_room_report.RoomComment;

public class CommentedRoomResponseReportDTO {

    private List<RoomComment> commentedRooms;

    public CommentedRoomResponseReportDTO() {
    }

    public CommentedRoomResponseReportDTO(List<RoomComment> commentedRooms) {
        this.commentedRooms = commentedRooms;
    }

    public List<RoomComment> getCommentedRooms() {
        return commentedRooms;
    }

    public void setCommentedRooms(List<RoomComment> commentedRooms) {
        this.commentedRooms = commentedRooms;
    }
}
