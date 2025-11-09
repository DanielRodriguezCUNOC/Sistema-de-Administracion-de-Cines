package com.api.gestion.cine.model.cine;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

public class CineModel {
  private int idCine;
  private String nombreCine;
  @JsonFormat(pattern = "yyyy-MM-dd")
  @JsonSerialize(using = LocalDateSerializer.class)
  private LocalDate fechaCreacion;
  private BigDecimal costoOcultacionAnuncio;

  public CineModel() {
  }

  public int getIdCine() {
    return idCine;
  }

  public String getNombreCine() {
    return nombreCine;
  }

  public LocalDate getFechaCreacion() {
    return fechaCreacion;
  }

  public BigDecimal getCostoOcultacionAnuncio() {
    return costoOcultacionAnuncio;
  }

  public void setIdCine(int idCine) {
    this.idCine = idCine;
  }

  public void setNombreCine(String nombreCine) {
    this.nombreCine = nombreCine;
  }

  public void setFechaCreacion(LocalDate fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
  }

  public void setCostoOcultacionAnuncio(BigDecimal costoOcultacionAnuncio) {
    this.costoOcultacionAnuncio = costoOcultacionAnuncio;
  }

}
