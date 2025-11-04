package com.api.gestion.cine.resources.reports.sysadmin_reports;

import com.api.gestion.cine.dto.reports.sysadmin.most_popular_room_report.MostPopularRoomResponseReportDTO;
import com.api.gestion.cine.services.reports.sysadmin.MostPopularRoomReportService;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("sysadmin/report/popular-room")
public class MostPopularRoomReportResource {

  @GET
  @Path("inicio/{fechaInicio}/fin/{fechaFin}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getResponse(@PathParam("fechaInicio") String fechaInicio,
      @PathParam("fechaFin") String fechaFin) {
    try {

      // * Creación del servicio de informes de anuncios comprados */
      MostPopularRoomReportService service = new MostPopularRoomReportService();

      // * Generación del informe de anuncios comprados */
      MostPopularRoomResponseReportDTO report = service.generateReport(fechaInicio,
          fechaFin);

      // * Retorno de la respuesta exitosa */
      return Response.ok(report).build();
    } catch (Exception e) {
      // ! Retorno de la respuesta en caso de error */
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
  }
}
