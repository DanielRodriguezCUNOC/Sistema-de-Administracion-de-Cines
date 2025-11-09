package com.api.gestion.cine.dto.movie;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

public class ListMovieDTO {

  private int idPelicula;
  private int idUsuario;
  private String tituloPelicula;
  private String sinopsis;
  private int duracion;
  private String reparto;
  private String director;
  private String clasificacion;
  @JsonFormat(pattern = "yyyy-MM-dd")
  @JsonSerialize(using = LocalDateSerializer.class)
  private LocalDate fechaEstreno;
  private BigDecimal precioPelicula;

  public int getIdPelicula() {
    return idPelicula;
  }

  public void setIdPelicula(int idPelicula) {
    this.idPelicula = idPelicula;
  }

  public int getIdUsuario() {
    return idUsuario;
  }

  public void setIdUsuario(int idUsuario) {
    this.idUsuario = idUsuario;
  }

  public String getTituloPelicula() {
    return tituloPelicula;
  }

  public void setTituloPelicula(String tituloPelicula) {
    this.tituloPelicula = tituloPelicula;
  }

  public String getSinopsis() {
    return sinopsis;
  }

  public void setSinopsis(String sinopsis) {
    this.sinopsis = sinopsis;
  }

  public int getDuracion() {
    return duracion;
  }

  public void setDuracion(int duracion) {
    this.duracion = duracion;
  }

  public String getReparto() {
    return reparto;
  }

  public void setReparto(String reparto) {
    this.reparto = reparto;
  }

  public String getDirector() {
    return director;
  }

  public void setDirector(String director) {
    this.director = director;
  }

  public String getClasificacion() {
    return clasificacion;
  }

  public void setClasificacion(String clasificacion) {
    this.clasificacion = clasificacion;
  }

  public LocalDate getFechaEstreno() {
    return fechaEstreno;
  }

  public void setFechaEstreno(LocalDate fechaEstreno) {
    this.fechaEstreno = fechaEstreno;
  }

  public BigDecimal getPrecioPelicula() {
    return precioPelicula;
  }

  public void setPrecioPelicula(BigDecimal precioPelicula) {
    this.precioPelicula = precioPelicula;
  }

}
