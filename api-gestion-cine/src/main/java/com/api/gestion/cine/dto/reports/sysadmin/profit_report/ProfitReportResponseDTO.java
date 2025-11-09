package com.api.gestion.cine.dto.reports.sysadmin.profit_report;

import java.math.BigDecimal;
import java.util.List;

public class ProfitReportResponseDTO {

    private List<CinemaCostReport> costoCinema;
    private List<AdvertisementProfitReport> advertisementPaymentAmount;
    private List<AdvertisementProfitReport> amountAdBlock;
    private BigDecimal totalProfit;
    private BigDecimal totalRevenue;
    private BigDecimal totalExpenses;

    public ProfitReportResponseDTO() {
    }

    public ProfitReportResponseDTO(List<CinemaCostReport> costoCinema,
            List<AdvertisementProfitReport> advertisementPaymentAmount,
            List<AdvertisementProfitReport> amountAdBlock) {
        this.costoCinema = costoCinema;
        this.advertisementPaymentAmount = advertisementPaymentAmount;
        this.amountAdBlock = amountAdBlock;
    }

    public List<CinemaCostReport> getCostoCinema() {
        return costoCinema;
    }

    public void setCostoCinema(List<CinemaCostReport> costoCinema) {
        this.costoCinema = costoCinema;
    }

    public List<AdvertisementProfitReport> getAdvertisementPaymentAmount() {
        return advertisementPaymentAmount;
    }

    public void setAdvertisementPaymentAmount(List<AdvertisementProfitReport> advertisementPaymentAmount) {
        this.advertisementPaymentAmount = advertisementPaymentAmount;
    }

    public List<AdvertisementProfitReport> getAmountAdBlock() {
        return amountAdBlock;
    }

    public void setAmountAdBlock(List<AdvertisementProfitReport> amountAdBlock) {
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
            for (BigDecimal cost : report.getCostos()) {
                totalCostCinema = totalCostCinema.add(cost);
            }
        }
        return totalCostCinema;
    }

    public BigDecimal totalRevenue() {
        BigDecimal totalAdvertisementPaymentAmount = BigDecimal.ZERO;
        BigDecimal totalAmountAdBlock = BigDecimal.ZERO;
        for (AdvertisementProfitReport report : advertisementPaymentAmount) {
            totalAdvertisementPaymentAmount = totalAdvertisementPaymentAmount.add(report.getAmount());
        }
        for (AdvertisementProfitReport report : amountAdBlock) {
            totalAmountAdBlock = totalAmountAdBlock.add(report.getAmount());
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
