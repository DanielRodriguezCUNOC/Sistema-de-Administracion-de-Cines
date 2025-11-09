package com.api.gestion.cine.services.reports.cinema_admin;

import java.time.LocalDate;
import java.util.List;

import com.api.gestion.cine.db.cinema_admin.MostLikedRoomReportDB;
import com.api.gestion.cine.dto.reports.cinema_admin.most_liked_room_report.LikedRoomData;
import com.api.gestion.cine.dto.reports.cinema_admin.most_liked_room_report.MostLikedRoomResponseReportDTO;
import com.api.gestion.cine.exceptions.ReportServiceException;
import com.api.gestion.cine.services.util.ValidatorCustom;

public class MostLikedRoomReportService {

  public MostLikedRoomResponseReportDTO generateReport(String fechaInicio, String fechaFin, String nombreSala)
      throws ReportServiceException {

    MostLikedRoomReportDB reportDB = new MostLikedRoomReportDB();
    MostLikedRoomResponseReportDTO reportDTO = new MostLikedRoomResponseReportDTO();

    LocalDate startDate = null;
    LocalDate endDate = null;

    if (ValidatorCustom.isValidDate(fechaInicio, fechaFin)) {
      LocalDate[] dates = ValidatorCustom.convertDateStringToLocalDate(fechaInicio, fechaFin);
      startDate = dates[0];
      endDate = dates[1];
    }

    if (ValidatorCustom.isNullOrEmpty(nombreSala)) {
      nombreSala = null;
    }
    try {
      List<LikedRoomData> likedRooms = reportDB.getMostLikedRoom(startDate, endDate, nombreSala);
      System.out.println("Salas gustadas: " + likedRooms.size());
      reportDTO.setLikedRooms(likedRooms);
    } catch (Exception e) {
      throw new ReportServiceException("Error al generar el informe de salas más gustadas", e);
    }
    return reportDTO;
  }

}
