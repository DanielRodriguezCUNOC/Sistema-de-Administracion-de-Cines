package com.api.gestion.cine.dto.reports.cinema_admin.commented_room_report;

import com.api.gestion.cine.dto.reports.sysadmin.most_commented_room_report.RoomComment;

public class CommentedRoomResponseReportDTO {

    private RoomComment[] commentedRooms;

    public CommentedRoomResponseReportDTO() {
    }

    public CommentedRoomResponseReportDTO(RoomComment[] commentedRooms) {
        this.commentedRooms = commentedRooms;
    }

    public RoomComment[] getCommentedRooms() {
        return commentedRooms;
    }

    public void setCommentedRooms(RoomComment[] commentedRooms) {
        this.commentedRooms = commentedRooms;
    }
}
