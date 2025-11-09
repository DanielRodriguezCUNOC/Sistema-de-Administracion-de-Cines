package com.api.gestion.cine.resources.reports.export_reports.cinema_admin;

import com.api.gestion.cine.dto.reports.cinema_admin.most_liked_room_report.MostLikedRoomResponseReportDTO;
import com.api.gestion.cine.services.reports.cinema_admin.MostLikedRoomReportService;
import com.api.gestion.cine.services.reports.export_reports.cinema_admin.ExportMostLikedRoomReportService;
import com.api.gestion.cine.services.util.NameFileGenerator;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.StreamingOutput;

@Path("reports/export/cinema-admin/most-liked-rooms")
public class ExportMostLikedRoomReportResource {

  @GET
  @Path("inicio/{fechaInicio}/fin/{fechaFin}/sala/{nombreSala}")
  @Produces("application/pdf")
  public Response getResponse(
      @PathParam("fechaInicio") String fechaInicio,
      @PathParam("fechaFin") String fechaFin,
      @PathParam("nombreSala") String nombreSala) {

    MostLikedRoomReportService reportService = new MostLikedRoomReportService();

    try {
      // Servicio que genera el DTO
      MostLikedRoomResponseReportDTO reportDTO = reportService.generateReport(fechaInicio, fechaFin, nombreSala);

      // Servicio que genera el PDF
      ExportMostLikedRoomReportService service = new ExportMostLikedRoomReportService();
      byte[] pdfData = service.getReport(reportDTO);

      if (pdfData == null || pdfData.length == 0) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity("No se generó ningún PDF.").build();
      }

      StreamingOutput stream = output -> {
        output.write(pdfData);
        output.flush();
      };

      NameFileGenerator nameFileGenerator = new NameFileGenerator();
      String fileName = nameFileGenerator.generateFileName("Most_Liked_Room_Report", "pdf");

      System.out.println(" Informe PDF generado correctamente.");

      return Response.ok(stream)
          .type("application/pdf")
          .header("Content-Disposition", "attachment; filename=\"" + fileName + "\"")
          .header("Access-Control-Expose-Headers", "Content-Disposition")
          .build();

    } catch (Exception e) {
      e.printStackTrace();
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
          .entity("Error generando el reporte: " + e.getMessage())
          .build();
    }
  }
}
