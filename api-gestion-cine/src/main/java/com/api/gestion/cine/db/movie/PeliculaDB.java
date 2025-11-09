package com.api.gestion.cine.db.movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.api.gestion.cine.db.connection.DBConnectionSingleton;
import com.api.gestion.cine.dto.movie.CreateMovieDTO;
import com.api.gestion.cine.exceptions.DataBaseException;
import com.api.gestion.cine.model.movie.MovieModel;

public class PeliculaDB {

  public boolean crearPelicula(CreateMovieDTO pelicula) {

    return true;
  }

  public boolean isMovieExists(String tituloPelicula) throws Exception {
    Connection conn = DBConnectionSingleton.getInstance().getConnection();

    String sql = "SELECT COUNT(*) FROM pelicula WHERE titulo_pelicula = ?";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setString(1, tituloPelicula);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        int count = rs.getInt(1);
        return count > 0;
      }
    } catch (SQLException e) {
      throw new DataBaseException("Error al verificar la existencia de la película en la base de datos.", e);
    }
    return false;
  }

  public MovieModel getMovieByName(String nombrePelicula) throws Exception {
    Connection conn = DBConnectionSingleton.getInstance().getConnection();
    String sql = "SELECT * FROM pelicula WHERE titulo_pelicula = ?";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setString(1, nombrePelicula);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        MovieModel pelicula = new MovieModel();
        pelicula.setIdPelicula(rs.getInt("id"));
        pelicula.setTituloPelicula(rs.getString("titulo_pelicula"));
        pelicula.setSinopsis(rs.getString("sinopsis"));
        pelicula.setDuracion(rs.getInt("duracion"));
        pelicula.setReparto(rs.getString("reparto"));
        pelicula.setDirector(rs.getString("director"));
        pelicula.setClasificacion(rs.getString("clasificacion"));
        pelicula.setFechaEstreno(rs.getDate("fecha_estreno").toLocalDate());
        pelicula.setPrecioPelicula(rs.getBigDecimal("precio_pelicula"));
        pelicula.setPoster(rs.getBytes("poster"));
        return pelicula;
      }
    } catch (SQLException e) {
      throw new DataBaseException("Error al verificar la existencia de la película en la base de datos.", e);
    }
    return null;
  }

  public List<MovieModel> getMovieByCategory(String categoria) throws Exception {

    List<MovieModel> peliculas = new ArrayList<>();
    Connection conn = DBConnectionSingleton.getInstance().getConnection();
    String sql = "SELECT * FROM pelicula WHERE categoria = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setString(1, categoria);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        MovieModel pelicula = new MovieModel();
        pelicula.setIdPelicula(rs.getInt("id"));
        pelicula.setTituloPelicula(rs.getString("titulo_pelicula"));
        pelicula.setSinopsis(rs.getString("sinopsis"));
        pelicula.setDuracion(rs.getInt("duracion"));
        pelicula.setReparto(rs.getString("reparto"));
        pelicula.setDirector(rs.getString("director"));
        pelicula.setClasificacion(rs.getString("clasificacion"));
        pelicula.setFechaEstreno(rs.getDate("fecha_estreno").toLocalDate());
        pelicula.setPrecioPelicula(rs.getBigDecimal("precio_pelicula"));
        pelicula.setPoster(rs.getBytes("poster"));
        peliculas.add(pelicula);
      }
    } catch (SQLException e) {
      throw new DataBaseException("Error al obtener las películas por categoría.", e);
    }
    return peliculas;
  }

}
