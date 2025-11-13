package com.api.gestion.cine.services.reports.export_reports.cinema_admin;

import java.io.InputStream;

import com.api.gestion.cine.dto.reports.cinema_admin.commented_room_report.CommentedRoomResponseReportDTO;
import com.api.gestion.cine.exceptions.ExportUnExpectedError;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ExportCommentedRoomReportService {

  public byte[] getReport(CommentedRoomResponseReportDTO reportDTO) throws ExportUnExpectedError {

    System.out.println("Generando el informe PDF para CommentedRoomResponseReportDTO...");
    InputStream reportStream = getClass().getClassLoader()
        .getResourceAsStream(
            "export/cinema_admin_reports/commented_rooms/ReporteSalasComentadas.jasper");

    System.out.println("Ruta del informe: export/cinema_admin_reports/commented_rooms/ReporteSalasComentadas.jasper");

    if (reportStream == null) {
      System.out.println("Error: No se pudo cargar el archivo del informe.");
      throw new ExportUnExpectedError("Error al cargar el archivo del informe.");
    }

    System.out.println("Archivo del informe cargado correctamente.");
    JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportDTO.getCommentedRooms());
    try {

      System.out.println("Rellenando el informe Jasper...");
      JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, null, dataSource);

      System.out.println("Exportando el informe a PDF...");
      return JasperExportManager.exportReportToPdf(jasperPrint);
    } catch (JRException e) {
      System.out.println("Error al generar el informe PDF: " + e.getMessage());
      throw new ExportUnExpectedError("Error al generar el informe PDF.");
    }
  }
}
