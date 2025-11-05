package com.api.gestion.cine.services.reports.sysadmin;

import java.time.LocalDate;
import java.util.List;

import com.api.gestion.cine.db.sysadmin.MostPopularRoomReportDB;
import com.api.gestion.cine.dto.reports.sysadmin.most_popular_room_report.MostPopularRoomResponseReportDTO;
import com.api.gestion.cine.dto.reports.sysadmin.most_popular_room_report.RoomData;
import com.api.gestion.cine.exceptions.ReportServiceException;
import com.api.gestion.cine.services.util.FormatterDateCustom;

public class MostPopularRoomReportService {

  public MostPopularRoomResponseReportDTO generateReport(String fechaInicio, String fechaFin)
      throws ReportServiceException {

    MostPopularRoomResponseReportDTO report = new MostPopularRoomResponseReportDTO();

    LocalDate startDate = FormatterDateCustom.parseStringToDate(fechaInicio);
    LocalDate endDate = FormatterDateCustom.parseStringToDate(fechaFin);

    MostPopularRoomReportDB reportDB = new MostPopularRoomReportDB();

    try {
      List<RoomData> popularRooms = reportDB.getMostPopularRooms(startDate, endDate);

      report.setRoomData(popularRooms.toArray(new RoomData[0]));
    } catch (Exception e) {
      throw new ReportServiceException("Error al generar informe de sala más popular", e);
    }
    return report;
  }
}
