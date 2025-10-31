package com.api.gestion.cine.dto.reports.cinema_admin.proyected_movies_report;

public class MovieProyectedRoom {

    private int idSala;
    private String nombreSala;
    private String titulosPeliculas[];
    private int cantidadPeliculasProyectadas;

    public MovieProyectedRoom() {
    }

    public MovieProyectedRoom(int idSala, String nombreSala, String[] titulosPeliculas,
            int cantidadPeliculasProyectadas) {
        this.idSala = idSala;
        this.nombreSala = nombreSala;
        this.titulosPeliculas = titulosPeliculas;
        this.cantidadPeliculasProyectadas = cantidadPeliculasProyectadas;
    }

    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public String getNombreSala() {
        return nombreSala;
    }

    public void setNombreSala(String nombreSala) {
        this.nombreSala = nombreSala;
    }

    public String[] getTitulosPeliculas() {
        return titulosPeliculas;
    }

    public void setTitulosPeliculas(String[] titulosPeliculas) {
        this.titulosPeliculas = titulosPeliculas;
    }

    public int getCantidadPeliculasProyectadas() {
        return cantidadPeliculasProyectadas;
    }

    public void setCantidadPeliculasProyectadas(int cantidadPeliculasProyectadas) {
        this.cantidadPeliculasProyectadas = cantidadPeliculasProyectadas;
    }

    public void calcularCantidadPeliculasProyectadas() {
        if (titulosPeliculas != null) {
            this.cantidadPeliculasProyectadas = titulosPeliculas.length;
        } else {
            this.cantidadPeliculasProyectadas = 0;
        }
    }

}
