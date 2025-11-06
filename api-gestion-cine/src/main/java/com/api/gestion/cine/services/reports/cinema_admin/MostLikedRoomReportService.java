package com.api.gestion.cine.services.reports.cinema_admin;

import java.time.LocalDate;
import java.util.List;

import com.api.gestion.cine.db.cinema_dmin.MostLikedRoomReportDB;
import com.api.gestion.cine.dto.reports.cinema_admin.most_liked_room_report.LikedRoomData;
import com.api.gestion.cine.dto.reports.cinema_admin.most_liked_room_report.MostLikedRoomResponseReportDTO;
import com.api.gestion.cine.exceptions.ReportServiceException;
import com.api.gestion.cine.services.util.FormatterDateCustom;

public class MostLikedRoomReportService {

  public MostLikedRoomResponseReportDTO generateReport(String fechaInicio, String fechaFin, String nombreSala)
      throws ReportServiceException {

    MostLikedRoomReportDB reportDB = new MostLikedRoomReportDB();
    MostLikedRoomResponseReportDTO reportDTO = new MostLikedRoomResponseReportDTO();

    LocalDate startDate = null;
    LocalDate endDate = null;

    if (fechaInicio != null && !fechaInicio.trim().isEmpty()) {
      startDate = FormatterDateCustom.parseStringToDate(fechaInicio);

    }
    if (fechaFin != null && !fechaFin.trim().isEmpty()) {
      endDate = FormatterDateCustom.parseStringToDate(fechaFin);
    }
    if (nombreSala != null && !nombreSala.trim().isEmpty()) {
      nombreSala = null;
    }
    try {
      List<LikedRoomData> likedRooms = reportDB.getMostLikedRoom(startDate, endDate, nombreSala);
      reportDTO.setLikedRooms(likedRooms.toArray(new LikedRoomData[0]));
    } catch (Exception e) {
      throw new ReportServiceException("Error al generar el informe de salas más gustadas", e);
    }
    return reportDTO;
  }

}
