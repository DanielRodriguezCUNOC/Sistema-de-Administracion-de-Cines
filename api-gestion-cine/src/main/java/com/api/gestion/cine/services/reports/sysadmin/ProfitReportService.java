package com.api.gestion.cine.services.reports.sysadmin;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.api.gestion.cine.db.sysadmin.ProfitReportDB;
import com.api.gestion.cine.dto.reports.sysadmin.profit_report.AdvertisementProfitReport;
import com.api.gestion.cine.dto.reports.sysadmin.profit_report.CinemaCostReport;
import com.api.gestion.cine.dto.reports.sysadmin.profit_report.ProfitReportResponseDTO;
import com.api.gestion.cine.exceptions.ReportServiceException;
import com.api.gestion.cine.services.util.ValidatorCustom;

public class ProfitReportService {
        public ProfitReportResponseDTO generateReport(String fechaInicio, String fechaFin) throws Exception {
                ProfitReportResponseDTO report = new ProfitReportResponseDTO();
                ProfitReportDB profitReportDB = new ProfitReportDB();

                LocalDate startDate = null;
                LocalDate endDate = null;

                try {
                        // Validación usando tu ValidatorCustom
                        if (!ValidatorCustom.isNullOrEmpty(fechaInicio) || !ValidatorCustom.isNullOrEmpty(fechaFin)) {
                                if (!ValidatorCustom.isValidDate(fechaInicio, fechaFin)) {
                                        throw new ReportServiceException(
                                                        "Formato de fecha inválido. Debe ser yyyy-MM-dd");
                                }

                                LocalDate[] dates = ValidatorCustom.convertDateStringToLocalDate(fechaInicio, fechaFin);
                                startDate = dates[0];
                                endDate = dates[1];
                        }

                        // 🔹 Si no hay fechas, las pasamos como null al DB → traer todos los datos
                        List<CinemaCostReport> cinemaCosts = profitReportDB.getCinemaCosts(startDate, endDate);
                        List<AdvertisementProfitReport> adPayments = profitReportDB.getAdvertisementPayments(startDate,
                                        endDate);
                        List<AdvertisementProfitReport> adBlockPayments = profitReportDB.getAdBlockPayments(startDate,
                                        endDate);

                        // Calcular costos
                        for (CinemaCostReport cinemaCost : cinemaCosts) {
                                if (cinemaCost.getCostos() != null) {
                                        cinemaCost.calcularCostoTotal();
                                } else {
                                        cinemaCost.setCostoTotal(BigDecimal.ZERO);
                                }
                        }

                        // Asignar datos al reporte
                        report.setCostoCinema(cinemaCosts);
                        report.setAdvertisementPaymentAmount(adPayments);
                        report.setAmountAdBlock(adBlockPayments);
                        report.assignValues();

                        return report;

                } catch (Exception e) {
                        throw new ReportServiceException("Error al generar informe de ganancias", e);
                }
        }

}
