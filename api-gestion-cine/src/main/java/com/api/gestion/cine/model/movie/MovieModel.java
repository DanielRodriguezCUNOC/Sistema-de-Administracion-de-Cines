package com.api.gestion.cine.model.movie;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

public class MovieModel {
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
  private byte[] poster;

  public MovieModel() {
  }

  public int getIdPelicula() {
    return idPelicula;
  }

  public int getIdUsuario() {
    return idUsuario;
  }

  public String getTituloPelicula() {
    return tituloPelicula;
  }

  public String getSinopsis() {
    return sinopsis;
  }

  public int getDuracion() {
    return duracion;
  }

  public String getReparto() {
    return reparto;
  }

  public String getDirector() {
    return director;
  }

  public String getClasificacion() {
    return clasificacion;
  }

  public LocalDate getFechaEstreno() {
    return fechaEstreno;
  }

  public BigDecimal getPrecioPelicula() {
    return precioPelicula;
  }

  public byte[] getPoster() {
    return poster;
  }

  public void setIdPelicula(int idPelicula) {
    this.idPelicula = idPelicula;
  }

  public void setIdUsuario(int idUsuario) {
    this.idUsuario = idUsuario;
  }

  public void setTituloPelicula(String tituloPelicula) {
    this.tituloPelicula = tituloPelicula;
  }

  public void setSinopsis(String sinopsis) {
    this.sinopsis = sinopsis;
  }

  public void setDuracion(int duracion) {
    this.duracion = duracion;
  }

  public void setReparto(String reparto) {
    this.reparto = reparto;
  }

  public void setDirector(String director) {
    this.director = director;
  }

  public void setClasificacion(String clasificacion) {
    this.clasificacion = clasificacion;
  }

  public void setFechaEstreno(LocalDate fechaEstreno) {
    this.fechaEstreno = fechaEstreno;
  }

  public void setPrecioPelicula(BigDecimal precioPelicula) {
    this.precioPelicula = precioPelicula;
  }

  public void setPoster(byte[] poster) {
    this.poster = poster;
  }

}
