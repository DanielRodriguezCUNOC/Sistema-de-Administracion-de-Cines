package com.api.gestion.cine.dto.reports.sysadmin.profit_report;

import java.math.BigDecimal;

public class ProfitReportResponseDTO {

    private CinemaCostReport[] costoCinema;
    private AdvertisementProfitReport[] advertisementPaymentAmount;
    private AdvertisementProfitReport[] amountAdBlock;

    private BigDecimal totalProfit;
    private BigDecimal totalRevenue;
    private BigDecimal totalExpenses;

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

    public BigDecimal getTotalProfit() {
        return totalProfit;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public BigDecimal getTotalExpenses() {
        return totalExpenses;
    }

    public BigDecimal totalExpenses() {
        BigDecimal totalCostCinema = BigDecimal.ZERO;
        for (CinemaCostReport report : costoCinema) {
            for (int cost : report.getCostos()) {
                totalCostCinema = totalCostCinema.add(BigDecimal.valueOf(cost));
            }
        }
        return totalCostCinema;
    }

    public BigDecimal totalRevenue() {
        BigDecimal totalAdvertisementPaymentAmount = BigDecimal.ZERO;
        BigDecimal totalAmountAdBlock = BigDecimal.ZERO;
        for (AdvertisementProfitReport report : advertisementPaymentAmount) {
            totalAdvertisementPaymentAmount = totalAdvertisementPaymentAmount
                    .add(BigDecimal.valueOf(report.getAmount()));
        }
        for (AdvertisementProfitReport report : amountAdBlock) {
            totalAmountAdBlock = totalAmountAdBlock.add(BigDecimal.valueOf(report.getAmount()));
        }
        return totalAdvertisementPaymentAmount.add(totalAmountAdBlock);
    }

    public BigDecimal totalProfit() {
        return totalRevenue().subtract(totalExpenses());
    }

    public void assignValues() {
        this.totalExpenses = totalExpenses();
        this.totalRevenue = totalRevenue();
        this.totalProfit = this.totalRevenue.subtract(this.totalExpenses);
    }
}
