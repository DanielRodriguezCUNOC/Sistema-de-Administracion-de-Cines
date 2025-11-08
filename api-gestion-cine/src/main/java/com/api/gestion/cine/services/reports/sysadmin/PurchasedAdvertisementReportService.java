package com.api.gestion.cine.services.reports.sysadmin;

import java.time.LocalDate;
import java.util.List;

import com.api.gestion.cine.db.sysadmin.PurchasedAdvertisementDB;
import com.api.gestion.cine.dto.reports.sysadmin.purchased_advertisement_report.PurchasedAdvertisement;
import com.api.gestion.cine.dto.reports.sysadmin.purchased_advertisement_report.PurchasedAdvertisementResponseReportDTO;
import com.api.gestion.cine.exceptions.ReportServiceException;
import com.api.gestion.cine.services.util.ValidatorCustom;

public class PurchasedAdvertisementReportService {

  public PurchasedAdvertisementResponseReportDTO generateReport(String fechaInicio,
      String fechaFin, String tipoAnuncio, int offset, int limit) throws ReportServiceException {

    PurchasedAdvertisementDB purchasesAdDB = new PurchasedAdvertisementDB();
    PurchasedAdvertisementResponseReportDTO report = new PurchasedAdvertisementResponseReportDTO();
    LocalDate startDate = null;
    LocalDate endDate = null;

    if (ValidatorCustom.isValidDate(fechaInicio, fechaFin)) {
      LocalDate[] dates = ValidatorCustom.convertDateStringToLocalDate(fechaInicio, fechaFin);
      startDate = dates[0];
      endDate = dates[1];
    }
    if (ValidatorCustom.isNullOrEmpty(tipoAnuncio)) {
      tipoAnuncio = null;
    }

    // * Obtener anuncios comprados desde la base de datos */
    List<PurchasedAdvertisement> purchasedAdvertisements;

    try {
      purchasedAdvertisements = purchasesAdDB.getPurchasedAdvertisements(startDate, endDate, tipoAnuncio, offset,
          limit);

    } catch (Exception e) {
      throw new ReportServiceException("Error al obtener anuncios comprados", e);
    }

    report.setPurchasedAdvertisements(purchasedAdvertisements);

    return report;
  }

}
