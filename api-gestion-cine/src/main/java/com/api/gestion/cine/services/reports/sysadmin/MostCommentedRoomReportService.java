package com.api.gestion.cine.services.reports.sysadmin;

import java.time.LocalDate;
import java.util.List;

import com.api.gestion.cine.db.sysadmin.MostCommentedRoomDB;
import com.api.gestion.cine.dto.reports.sysadmin.most_commented_room_report.MostCommentedRoomResponseReportDTO;
import com.api.gestion.cine.dto.reports.sysadmin.most_commented_room_report.RoomComment;
import com.api.gestion.cine.exceptions.ReportServiceException;
import com.api.gestion.cine.services.util.FormatterDateCustom;

public class MostCommentedRoomReportService {

  public MostCommentedRoomResponseReportDTO generateReport(String fechaInicio, String fechaFin)
      throws ReportServiceException {

    MostCommentedRoomResponseReportDTO report = new MostCommentedRoomResponseReportDTO();
    MostCommentedRoomDB reportDB = new MostCommentedRoomDB();
    LocalDate startDate = null;
    LocalDate endDate = null;

    if (fechaInicio != null && !fechaInicio.trim().isEmpty()) {
      startDate = FormatterDateCustom.parseStringToDate(fechaInicio);
    }

    if (fechaFin != null && !fechaFin.trim().isEmpty()) {
      endDate = FormatterDateCustom.parseStringToDate(fechaFin);
    }

    try {
      List<RoomComment> roomComments = reportDB.getMostCommentedRooms(startDate, endDate);
      report.setSalasConComentarios(roomComments.toArray(new RoomComment[0]));
    } catch (Exception e) {
      throw new ReportServiceException("Error al generar el informe de salas más comentadas", e);
    }
    return report;
  }

}
