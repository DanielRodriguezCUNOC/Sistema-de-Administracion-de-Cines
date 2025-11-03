package com.api.gestion.cine.resources.reports.cinema_admin_reports;

import com.api.gestion.cine.dto.reports.cinema_admin.commented_room_report.CommentedRoomResponseReportDTO;
import com.api.gestion.cine.services.reports.cinema_admin.CommentedRoomReportService;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("admin-cinema/report/commented-room")
public class CommentedRoomReportResource {

  @GET
  @Path("inicio/{fechaInicio}/fin/{fechaFin}/sala/{idSala}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getResponse(@PathParam("fechaInicio") String fechaInicio,
      @PathParam("fechaFin") String fechaFin, @PathParam("idSala") int idSala) {

    try {

      // * Creación del servicio de informes de anuncios comprados */
      CommentedRoomReportService service = new CommentedRoomReportService();

      // * Generación del informe de anuncios comprados */
      CommentedRoomResponseReportDTO report = service.generateReport(fechaInicio,
          fechaFin, idSala);

      // * Retorno de la respuesta exitosa */
      return Response.ok(report).build();
    } catch (Exception e) {

      // ! Retorno de la respuesta en caso de error */
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
  }
}
