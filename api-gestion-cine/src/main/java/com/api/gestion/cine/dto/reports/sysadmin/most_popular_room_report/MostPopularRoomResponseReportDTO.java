package com.api.gestion.cine.dto.reports.sysadmin.most_popular_room_report;

import java.util.List;

public class MostPopularRoomResponseReportDTO {

    private List<RoomData> roomData;

    public MostPopularRoomResponseReportDTO() {
    }

    public MostPopularRoomResponseReportDTO(List<RoomData> roomData) {
        this.roomData = roomData;
    }

    public List<RoomData> getRoomData() {
        return roomData;
    }

    public void setRoomData(List<RoomData> roomData) {
        this.roomData = roomData;
    }

}
