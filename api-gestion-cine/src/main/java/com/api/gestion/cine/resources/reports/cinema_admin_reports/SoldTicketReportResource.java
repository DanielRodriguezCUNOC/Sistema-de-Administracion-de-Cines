package com.api.gestion.cine.resources.reports.cinema_admin_reports;

import com.api.gestion.cine.dto.reports.cinema_admin.sold_ticket_report.SoldTicketResponseReportDTO;
import com.api.gestion.cine.services.reports.cinema_admin.SoldTicketReportService;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("admin-cinema/report/sold-ticket")
public class SoldTicketReportResource {

  @GET
  @Path("inicio/{fechaInicio}/fin/{fechaFin}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getResponse(@PathParam("fechaInicio") String fechaInicio,
      @PathParam("fechaFin") String fechaFin) {
    try {

      // * Creación del servicio de informes de anuncios comprados */
      SoldTicketReportService service = new SoldTicketReportService();

      // * Generación del informe de anuncios comprados */
      SoldTicketResponseReportDTO report = service.generateReport(fechaInicio,
          fechaFin);

      // * Retorno de la respuesta exitosa */
      return Response.ok(report).build();
    } catch (Exception e) {
      // ! Retorno de la respuesta en caso de error */
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
  }

}
