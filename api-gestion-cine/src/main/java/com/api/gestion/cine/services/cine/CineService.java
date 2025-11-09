package com.api.gestion.cine.services.cine;

import java.math.BigDecimal;
import java.util.List;

import com.api.gestion.cine.db.cine.CineDB;
import com.api.gestion.cine.dto.cine.CreateCinemaDTO;
import com.api.gestion.cine.dto.cine.ListadoCineDTO;
import com.api.gestion.cine.model.cine.CineModel;

public class CineService {

  public void crearCinema(CreateCinemaDTO createCinemaDTO) {
    // Validaciones
    if (createCinemaDTO.getNombreCine() == null || createCinemaDTO.getNombreCine().isBlank()) {
      throw new IllegalArgumentException("El nombre del cine no puede estar vacío.");
    }
    if (createCinemaDTO.getFechaCreacion() == null) {
      throw new IllegalArgumentException("La fecha de creación no puede estar vacía.");
    }
    if (createCinemaDTO.getCostoOcultacionAnuncio() == null
        || createCinemaDTO.getCostoOcultacionAnuncio().compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalArgumentException("El costo de ocultación del anuncio debe ser un número positivo.");
    }

    CineDB cineDB = new CineDB();
    try {
      cineDB.crearCinema(createCinemaDTO.getNombreCine(), createCinemaDTO.getFechaCreacion(),
          createCinemaDTO.getCostoOcultacionAnuncio());
    } catch (Exception e) {
      throw new RuntimeException("Error al crear el cinema: ", e);
    }
  }

  public void actualizarCinema(int idCine, String nombreCine) {
    // Validaciones
    if (idCine <= 0) {
      throw new IllegalArgumentException("ID del cine inválido.");
    }
    if (nombreCine == null || nombreCine.isBlank()) {
      throw new IllegalArgumentException("El nombre del cine no puede estar vacío.");
    }

    try {
      CineDB cineDB = new CineDB();
      cineDB.actualizarCinema(idCine, nombreCine);
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

  public CineModel obtenerCinePorId(int idCine) {
    if (idCine <= 0) {
      throw new IllegalArgumentException("ID del cine inválido.");
    }
    try {
      CineDB cineDB = new CineDB();
      CineModel cine = cineDB.obtenerCinePorId(idCine);
      if (cine == null) {
        throw new RuntimeException("Cine no encontrado.");
      }
      return cine;
    } catch (Exception e) {
      throw new RuntimeException("Error al obtener el cine por ID: ", e);
    }
  }

  public void desactivarCine(int idCine) {
    if (idCine <= 0) {
      throw new IllegalArgumentException("ID del cine inválido.");
    }
    try {
      CineDB cineDB = new CineDB();
      cineDB.desactivarCine(idCine);
    } catch (Exception e) {
      throw new RuntimeException("Error al desactivar el cine: ", e);
    }
  }

  public List<CineModel> buscarCinesPorNombre(String nombre) {
    if (nombre == null || nombre.isBlank()) {
      throw new IllegalArgumentException("El nombre no puede estar vacío.");
    }
    try {
      CineDB cineDB = new CineDB();
      return cineDB.buscarCinesPorNombre(nombre);
    } catch (Exception e) {
      throw new RuntimeException("Error al buscar cines por nombre: ", e);
    }
  }
}