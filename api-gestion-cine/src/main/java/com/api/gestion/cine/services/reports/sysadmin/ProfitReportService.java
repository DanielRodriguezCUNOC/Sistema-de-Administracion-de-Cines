package com.api.gestion.cine.services.reports.sysadmin;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.api.gestion.cine.db.sysadmin.ProfitReportDB;
import com.api.gestion.cine.dto.reports.sysadmin.profit_report.AdvertisementProfitReport;
import com.api.gestion.cine.dto.reports.sysadmin.profit_report.CinemaCostReport;
import com.api.gestion.cine.dto.reports.sysadmin.profit_report.ProfitReportResponseDTO;
import com.api.gestion.cine.exceptions.ReportServiceException;
import com.api.gestion.cine.services.util.FormatterDateCustom;

public class ProfitReportService {

        public ProfitReportResponseDTO generateReport(String fechaInicio, String fechaFin) throws Exception {

                LocalDate startDate = FormatterDateCustom.parseStringToDate(fechaInicio);
                LocalDate endDate = FormatterDateCustom.parseStringToDate(fechaFin);

                ProfitReportResponseDTO report = new ProfitReportResponseDTO();
                ProfitReportDB profitReportDB = new ProfitReportDB();

                // *Obtenemos los dato desde la BD

                try {
                        List<CinemaCostReport> cinemaCosts = profitReportDB.getCinemaCosts(startDate, endDate);
                        List<AdvertisementProfitReport> adPayments = profitReportDB
                                        .getAdvertisementPayments(startDate, endDate);
                        List<AdvertisementProfitReport> adBlockPayments = profitReportDB
                                        .getAdBlockPayments(startDate, endDate);

                        // * Calcular costo total de cines */
                        for (CinemaCostReport cinemaCost : cinemaCosts) {
                                if (cinemaCost.getCostos() != null) {
                                        cinemaCost.calcularCostoTotal();

                                } else {
                                        cinemaCost.setCostoTotal(BigDecimal.ZERO);
                                }
                        }

                        // * Asignamos los datos al reporte */

                        report.setCostoCinema(cinemaCosts);

                        report.setAdvertisementPaymentAmount(adPayments);
                        report.setAmountAdBlock(adBlockPayments);

                        // * Asignar valores totales */
                        report.assignValues();

                        return report;

                } catch (Exception e) {
                        throw new ReportServiceException("Error al generar informe de ganancias", e);
                }

        }

}
