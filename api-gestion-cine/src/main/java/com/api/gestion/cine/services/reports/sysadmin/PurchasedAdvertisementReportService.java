package com.api.gestion.cine.services.reports.sysadmin;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.api.gestion.cine.dto.reports.sysadmin.purchased_advertisement_report.PurchasedAdvertisement;
import com.api.gestion.cine.dto.reports.sysadmin.purchased_advertisement_report.PurchasedAdvertisementResponseReportDTO;
import com.api.gestion.cine.services.util.FormatterDateCustom;

public class PurchasedAdvertisementReportService {

  public PurchasedAdvertisementResponseReportDTO generateReport(String fechaInicio,
      String fechaFin, String tipoAnuncio) {

    LocalDate startDate = FormatterDateCustom.parseStringToDate(fechaInicio);
    LocalDate endDate = FormatterDateCustom.parseStringToDate(fechaFin);

    PurchasedAdvertisementResponseReportDTO report = new PurchasedAdvertisementResponseReportDTO();

    PurchasedAdvertisement[] purchasedAdvertisements = {
        new PurchasedAdvertisement(1, "Campaña A", "Anuncio de texto", LocalDate.of(2024, 10, 12),
            BigDecimal.valueOf(1500)),
        new PurchasedAdvertisement(2, "Campaña B", "Anuncio de texto e imagen", LocalDate.of(2024, 10, 15),
            BigDecimal.valueOf(2500)),
        new PurchasedAdvertisement(3, "Campaña C", "Anuncio de video y texto", LocalDate.of(2024, 10, 18),
            BigDecimal.valueOf(3500))
    };
    report.setPurchasedAdvertisements(purchasedAdvertisements);

    if (startDate != null && endDate != null) {
      // *Filtrar los anuncios comprados por el rango de fechas
      return report;
    } else if (startDate != null && endDate != null && tipoAnuncio != "Todo") {
      // *Filtrar los anuncios comprados por el rango de fechas y por tipo de anuncio
    } else if (startDate == null && endDate == null && tipoAnuncio != "Todo") {
      // *Filtrar los anuncios comprados por el tipo de anuncio
    }
    // !Si las fechas son nulas se retorna todo de la base de datos
    return report;
  }
}
