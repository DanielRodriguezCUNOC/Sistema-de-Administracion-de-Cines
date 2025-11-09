package com.api.gestion.cine.services.reports.export_reports.cinema_admin;

import java.io.InputStream;
import java.util.Collections;

import com.api.gestion.cine.dto.reports.cinema_admin.most_liked_room_report.LikedRoomData;
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
      System.out.println("El archivo del informe no se encontró.");
      throw new ExportUnExpectedError("Error al cargar el archivo del informe.");
    }

    System.out.println("Creando la fuente de datos del informe...");
    JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Collections.singletonList(reportDTO));
    try {

      System.out.println("Número de likedRooms = " + reportDTO.getLikedRooms().size());
      for (LikedRoomData lr : reportDTO.getLikedRooms()) {
        System.out.println("LR id=" + lr.getIdSala() + ", nombre=" + lr.getNombreSala() +
            ", valoraciones.size=" + (lr.getValoraciones() == null ? "null" : lr.getValoraciones().size()));
      }

      System.out.println("Llenando el informe...");
      JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, null, dataSource);

      System.out.println("Generando informe PDF...");
      return JasperExportManager.exportReportToPdf(jasperPrint);
    } catch (JRException e) {
      System.out.println("Error al generar el informe PDF: " + e.getMessage());
      throw new ExportUnExpectedError("Error al generar el informe PDF.");
    }
  }
}
