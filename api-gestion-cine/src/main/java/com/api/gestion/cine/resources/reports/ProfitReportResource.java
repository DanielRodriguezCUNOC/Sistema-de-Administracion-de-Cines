package com.api.gestion.cine.resources.reports;

import com.api.gestion.cine.dto.reports.sysadmin.ProfitReportResponseDTO;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;

@Path("sysadmin/report/profit")
public class ProfitReportResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ProfitReportResponseDTO getProfitReport(
            @QueryParam("fechaInicio") String fechaInicio,
            @QueryParam("fechaFin") String fechaFin) {
        // Lógica para generar el informe de ganancias
        return new ProfitReportResponseDTO();
    }
}
