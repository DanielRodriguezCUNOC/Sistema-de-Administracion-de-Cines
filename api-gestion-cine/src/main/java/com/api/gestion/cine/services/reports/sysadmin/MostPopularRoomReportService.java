package com.api.gestion.cine.services.reports.sysadmin;

import java.time.LocalDate;
import java.util.List;

import com.api.gestion.cine.db.sysadmin.MostPopularRoomReportDB;
import com.api.gestion.cine.dto.reports.sysadmin.most_popular_room_report.MostPopularRoomResponseReportDTO;
import com.api.gestion.cine.dto.reports.sysadmin.most_popular_room_report.RoomData;
import com.api.gestion.cine.exceptions.ReportServiceException;
import com.api.gestion.cine.services.util.ValidatorCustom;

public class MostPopularRoomReportService {

  public MostPopularRoomResponseReportDTO generateReport(String fechaInicio, String fechaFin)
      throws ReportServiceException {

    MostPopularRoomResponseReportDTO report = new MostPopularRoomResponseReportDTO();

    LocalDate startDate = null;
    LocalDate endDate = null;

    MostPopularRoomReportDB reportDB = new MostPopularRoomReportDB();

    try {

      if (ValidatorCustom.isValidDate(fechaInicio, fechaFin)) {
        LocalDate[] dates = ValidatorCustom.convertDateStringToLocalDate(fechaInicio, fechaFin);
        startDate = dates[0];
        endDate = dates[1];
      }
      List<RoomData> popularRooms = reportDB.getMostPopularRooms(startDate, endDate);

      report.setRoomData(popularRooms);
    } catch (Exception e) {
      throw new ReportServiceException("Error al generar informe de sala más popular", e);
    }
    return report;
  }
}
