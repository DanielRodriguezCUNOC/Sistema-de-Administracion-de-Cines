package com.api.gestion.cine.resources.reports.cinema_admin_reports;

import com.api.gestion.cine.dto.reports.cinema_admin.proyected_movies_report.ProyectedMovieResponseReportDTO;
import com.api.gestion.cine.services.reports.cinema_admin.ProyectedMoviesReportService;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("admin-cinema/report/proyected-movies")
public class ProyectedMoviesReportResource {

  @GET
  @Path("inicio/{fechaInicio}/fin/{fechaFin}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getResponse(@PathParam("fechaInicio") String fechaInicio,
      @PathParam("fechaFin") String fechaFin) {
    try {

      // * Creación del servicio de informes de anuncios comprados */
      ProyectedMoviesReportService service = new ProyectedMoviesReportService();

      // * Generación del informe de anuncios comprados */
      ProyectedMovieResponseReportDTO report = service.generateReport(fechaInicio,
          fechaFin);

      // * Retorno de la respuesta exitosa */
      return Response.ok(report).build();
    } catch (Exception e) {
      // ! Retorno de la respuesta en caso de error */
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
  }
}
