package com.api.gestion.cine.dto.reports.sysadmin.advertiser_profit_report;

import java.math.BigDecimal;
import java.util.List;

import com.api.gestion.cine.dto.reports.sysadmin.purchased_advertisement_report.PurchasedAdvertisement;

public class AdvertiserProfitReportResponseDTO {
    private List<AdvertiserList> advertiserList;
    private BigDecimal totalProfit;

    public AdvertiserProfitReportResponseDTO() {
    }

    public AdvertiserProfitReportResponseDTO(List<AdvertiserList> advertiserList, BigDecimal totalProfit) {
        this.advertiserList = advertiserList;
        this.totalProfit = totalProfit;
    }

    public BigDecimal getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(BigDecimal totalProfit) {
        this.totalProfit = totalProfit;
    }

    public List<AdvertiserList> getAdvertiserList() {
        return advertiserList;
    }

    public void setAdvertiserList(List<AdvertiserList> advertiserList) {
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
