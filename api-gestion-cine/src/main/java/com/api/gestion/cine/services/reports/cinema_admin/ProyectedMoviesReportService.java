package com.api.gestion.cine.services.reports.cinema_admin;

import java.time.LocalDate;
import java.util.List;

import com.api.gestion.cine.db.cinema_dmin.ProyectedMovieReportDB;
import com.api.gestion.cine.dto.reports.cinema_admin.proyected_movies_report.MovieProyectedRoom;
import com.api.gestion.cine.dto.reports.cinema_admin.proyected_movies_report.ProyectedMovieResponseReportDTO;
import com.api.gestion.cine.exceptions.ReportServiceException;
import com.api.gestion.cine.services.util.FormatterDateCustom;

public class ProyectedMoviesReportService {

  public ProyectedMovieResponseReportDTO generateReport(String fechaInicio, String fechaFin, int offset, int limit,
      String nombreSala) throws ReportServiceException {

    ProyectedMovieReportDB reportDB = new ProyectedMovieReportDB();
    ProyectedMovieResponseReportDTO response = new ProyectedMovieResponseReportDTO();

    LocalDate startDate = null;
    LocalDate endDate = null;

    if (fechaInicio != null && !fechaInicio.trim().isEmpty()) {
      startDate = FormatterDateCustom.parseStringToDate(fechaInicio);
    }

    if (fechaFin != null && !fechaFin.trim().isEmpty()) {
      endDate = FormatterDateCustom.parseStringToDate(fechaFin);
    }

    if (nombreSala == null || nombreSala.trim().isEmpty()) {
      nombreSala = null;
    }

    try {
      List<MovieProyectedRoom> proyectedRooms = reportDB.getProyectedMovies(startDate, endDate, offset, limit,
          nombreSala);

      response.setSalasProyectadas(proyectedRooms);

    } catch (Exception e) {
      throw new ReportServiceException("Error al generar el informe de películas proyectadas", e);
    }
    return response;
  }

}
