package com.api.gestion.cine.services.cine;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.api.gestion.cine.db.cine.CineDB;
import com.api.gestion.cine.dto.cine.CreateCinemaDTO;
import com.api.gestion.cine.dto.cine.ListadoCineDTO;

public class CineService {

  public void crearCinema(CreateCinemaDTO createCinemaDTO) {

    CineDB cineDB = new CineDB();
    try {
      cineDB.crearCinema(createCinemaDTO.getNombreCine(), createCinemaDTO.getFechaCreacion(),
          createCinemaDTO.getCostoOcultacionAnuncio());
    } catch (Exception e) {
      throw new RuntimeException("Error al crear el cinema: ", e);
    }
  }

  public void actualizarCinema(int idCine, String nombreCine, LocalDate fechaCreacion,
      BigDecimal costoOcultacionAnuncio) {

    try {
      CineDB cineDB = new CineDB();
      cineDB.actualizarCinema(idCine, nombreCine, fechaCreacion, costoOcultacionAnuncio);
    } catch (Exception e) {
      throw new RuntimeException("Error al actualizar el cinema: ", e);
    }
  }

  public ListadoCineDTO obtenerTodosCines() {

    try {
      CineDB cineDB = new CineDB();
      ListadoCineDTO listado = new ListadoCineDTO();
      listado.setCines(cineDB.obtenerTodosCines());
      return listado;
    } catch (Exception e) {
      throw new RuntimeException("Error al obtener los cines: ", e);
    }
  }

}
