package com.api.gestion.cine.dto.reports.cinema_admin.sold_ticket_report;

import java.math.BigDecimal;
import java.util.List;

public class SoldTicketResponseReportDTO {

    private List<SoldTicketData> boletosVendidos;
    private BigDecimal totalDinero;

    public SoldTicketResponseReportDTO() {
    }

    public List<SoldTicketData> getBoletosVendidos() {
        return boletosVendidos;
    }

    public void setBoletosVendidos(List<SoldTicketData> boletosVendidos) {
        this.boletosVendidos = boletosVendidos;
    }

    public BigDecimal getTotalDinero() {
        return totalDinero;
    }

    public void setTotalDinero(BigDecimal totalDinero) {
        this.totalDinero = totalDinero;
    }
}
