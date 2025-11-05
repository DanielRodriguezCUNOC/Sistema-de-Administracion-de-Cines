package com.api.gestion.cine.resources.reports.sysadmin_reports;

import com.api.gestion.cine.dto.reports.sysadmin.advertiser_profit_report.AdvertiserProfitReportResponseDTO;
import com.api.gestion.cine.services.reports.sysadmin.AdvertiserProfitReportService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("sysadmin/report/advertiser-profit")
public class AdvertiserProfitReportResource {

    @GET
    @Path("inicio/{fechaInicio}/fin/{fechaFin}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdvertiserProfit(
            @PathParam("fechaInicio") String fechaInicio,
            @PathParam("fechaFin") String fechaFin,
            @PathParam("nombreAnunciante") String nombreAnunciante) {

        try {
            AdvertiserProfitReportService service = new AdvertiserProfitReportService();

            AdvertiserProfitReportResponseDTO report = service.getReport(fechaInicio, fechaFin, nombreAnunciante);

            return Response.ok(report).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }
}
