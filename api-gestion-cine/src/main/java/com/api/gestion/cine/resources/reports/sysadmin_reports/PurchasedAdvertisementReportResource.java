package com.api.gestion.cine.resources.reports.sysadmin_reports;

import com.api.gestion.cine.dto.reports.sysadmin.purchased_advertisement_report.PurchasedAdvertisementResponseReportDTO;
import com.api.gestion.cine.services.reports.sysadmin.PurchasedAdvertisementReportService;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("sysadmin/report/purchased-advertisements")
public class PurchasedAdvertisementReportResource {

  @GET
  @Path("inicio/{fechaInicio}/fin/{fechaFin}/tipo-anuncio/{tipoAnuncio}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getResponse(@PathParam("fechaInicio") String fechaInicio,
      @PathParam("fechaFin") String fechaFin,
      @PathParam("tipoAnuncio") String tipoAnuncio) {
    try {

      // * Creación del servicio de informes de anuncios comprados */
      PurchasedAdvertisementReportService service = new PurchasedAdvertisementReportService();

      // * Generación del informe de anuncios comprados */
      PurchasedAdvertisementResponseReportDTO report = service.generateReport(fechaInicio,
          fechaFin, tipoAnuncio);

      // * Retorno de la respuesta exitosa */
      return Response.ok(report).build();
    } catch (Exception e) {
      // ! Retorno de la respuesta en caso de error */
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
  }
}
