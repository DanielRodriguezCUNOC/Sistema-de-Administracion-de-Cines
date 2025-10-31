package com.api.gestion.cine.dto.reports.sysadmin.advertiser_profit_report;

import java.math.BigDecimal;

import com.api.gestion.cine.dto.reports.sysadmin.purchased_advertisement_report.PurchasedAdvertisement;

public class AdvertiserProfitReportResponseDTO {
    private AdvertiserList[] advertiserList;
    private BigDecimal totalProfit;

    public AdvertiserProfitReportResponseDTO() {
    }

    public AdvertiserProfitReportResponseDTO(AdvertiserList[] advertiserList, BigDecimal totalProfit) {
        this.advertiserList = advertiserList;
        this.totalProfit = totalProfit;
    }

    public BigDecimal getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(BigDecimal totalProfit) {
        this.totalProfit = totalProfit;
    }

    public AdvertiserList[] getAdvertiserList() {
        return advertiserList;
    }

    public void setAdvertiserList(AdvertiserList[] advertiserList) {
        this.advertiserList = advertiserList;
    }

    public void calculateTotalProfit() {
        BigDecimal total = BigDecimal.ZERO;
        if (advertiserList != null) {
            for (AdvertiserList advertiser : advertiserList) {
                if (advertiser.getPurchasedAdvertisement() != null) {
                    for (PurchasedAdvertisement ad : advertiser.getPurchasedAdvertisement()) {
                        if (ad.getMontoPago() != null) {
                            total = total.add(ad.getMontoPago());
                        }
                    }
                }
            }
        }
        this.totalProfit = total;
    }

}
