package com.api.gestion.cine.dto.reports.cinema_admin.sold_ticket_report;

import java.math.BigDecimal;

public class SoldTicketResponseReportDTO {

    private SoldTicketData[] data;
    private BigDecimal totalDinero;

    public SoldTicketResponseReportDTO() {
    }

    public SoldTicketData[] getData() {
        return data;
    }

    public void setData(SoldTicketData[] data) {
        this.data = data;
    }

    public BigDecimal getTotalDinero() {
        return totalDinero;
    }

    public void setTotalDinero(BigDecimal totalDinero) {
        this.totalDinero = totalDinero;
    }
}
