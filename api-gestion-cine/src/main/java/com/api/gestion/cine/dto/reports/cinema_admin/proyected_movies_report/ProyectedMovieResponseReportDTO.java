package com.api.gestion.cine.dto.reports.cinema_admin.proyected_movies_report;

public class ProyectedMovieResponseReportDTO {

    private MovieProyectedRoom[] salasProyectadas;

    public ProyectedMovieResponseReportDTO() {
    }

    public ProyectedMovieResponseReportDTO(MovieProyectedRoom[] salasProyectadas) {
        this.salasProyectadas = salasProyectadas;
    }

    public MovieProyectedRoom[] getSalasProyectadas() {
        return salasProyectadas;
    }

    public void setSalasProyectadas(MovieProyectedRoom[] salasProyectadas) {
        this.salasProyectadas = salasProyectadas;
    }

}
