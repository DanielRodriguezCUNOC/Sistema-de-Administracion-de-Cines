package com.api.gestion.cine.dto.reports.cinema_admin.proyected_movies_report;

import java.util.List;

public class ProyectedMovieResponseReportDTO {

    private List<MovieProyectedRoom> peliculasProyectadas;

    public ProyectedMovieResponseReportDTO() {
    }

    public ProyectedMovieResponseReportDTO(List<MovieProyectedRoom> peliculasProyectadas) {
        this.peliculasProyectadas = peliculasProyectadas;
    }

    public List<MovieProyectedRoom> getPeliculasProyectadas() {
        return peliculasProyectadas;
    }

    public void setPeliculasProyectadas(List<MovieProyectedRoom> peliculasProyectadas) {
        this.peliculasProyectadas = peliculasProyectadas;
    }

}
