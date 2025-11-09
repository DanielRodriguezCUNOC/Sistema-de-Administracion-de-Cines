package com.api.gestion.cine.dto.reports.sysadmin.purchased_advertisement_report;

import java.util.List;

public class PurchasedAdvertisementResponseReportDTO {

    private List<PurchasedAdvertisement> purchasedAdvertisements;

    public PurchasedAdvertisementResponseReportDTO() {
    }

    public PurchasedAdvertisementResponseReportDTO(List<PurchasedAdvertisement> purchasedAdvertisements) {
        this.purchasedAdvertisements = purchasedAdvertisements;
    }

    public List<PurchasedAdvertisement> getPurchasedAdvertisements() {
        return purchasedAdvertisements;
    }

    public void setPurchasedAdvertisements(List<PurchasedAdvertisement> purchasedAdvertisements) {
        this.purchasedAdvertisements = purchasedAdvertisements;
    }
}
