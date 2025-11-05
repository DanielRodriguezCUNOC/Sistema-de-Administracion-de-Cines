package com.api.gestion.cine.services.reports.sysadmin;

import java.time.LocalDate;
import java.util.List;

import com.api.gestion.cine.db.sysadmin.PurchaedAdvertisementDB;
import com.api.gestion.cine.dto.reports.sysadmin.purchased_advertisement_report.PurchasedAdvertisement;
import com.api.gestion.cine.dto.reports.sysadmin.purchased_advertisement_report.PurchasedAdvertisementResponseReportDTO;
import com.api.gestion.cine.exceptions.ReportServiceException;
import com.api.gestion.cine.services.util.FormatterDateCustom;

public class PurchasedAdvertisementReportService {

  public PurchasedAdvertisementResponseReportDTO generateReport(String fechaInicio,
      String fechaFin, String tipoAnuncio) throws ReportServiceException {

    LocalDate startDate = null;
    LocalDate endDate = null;
    PurchaedAdvertisementDB purchasesAdDB = new PurchaedAdvertisementDB();

    if (fechaInicio != null && !fechaInicio.trim().isEmpty()) {
      startDate = FormatterDateCustom.parseStringToDate(fechaInicio);
    }
    if (fechaFin != null && !fechaFin.trim().isEmpty()) {
      endDate = FormatterDateCustom.parseStringToDate(fechaFin);
    }
    if (tipoAnuncio == null || tipoAnuncio.trim().isEmpty()) {
      tipoAnuncio = "Todo";
    }

    PurchasedAdvertisementResponseReportDTO report = new PurchasedAdvertisementResponseReportDTO();

    // * Obtener anuncios comprados desde la base de datos */
    List<PurchasedAdvertisement> purchasedAdvertisements;

    try {
      purchasedAdvertisements = purchasesAdDB.getPurchasedAdvertisements(startDate, endDate, tipoAnuncio);
    } catch (Exception e) {
      throw new ReportServiceException("Error retrieving purchased advertisements", e);
    }

    report.setPurchasedAdvertisements(purchasedAdvertisements.toArray(new PurchasedAdvertisement[0]));

    return report;
  }

}
