package com.api.gestion.cine.resources.reports.cinema_admin_reports;

import com.api.gestion.cine.dto.reports.cinema_admin.most_liked_room_report.MostLikedRoomResponseReportDTO;
import com.api.gestion.cine.services.reports.cinema_admin.MostLikedRoomReportService;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("admin-cinema/report/liked-room")
public class MostLikedRoomReportResource {

  @GET
  @Path("inicio/{fechaInicio}/fin/{fechaFin}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getResponse(
      @PathParam("fechaInicio") String fechaInicio,
      @PathParam("fechaFin") String fechaFin,
      @PathParam("nombreSala") String nombreSala) {
    try {

      // * Creación del servicio de informes de anuncios comprados */
      MostLikedRoomReportService service = new MostLikedRoomReportService();

      // * Generación del informe de anuncios comprados */
      MostLikedRoomResponseReportDTO report = service.generateReport(fechaInicio,
          fechaFin, nombreSala);

      // * Retorno de la respuesta exitosa */
      return Response.ok(report).build();
    } catch (Exception e) {
      // ! Retorno de la respuesta en caso de error */
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
  }
}
