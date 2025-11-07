package com.api.gestion.cine.services.reports.sysadmin;

import java.time.LocalDate;
import java.util.List;

import com.api.gestion.cine.db.sysadmin.AdvertiserProfitDB;
import com.api.gestion.cine.dto.reports.sysadmin.advertiser_profit_report.AdvertiserList;
import com.api.gestion.cine.dto.reports.sysadmin.advertiser_profit_report.AdvertiserProfitReportResponseDTO;
import com.api.gestion.cine.services.util.ValidatorCustom;

public class AdvertiserProfitReportService {

    public AdvertiserProfitReportResponseDTO getReport(String fechaInicio, String fechaFin, String nombreAnunciante) {

        AdvertiserProfitDB profitReportDB = new AdvertiserProfitDB();
        AdvertiserProfitReportResponseDTO report = new AdvertiserProfitReportResponseDTO();

        LocalDate startDate = null;
        LocalDate endDate = null;

        if (ValidatorCustom.isValidDate(fechaInicio, fechaFin)) {
            LocalDate[] dates = ValidatorCustom.convertDateStringToLocalDate(fechaInicio, fechaFin);
            startDate = dates[0];
            endDate = dates[1];

        }

        if (ValidatorCustom.isNullOrEmpty(nombreAnunciante)) {
            nombreAnunciante = null;
        }

        try {
            List<AdvertiserList> advertisers = profitReportDB.getAdvertiserReport(startDate, endDate, nombreAnunciante);

            report.setAdvertiserList(advertisers.toArray(new AdvertiserList[0]));

            report.calculateTotalProfit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return report;

    }

}
