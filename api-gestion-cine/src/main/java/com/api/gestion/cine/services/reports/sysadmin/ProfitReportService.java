package com.api.gestion.cine.services.reports.sysadmin;

import java.time.LocalDate;

import com.api.gestion.cine.dto.reports.sysadmin.profit_report.AdvertisementProfitReport;
import com.api.gestion.cine.dto.reports.sysadmin.profit_report.CinemaCostReport;
import com.api.gestion.cine.dto.reports.sysadmin.profit_report.ProfitReportResponseDTO;

public class ProfitReportService {

        public ProfitReportResponseDTO generateProfitReport(String fechaInicio, String fechaFin) {
                /*
                 * Lógica simulada para generar el informe de ganancias, estos datos vendran de
                 * una consulta a la base de datos
                 */

                ProfitReportResponseDTO report = new ProfitReportResponseDTO();

                // Costos de cines
                CinemaCostReport[] costoCinema = {
                                new CinemaCostReport(1, "Cine Central", LocalDate.of(2024, 10, 15),
                                                new int[] { 5000, 3000, 2000 }),
                                new CinemaCostReport(2, "Cine Norte", LocalDate.of(2024, 10, 15),
                                                new int[] { 4000, 2500 }),
                                new CinemaCostReport(3, "Cine Sur", LocalDate.of(2024, 10, 15),
                                                new int[] { 6000, 3500, 1500 })
                };

                // Pagos por anuncios
                AdvertisementProfitReport[] advertisementPayments = {
                                new AdvertisementProfitReport(1, "Anuncio Coca-Cola", LocalDate.of(2024, 10, 15), 8000),
                                new AdvertisementProfitReport(2, "Anuncio Samsung", LocalDate.of(2024, 10, 20), 12000),
                                new AdvertisementProfitReport(3, "Anuncio Nike", LocalDate.of(2024, 10, 25), 10000)
                };

                // Pagos por bloqueo de anuncios (AdBlock)
                AdvertisementProfitReport[] adBlockPayments = {
                                new AdvertisementProfitReport(4, "Usuario Premium 1", LocalDate.of(2024, 10, 10), 500),
                                new AdvertisementProfitReport(5, "Usuario Premium 2", LocalDate.of(2024, 10, 18), 500),
                                new AdvertisementProfitReport(6, "Usuario Premium 3", LocalDate.of(2024, 10, 22), 500)
                };
                report.setCostoCinema(costoCinema);
                report.setAdvertisementPaymentAmount(advertisementPayments);
                report.setAmountAdBlock(adBlockPayments);

                report.assignValues();

                return report;
        }

}
