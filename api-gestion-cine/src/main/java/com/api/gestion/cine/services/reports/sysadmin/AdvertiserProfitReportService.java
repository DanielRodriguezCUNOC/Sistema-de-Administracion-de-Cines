package com.api.gestion.cine.services.reports.sysadmin;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.api.gestion.cine.db.sysadmin.AdvertiserProfitDB;
import com.api.gestion.cine.dto.reports.sysadmin.advertiser_profit_report.AdvertiserList;
import com.api.gestion.cine.dto.reports.sysadmin.advertiser_profit_report.AdvertiserProfitReportResponseDTO;
import com.api.gestion.cine.exceptions.NotFoundDataDBException;
import com.api.gestion.cine.services.util.ValidatorCustom;

public class AdvertiserProfitReportService {

    public AdvertiserProfitReportResponseDTO getReport(String fechaInicio, String fechaFin, String nombreAnunciante)
            throws NotFoundDataDBException, Exception {

        AdvertiserProfitDB profitReportDB = new AdvertiserProfitDB();
        AdvertiserProfitReportResponseDTO report = new AdvertiserProfitReportResponseDTO();

        // * Normalizar los parámetros que vienen del frontend
        fechaInicio = normalizeParam(fechaInicio);
        fechaFin = normalizeParam(fechaFin);
        nombreAnunciante = normalizeParam(nombreAnunciante);

        LocalDate startDate = null;
        LocalDate endDate = null;

        // * Validar y convertir las fechas
        if (!ValidatorCustom.isNullOrEmpty(fechaInicio) && !ValidatorCustom.isNullOrEmpty(fechaFin)) {

            if (!ValidatorCustom.isValidDate(fechaInicio, fechaFin)) {
                throw new IllegalArgumentException("Las fechas proporcionadas no son válidas.");
            }

            LocalDate[] dates = ValidatorCustom.convertDateStringToLocalDate(fechaInicio, fechaFin);
            startDate = dates[0];
            endDate = dates[1];

            if (startDate.isAfter(endDate)) {
                throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha de fin.");
            }
        }

        try {
            List<AdvertiserList> advertisers = profitReportDB.getAdvertiserReport(startDate, endDate, nombreAnunciante);

            report.setAdvertiserList(advertisers);
            report.calculateTotalProfit();

            if (report.getAdvertiserList() == null || report.getAdvertiserList().isEmpty()) {
                throw new NotFoundDataDBException("No se encontraron datos para los parámetros proporcionados.");

            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener el reporte de ganancias por anunciante.", e);
        }

        return report;
    }

    /*
     * Convierte "null", valores vacíos o solo espacios en null.
     */
    private String normalizeParam(String param) {
        if (param == null)
            return null;

        String trimmed = param.trim();
        if (trimmed.isEmpty() || "null".equalsIgnoreCase(trimmed)) {
            return null;
        }
        return trimmed;
    }
}
