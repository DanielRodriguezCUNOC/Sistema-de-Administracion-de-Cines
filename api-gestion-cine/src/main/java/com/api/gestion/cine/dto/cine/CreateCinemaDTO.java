package com.api.gestion.cine.dto.cine;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

public class CreateCinemaDTO {

  private String nombreCine;
  @JsonFormat(pattern = "yyyy-MM-dd")
  @JsonSerialize(using = LocalDateSerializer.class)
  private LocalDate fechaCreacion;
  private BigDecimal costoOcultacionAnuncio;

  public CreateCinemaDTO() {
  }

  public BigDecimal getCostoOcultacionAnuncio() {
    return costoOcultacionAnuncio;
  }

  public void setCostoOcultacionAnuncio(BigDecimal costoOcultacionAnuncio) {
    this.costoOcultacionAnuncio = costoOcultacionAnuncio;
  }

  public String getNombreCine() {
    return nombreCine;
  }

  public void setNombreCine(String nombreCine) {
    this.nombreCine = nombreCine;
  }

  public LocalDate getFechaCreacion() {
    return fechaCreacion;
  }

  public void setFechaCreacion(LocalDate fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
  }

}
