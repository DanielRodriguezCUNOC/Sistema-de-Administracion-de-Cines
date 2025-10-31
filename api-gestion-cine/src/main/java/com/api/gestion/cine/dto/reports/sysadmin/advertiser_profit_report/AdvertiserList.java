package com.api.gestion.cine.dto.reports.sysadmin.advertiser_profit_report;

import com.api.gestion.cine.dto.reports.sysadmin.purchased_advertisement_report.PurchasedAdvertisement;

public class AdvertiserList {
    private int idUsuario;
    private String nombreUsuario;
    private PurchasedAdvertisement purchasedAdvertisement[];

    public AdvertiserList() {
    }

    public AdvertiserList(int idUsuario, String nombreUsuario, PurchasedAdvertisement[] purchasedAdvertisement) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.purchasedAdvertisement = purchasedAdvertisement;
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

}
