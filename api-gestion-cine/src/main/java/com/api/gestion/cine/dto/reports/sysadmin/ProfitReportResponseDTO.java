package com.api.gestion.cine.dto.reports.sysadmin;

public class ProfitReportResponseDTO {

    private CinemaCostReport[] costoCinema;
    private AdvertisementProfitReport[] advertisementPaymentAmount;
    private AdvertisementProfitReport[] amountAdBlock;

    public ProfitReportResponseDTO() {
    }

    public ProfitReportResponseDTO(CinemaCostReport[] costoCinema,
            AdvertisementProfitReport[] advertisementPaymentAmount,
            AdvertisementProfitReport[] amountAdBlock) {
        this.costoCinema = costoCinema;
        this.advertisementPaymentAmount = advertisementPaymentAmount;
        this.amountAdBlock = amountAdBlock;
    }

    public CinemaCostReport[] getCostoCinema() {
        return costoCinema;
    }

    public void setCostoCinema(CinemaCostReport[] costoCinema) {
        this.costoCinema = costoCinema;
    }

    public AdvertisementProfitReport[] getAdvertisementPaymentAmount() {
        return advertisementPaymentAmount;
    }

    public void setAdvertisementPaymentAmount(AdvertisementProfitReport[] advertisementPaymentAmount) {
        this.advertisementPaymentAmount = advertisementPaymentAmount;
    }

    public AdvertisementProfitReport[] getAmountAdBlock() {
        return amountAdBlock;
    }

    public void setAmountAdBlock(AdvertisementProfitReport[] amountAdBlock) {
        this.amountAdBlock = amountAdBlock;
    }

    public int totalExpenses() {
        int totalCostCinema = 0;
        for (CinemaCostReport report : costoCinema) {
            for (int cost : report.getCostoTotal()) {
                totalCostCinema += cost;
            }
        }
        return totalCostCinema;
    }

    public int totalRevenue() {
        int totalAdvertisementPaymentAmount = 0;
        int totalAmountAdBlock = 0;
        for (AdvertisementProfitReport report : advertisementPaymentAmount) {
            totalAdvertisementPaymentAmount += report.getAmount();
        }
        for (AdvertisementProfitReport report : amountAdBlock) {
            totalAmountAdBlock += report.getAmount();
        }
        return totalAdvertisementPaymentAmount + totalAmountAdBlock;
    }

    public int totalProfit() {
        return totalRevenue() - totalExpenses();
    }
}
