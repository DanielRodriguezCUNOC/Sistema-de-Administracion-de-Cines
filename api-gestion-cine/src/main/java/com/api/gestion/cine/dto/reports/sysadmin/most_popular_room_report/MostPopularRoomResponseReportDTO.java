package com.api.gestion.cine.dto.reports.sysadmin.most_popular_room_report;

public class MostPopularRoomResponseReportDTO {

    private RoomData[] roomData;

    public MostPopularRoomResponseReportDTO() {
    }

    public MostPopularRoomResponseReportDTO(RoomData[] roomData) {
        this.roomData = roomData;
    }

    public RoomData[] getRoomData() {
        return roomData;
    }

    public void setRoomData(RoomData[] roomData) {
        this.roomData = roomData;
    }

}
