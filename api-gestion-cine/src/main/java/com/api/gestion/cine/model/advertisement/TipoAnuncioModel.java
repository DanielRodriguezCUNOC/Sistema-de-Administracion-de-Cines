package com.api.gestion.cine.model.advertisement;

public class TipoAnuncioModel {
  private int idTipoAnuncio;
  private String tipoAnuncio;

  public TipoAnuncioModel() {
  }

  public TipoAnuncioModel(int idTipoAnuncio, String tipoAnuncio) {
    this.idTipoAnuncio = idTipoAnuncio;
    this.tipoAnuncio = tipoAnuncio;
  }

  public int getIdTipoAnuncio() {
    return idTipoAnuncio;
  }

  public void setIdTipoAnuncio(int idTipoAnuncio) {
    this.idTipoAnuncio = idTipoAnuncio;
  }

  public String getNombreTipoAnuncio() {
    return tipoAnuncio;
  }

  public void setNombreTipoAnuncio(String tipoAnuncio) {
    this.tipoAnuncio = tipoAnuncio;
  }
}
