package com.api.gestion.cine.resources.reports.sysadmin_reports;

import jakarta.ws.rs.Produces;

import com.api.gestion.cine.dto.reports.sysadmin.profit_report.ProfitReportResponseDTO;
import com.api.gestion.cine.services.reports.sysadmin.ProfitReportService;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("sysadmin/report/profit")
public class ProfitReportResource {

    @GET
    @Path("inicio/{fechaInicio}/fin/{fechaFin}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProfitReport(
            @PathParam("fechaInicio") String fechaInicio,
            @PathParam("fechaFin") String fechaFin) {
        try {
            // * Creación del servicio de informes de ganancias */
            ProfitReportService profitReportService = new ProfitReportService();

            // * Generación del informe de ganancias */
            ProfitReportResponseDTO report = profitReportService.generateProfitReport(fechaInicio, fechaFin);

            // * Retorno de la respuesta exitosa */
            return Response.ok(report).build();
        } catch (Exception e) {
            e.printStackTrace();
            // ! Retorno de la respuesta en caso de error */
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
    }
}
