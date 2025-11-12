package com.api.gestion.cine.services.reports.export_reports.cinema_admin;

import java.io.InputStream;

import com.api.gestion.cine.dto.reports.cinema_admin.most_liked_room_report.MostLikedRoomResponseReportDTO;
import com.api.gestion.cine.exceptions.ExportUnExpectedError;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ExportMostLikedRoomReportService {

  public byte[] getReport(MostLikedRoomResponseReportDTO reportDTO) throws ExportUnExpectedError {

    InputStream reportStream = getClass().getClassLoader()
        .getResourceAsStream(
            "export/cinema_admin_reports/most_liked_rooms/MostLikedRoomResponseReportDTO.jasper");

    if (reportStream == null) {
      throw new ExportUnExpectedError("Error al cargar el archivo del informe.");
    }

    JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportDTO.getLikedRooms());
    try {

      JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, null, dataSource);

      return JasperExportManager.exportReportToPdf(jasperPrint);
    } catch (JRException e) {
      throw new ExportUnExpectedError("Error al generar el informe PDF.");
    }
  }
}
