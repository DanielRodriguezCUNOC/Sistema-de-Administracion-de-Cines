package com.api.gestion.cine.services.reports.cinema_admin;

import java.time.LocalDate;
import java.util.List;

import com.api.gestion.cine.db.cinema_dmin.CommentedRoomReportDB;
import com.api.gestion.cine.dto.reports.cinema_admin.commented_room_report.CommentedRoomResponseReportDTO;
import com.api.gestion.cine.dto.reports.sysadmin.most_commented_room_report.RoomComment;
import com.api.gestion.cine.exceptions.ReportServiceException;
import com.api.gestion.cine.services.util.FormatterDateCustom;

public class CommentedRoomReportService {

  public CommentedRoomResponseReportDTO generateReport(String fechaInicio, String fechaFin, String nombreSala,
      int offset, int limit) throws ReportServiceException {

    CommentedRoomResponseReportDTO report = new CommentedRoomResponseReportDTO();
    CommentedRoomReportDB reportDB = new CommentedRoomReportDB();

    LocalDate startDate = null;
    LocalDate endDate = null;

    if (fechaInicio != null && !fechaInicio.trim().isEmpty()) {
      startDate = FormatterDateCustom.parseStringToDate(fechaInicio);
    }

    if (fechaFin != null && !fechaFin.trim().isEmpty()) {
      endDate = FormatterDateCustom.parseStringToDate(fechaFin);
    }

    if (nombreSala == null || nombreSala.trim().isEmpty()) {
      nombreSala = "Todo";
    }
    try {
      List<RoomComment> roomComments = reportDB.getCommentedRooms(startDate, endDate, nombreSala, offset, limit);
      report.setCommentedRooms(roomComments.toArray(new RoomComment[0]));
    } catch (Exception e) {
      throw new ReportServiceException("Error al generar el informe de salas comentadas", e);
    }
    return report;
  }

}
