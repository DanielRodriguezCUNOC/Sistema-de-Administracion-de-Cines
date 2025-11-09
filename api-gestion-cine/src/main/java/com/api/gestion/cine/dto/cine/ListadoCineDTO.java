package com.api.gestion.cine.dto.cine;

import java.util.List;

import com.api.gestion.cine.model.cine.CineModel;

public class ListadoCineDTO {

  private List<CineModel> cines;

  public List<CineModel> getCines() {
    return cines;
  }

  public void setCines(List<CineModel> cines) {
    this.cines = cines;
  }

}
