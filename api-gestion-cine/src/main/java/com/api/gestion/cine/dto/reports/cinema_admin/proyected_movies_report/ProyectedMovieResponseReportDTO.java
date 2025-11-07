package com.api.gestion.cine.dto.reports.cinema_admin.proyected_movies_report;

import java.util.List;

public class ProyectedMovieResponseReportDTO {

    private List<MovieProyectedRoom> salasProyectadas;

    public ProyectedMovieResponseReportDTO() {
    }

    public ProyectedMovieResponseReportDTO(List<MovieProyectedRoom> salasProyectadas) {
        this.salasProyectadas = salasProyectadas;
    }

    public List<MovieProyectedRoom> getSalasProyectadas() {
        return salasProyectadas;
    }

    public void setSalasProyectadas(List<MovieProyectedRoom> salasProyectadas) {
        this.salasProyectadas = salasProyectadas;
    }

}
