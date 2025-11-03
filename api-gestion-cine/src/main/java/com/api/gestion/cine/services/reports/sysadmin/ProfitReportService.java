package com.api.gestion.cine.services.reports.sysadmin;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import com.api.gestion.cine.dto.reports.sysadmin.profit_report.AdvertisementProfitReport;
import com.api.gestion.cine.dto.reports.sysadmin.profit_report.CinemaCostReport;
import com.api.gestion.cine.dto.reports.sysadmin.profit_report.ProfitReportResponseDTO;
import com.api.gestion.cine.services.util.FormatterDateCustom;

public class ProfitReportService {

        public ProfitReportResponseDTO generateReport(String fechaInicio, String fechaFin) {

                LocalDate startDate = FormatterDateCustom.parseStringToDate(fechaInicio);
                LocalDate endDate = FormatterDateCustom.parseStringToDate(fechaFin);

                /*
                 * Lógica simulada para generar el informe de ganancias, estos datos vendran de
                 * una consulta a la base de datos
                 */

                ProfitReportResponseDTO report = new ProfitReportResponseDTO();

                // *Costos de cines
                CinemaCostReport[] costoCinema = {
                                new CinemaCostReport(1, "Cine Central",
                                                Arrays.asList(LocalDate.of(2024, 10, 11), LocalDate.of(2024, 10, 21),
                                                                LocalDate.of(2024, 10, 31)),
                                                new BigDecimal[] { BigDecimal.valueOf(5000), BigDecimal.valueOf(3000),
                                                                BigDecimal.valueOf(2000) }),
                                new CinemaCostReport(2, "Cine Norte",
                                                Arrays.asList(LocalDate.of(2024, 10, 12),
                                                                LocalDate.of(2024, 10, 22)),
                                                new BigDecimal[] { BigDecimal.valueOf(4000),
                                                                BigDecimal.valueOf(2500) }),
                                new CinemaCostReport(3, "Cine Sur",
                                                Arrays.asList(LocalDate.of(2024, 10, 10),
                                                                LocalDate.of(2024, 10, 20),
                                                                LocalDate.of(2024, 10, 30)),
                                                new BigDecimal[] { BigDecimal.valueOf(6000), BigDecimal.valueOf(3500),
                                                                BigDecimal.valueOf(1500) })
                };

                // * Pagos por anuncios
                AdvertisementProfitReport[] advertisementPayments = {
                                new AdvertisementProfitReport(1, "Anuncio Coca-Cola", LocalDate.of(2024, 10, 15), 8000),
                                new AdvertisementProfitReport(2, "Anuncio Samsung", LocalDate.of(2024, 10, 20), 12000),
                                new AdvertisementProfitReport(3, "Anuncio Nike", LocalDate.of(2024, 10, 25), 10000)
                };

                // *Pagos por bloqueo de anuncios (AdBlock)
                AdvertisementProfitReport[] adBlockPayments = {
                                new AdvertisementProfitReport(4, "Usuario Premium 1", LocalDate.of(2024, 10, 10), 500),
                                new AdvertisementProfitReport(5, "Usuario Premium 2", LocalDate.of(2024, 10, 18), 500),
                                new AdvertisementProfitReport(6, "Usuario Premium 3", LocalDate.of(2024, 10, 22), 500)
                };
                for (CinemaCostReport c : costoCinema) {
                        c.calcularCostoTotal();
                }
                report.setCostoCinema(costoCinema);
                report.setAdvertisementPaymentAmount(advertisementPayments);
                report.setAmountAdBlock(adBlockPayments);

                // * Asignar valores totales */
                report.assignValues();

                return report;
        }

}
