package com.api.gestion.cine.services.movie;

import java.math.BigDecimal;
import java.util.List;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;

import com.api.gestion.cine.db.categoria.CategoriaDB;
import com.api.gestion.cine.db.movie.PeliculaDB;
import com.api.gestion.cine.dto.movie.CreateMovieDTO;
import com.api.gestion.cine.exceptions.DataBaseException;
import com.api.gestion.cine.exceptions.ImageFormatException;

import com.api.gestion.cine.services.util.ImageConverter;
import com.api.gestion.cine.services.util.ValidatorCustom;

public class PeliculaService {

  public void crearPelicula(int idUsuario, String tituloPelicula, String sinopsis, int duracion,
      String reparto, String director, String clasificacion, String fechaEstreno,
      BigDecimal precioPelicula, FormDataBodyPart posterPart, List<String> categorias)
      throws ImageFormatException, DataBaseException {

    CreateMovieDTO nuevaPelicula = new CreateMovieDTO();
    nuevaPelicula.setIdUsuario(idUsuario);
    nuevaPelicula.setTituloPelicula(tituloPelicula);
    nuevaPelicula.setSinopsis(sinopsis);
    nuevaPelicula.setDuracion(duracion);
    nuevaPelicula.setReparto(reparto);
    nuevaPelicula.setDirector(director);
    nuevaPelicula.setClasificacion(clasificacion);
    nuevaPelicula.setFechaEstreno(ValidatorCustom.parseStringToDate(fechaEstreno));
    nuevaPelicula.setPrecioPelicula(precioPelicula);
    nuevaPelicula.setPoster(procesarPoster(posterPart));
    nuevaPelicula.setCategorias(categorias);

    PeliculaDB peliculaDB = new PeliculaDB();
    try {
      if (peliculaDB.isMovieExists(tituloPelicula)) {
        throw new IllegalArgumentException("La película con el título proporcionado ya existe.");
      }
      if (!peliculaDB.crearPelicula(nuevaPelicula)) {
        throw new DataBaseException("Error al crear la película en la base de datos.");
      }

      for (String categoria : categorias) {
        CategoriaDB categoriaDB = new CategoriaDB();
        List<Integer> categoryIds = categoriaDB.getCategoryIdsByNames(List.of(categoria));
        for (Integer idCategoria : categoryIds) {
          categoriaDB.insertarPeliculaPorCategoria(peliculaDB.getMovieByName(tituloPelicula).getIdPelicula(),
              idCategoria);
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
      throw new DataBaseException("Error al crear la película: ", e);
    }

  }

  private byte[] procesarPoster(FormDataBodyPart posterPart) throws ImageFormatException {
    if (posterPart == null) {
      return null;
    }
    ImageConverter imageConverter = new ImageConverter();
    try {
      byte[] processedImage = imageConverter.processImage(posterPart);
      return processedImage;
    } catch (Exception e) {
      throw new ImageFormatException("Error al procesar el poster de la película.");
    }
  }

}
