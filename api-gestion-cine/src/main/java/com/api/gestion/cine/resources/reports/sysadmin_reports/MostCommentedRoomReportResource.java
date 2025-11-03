package com.api.gestion.cine.resources.reports.sysadmin_reports;

import com.api.gestion.cine.dto.reports.sysadmin.most_commented_room_report.MostCommentedRoomResponseReportDTO;
import com.api.gestion.cine.services.reports.sysadmin.MostCommentedRoomReportService;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("sysadmin/report/commented-room")
public class MostCommentedRoomReportResource {

  @GET
  @Path("inicio/{fechaInicio}/fin/{fechaFin}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getResponse(@PathParam("fechaInicio") String fechaInicio,
      @PathParam("fechaFin") String fechaFin) {
    try {

      // * Creación del servicio de informes de anuncios comprados */
      MostCommentedRoomReportService service = new MostCommentedRoomReportService();

      // * Generación del informe de anuncios comprados */
      MostCommentedRoomResponseReportDTO report = service.generateReport(fechaInicio,
          fechaFin);

      // * Retorno de la respuesta exitosa */
      return Response.ok(report).build();
    } catch (Exception e) {
      // ! Retorno de la respuesta en caso de error */
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
  }
}
