package com.api.gestion.cine.dto.reports.cinema_admin.sold_ticket_report;

import java.math.BigDecimal;
import java.util.List;

public class SoldTicketResponseReportDTO {

    private List<SoldTicketData> data;
    private BigDecimal totalDinero;

    public SoldTicketResponseReportDTO() {
    }

    public List<SoldTicketData> getData() {
        return data;
    }

    public void setData(List<SoldTicketData> data) {
        this.data = data;
    }

    public BigDecimal getTotalDinero() {
        return totalDinero;
    }

    public void setTotalDinero(BigDecimal totalDinero) {
        this.totalDinero = totalDinero;
    }
}
