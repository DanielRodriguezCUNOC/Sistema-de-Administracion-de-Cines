package com.api.gestion.cine.dto.reports.cinema_admin.most_liked_room_report;

import java.util.List;

public class MostLikedRoomResponseReportDTO {

    private List<LikedRoomData> likedRooms;

    public MostLikedRoomResponseReportDTO() {
    }

    public MostLikedRoomResponseReportDTO(List<LikedRoomData> likedRooms) {
        this.likedRooms = likedRooms;
    }

    public List<LikedRoomData> getLikedRooms() {
        return likedRooms;
    }

    public void setLikedRooms(List<LikedRoomData> likedRooms) {
        this.likedRooms = likedRooms;
    }
}
