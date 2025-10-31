package com.api.gestion.cine.dto.reports.cinema_admin.sold_ticket_report;

public class SoldTicketResponseReportDTO {

    private SoldTicketData[] data;

    public SoldTicketResponseReportDTO() {
    }

    public SoldTicketData[] getData() {
        return data;
    }

    public void setData(SoldTicketData[] data) {
        this.data = data;
    }
}
