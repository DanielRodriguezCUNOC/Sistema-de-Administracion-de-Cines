package com.api.gestion.cine.dto.reports.sysadmin.purchased_advertisement_report;

public class PurchasedAdvertisementResponseReportDTO {

    private PurchasedAdvertisement[] purchasedAdvertisements;

    public PurchasedAdvertisementResponseReportDTO() {
    }

    public PurchasedAdvertisementResponseReportDTO(PurchasedAdvertisement[] purchasedAdvertisements) {
        this.purchasedAdvertisements = purchasedAdvertisements;
    }

    public PurchasedAdvertisement[] getPurchasedAdvertisements() {
        return purchasedAdvertisements;
    }

    public void setPurchasedAdvertisements(PurchasedAdvertisement[] purchasedAdvertisements) {
        this.purchasedAdvertisements = purchasedAdvertisements;
    }
}
