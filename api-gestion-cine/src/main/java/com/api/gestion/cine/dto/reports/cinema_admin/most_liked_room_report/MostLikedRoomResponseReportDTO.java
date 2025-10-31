package com.api.gestion.cine.dto.reports.cinema_admin.most_liked_room_report;

public class MostLikedRoomResponseReportDTO {

    private LikedRoomData[] likedRooms;

    public MostLikedRoomResponseReportDTO() {
    }

    public MostLikedRoomResponseReportDTO(LikedRoomData[] likedRooms) {
        this.likedRooms = likedRooms;
    }

    public LikedRoomData[] getLikedRooms() {
        return likedRooms;
    }

    public void setLikedRooms(LikedRoomData[] likedRooms) {
        this.likedRooms = likedRooms;
    }
}
