package com.api.gestion.cine.services.reports.cinema_admin;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.api.gestion.cine.db.cinema_dmin.SoldTicketReportDB;
import com.api.gestion.cine.dto.reports.cinema_admin.sold_ticket_report.SoldTicketData;
import com.api.gestion.cine.dto.reports.cinema_admin.sold_ticket_report.SoldTicketResponseReportDTO;
import com.api.gestion.cine.dto.reports.cinema_admin.sold_ticket_report.UserData;
import com.api.gestion.cine.exceptions.ReportServiceException;
import com.api.gestion.cine.services.util.FormatterDateCustom;

public class SoldTicketReportService {

  public SoldTicketResponseReportDTO generateReport(String fechaInicio, String fechaFin, String nombreSala)
      throws ReportServiceException {

    LocalDate startDate = null;
    LocalDate endDate = null;

    if (fechaInicio != null && !fechaInicio.trim().isEmpty()) {
      startDate = FormatterDateCustom.parseStringToDate(fechaInicio);
    }
    if (fechaFin != null && !fechaFin.trim().isEmpty()) {
      endDate = FormatterDateCustom.parseStringToDate(fechaFin);
    }

    SoldTicketResponseReportDTO reportDTO = new SoldTicketResponseReportDTO();
    SoldTicketReportDB reportDB = new SoldTicketReportDB();

    try {
      List<SoldTicketData> soldTickets = reportDB.getSoldTicket(startDate, endDate, nombreSala);
      reportDTO.setData(soldTickets.toArray(new SoldTicketData[0]));

      // * calcular el total general */

      BigDecimal total = BigDecimal.ZERO;
      for (SoldTicketData sala : soldTickets) {
        for (UserData user : sala.getUsuarios()) {
          total = total.add(user.getMontoPago());
        }
      }
      reportDTO.setTotalDinero(total);

    } catch (Exception e) {
      throw new ReportServiceException("Error al generar el informe de tickets vendidos", e);
    }
    return reportDTO;
  }

}
