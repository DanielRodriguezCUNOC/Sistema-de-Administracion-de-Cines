package com.api.gestion.cine.dto.reports.sysadmin.advertiser_profit_report;

import java.math.BigDecimal;

import com.api.gestion.cine.dto.reports.sysadmin.purchased_advertisement_report.PurchasedAdvertisement;

public class AdvertiserList {
    private int idUsuario;
    private String nombreUsuario;
    private PurchasedAdvertisement purchasedAdvertisement[];
    private BigDecimal totalPurchasedAmount;

    public AdvertiserList() {
    }

    public AdvertiserList(int idUsuario, String nombreUsuario, PurchasedAdvertisement[] purchasedAdvertisement,
            BigDecimal totalPurchasedAmount) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.purchasedAdvertisement = purchasedAdvertisement;
        this.totalPurchasedAmount = totalPurchasedAmount;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public PurchasedAdvertisement[] getPurchasedAdvertisement() {
        return purchasedAdvertisement;
    }

    public void setPurchasedAdvertisement(PurchasedAdvertisement[] purchasedAdvertisement) {
        this.purchasedAdvertisement = purchasedAdvertisement;
    }

    public BigDecimal getTotalPurchasedAmount() {
        return totalPurchasedAmount;
    }

    public void setTotalPurchasedAmount(BigDecimal totalPurchasedAmount) {
        this.totalPurchasedAmount = totalPurchasedAmount;
    }

    public void calculateTotalPurchasedAmount() {
        BigDecimal total = BigDecimal.ZERO;
        if (purchasedAdvertisement != null) {
            for (PurchasedAdvertisement ad : purchasedAdvertisement) {
                if (ad.getMontoPago() != null) {
                    total = total.add(ad.getMontoPago());
                }
            }
        }
        this.totalPurchasedAmount = total;
    }

}
