package com.api.gestion.cine.resources.reports.export_reports.cinema_admin;

import com.api.gestion.cine.dto.reports.cinema_admin.commented_room_report.CommentedRoomResponseReportDTO;
import com.api.gestion.cine.services.reports.cinema_admin.CommentedRoomReportService;
import com.api.gestion.cine.services.reports.export_reports.cinema_admin.ExportCommentedRoomReportService;
import com.api.gestion.cine.services.util.NameFileGenerator;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.StreamingOutput;

@Path("reports/export/cinema-admin/commented-rooms")
public class ExportCommentedRoomReportResource {

  @GET
  @Path("inicio/{fechaInicio}/fin/{fechaFin}/sala/{nombreSala}/offset/{offset}/limit/{limit}")
  @Produces("application/pdf")
  public Response getResponse(
      @PathParam("fechaInicio") String fechaInicio,
      @PathParam("fechaFin") String fechaFin,
      @PathParam("nombreSala") String nombreSala,
      @PathParam("offset") int offset,
      @PathParam("limit") int limit) {

    System.out.println("Solicitud de informe PDF para salas comentadas recibida.");
    CommentedRoomReportService reportService = new CommentedRoomReportService();

    try {

      System.out.println("Generando el DTO del informe..." +
          " fechaInicio=" + fechaInicio +
          ", fechaFin=" + fechaFin +
          ", nombreSala=" + nombreSala +
          ", offset=" + offset +
          ", limit=" + limit);

      CommentedRoomResponseReportDTO reportDTO = reportService.generateReport(fechaInicio, fechaFin, nombreSala, offset,
          limit);

      // Servicio que genera el PDF
      System.out.println("Llamando al servicio de exportacion...");
      ExportCommentedRoomReportService service = new ExportCommentedRoomReportService();

      byte[] pdfData = service.getReport(reportDTO);

      System.out.println("PDF antes del return: " + (pdfData != null ? pdfData.length : "null"));
      StreamingOutput stream = output -> {
        output.write(pdfData);
        output.flush();
      };

      NameFileGenerator nameFileGenerator = new NameFileGenerator();
      String fileName = nameFileGenerator.generateFileName("Commented_Room_Report", "pdf");

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
