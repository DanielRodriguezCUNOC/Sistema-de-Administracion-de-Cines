package com.api.gestion.cine.db.movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.api.gestion.cine.db.connection.DBConnectionSingleton;
import com.api.gestion.cine.dto.movie.CreateMovieDTO;
import com.api.gestion.cine.dto.movie.ListMovieDTO;
import com.api.gestion.cine.exceptions.DataBaseException;
import com.api.gestion.cine.model.movie.MovieModel;

public class PeliculaDB {

  public boolean crearPelicula(CreateMovieDTO pelicula) throws Exception {
    Connection conn = DBConnectionSingleton.getInstance().getConnection();
    String sql = "INSERT INTO pelicula (id_usuario, titulo_pelicula, sinopsis, duracion, reparto, director, clasificacion, fecha_estreno, precio_pelicula, poster) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    System.out.println("ID usuario: " + pelicula.getIdUsuario());

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, pelicula.getIdUsuario());
      pstmt.setString(2, pelicula.getTituloPelicula());
      pstmt.setString(3, pelicula.getSinopsis());
      pstmt.setInt(4, pelicula.getDuracion());
      pstmt.setString(5, pelicula.getReparto());
      pstmt.setString(6, pelicula.getDirector());
      pstmt.setString(7, pelicula.getClasificacion());
      pstmt.setDate(8, java.sql.Date.valueOf(pelicula.getFechaEstreno()));
      pstmt.setBigDecimal(9, pelicula.getPrecioPelicula());
      pstmt.setBytes(10, pelicula.getPoster());

      int rowsInserted = pstmt.executeUpdate();
      return rowsInserted > 0;
    } catch (SQLException e) {
      throw new DataBaseException("Error al crear la película en la base de datos.", e);
    }
  }

  public boolean isMovieExists(String tituloPelicula) throws Exception {
    Connection conn = DBConnectionSingleton.getInstance().getConnection();

    String sql = "SELECT COUNT(*) FROM pelicula WHERE titulo_pelicula = ?";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, tituloPelicula);
      ResultSet rs = pstmt.executeQuery();
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

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, nombrePelicula);
      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          MovieModel pelicula = new MovieModel();
          pelicula.setIdPelicula(rs.getInt("id_pelicula"));
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
      }
    } catch (SQLException e) {
      throw new DataBaseException("Error al obtener la película por título.", e);
    }
    return null;
  }

  public List<MovieModel> getMovieByCategory(String categoria) throws Exception {

    List<MovieModel> peliculas = new ArrayList<>();
    Connection conn = DBConnectionSingleton.getInstance().getConnection();
    String sql = "SELECT p.* FROM pelicula p " +
        "JOIN categoria_pelicula cp ON p.id_pelicula = cp.id_pelicula " +
        "JOIN categoria c ON cp.id_categoria = c.id_categoria " +
        "WHERE c.categoria = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, categoria);
      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
          MovieModel pelicula = new MovieModel();
          pelicula.setIdPelicula(rs.getInt("id_pelicula"));
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
      }
    } catch (SQLException e) {
      throw new DataBaseException("Error al obtener las películas por categoría.", e);
    }
    return peliculas;
  }

  public List<ListMovieDTO> obtenerTodasLasPeliculas() throws Exception {
    List<ListMovieDTO> peliculas = new ArrayList<>();
    Connection conn = DBConnectionSingleton.getInstance().getConnection();
    String sql = "SELECT * FROM pelicula";
    try (PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery()) {
      while (rs.next()) {
        ListMovieDTO pelicula = new ListMovieDTO();
        pelicula.setIdPelicula(rs.getInt("id_pelicula"));
        pelicula.setIdUsuario(rs.getInt("id_usuario"));
        pelicula.setTituloPelicula(rs.getString("titulo_pelicula"));
        pelicula.setSinopsis(rs.getString("sinopsis"));
        pelicula.setDuracion(rs.getInt("duracion"));
        pelicula.setReparto(rs.getString("reparto"));
        pelicula.setDirector(rs.getString("director"));
        pelicula.setClasificacion(rs.getString("clasificacion"));
        pelicula.setFechaEstreno(rs.getDate("fecha_estreno").toLocalDate());
        pelicula.setPrecioPelicula(rs.getBigDecimal("precio_pelicula"));
        peliculas.add(pelicula);
      }
    } catch (SQLException e) {
      throw new DataBaseException("Error al obtener todas las películas de la base de datos.", e);
    }
    return peliculas;
  }

  public void eliminarPelicula(int idPelicula) throws Exception {
    Connection conn = DBConnectionSingleton.getInstance().getConnection();
    String sql = "UPDATE pelicula SET estado = 0 WHERE id_pelicula = ?";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, idPelicula);

      int rowsDeleted = pstmt.executeUpdate();
      if (rowsDeleted == 0) {
        throw new DataBaseException("No se encontró la película con el ID proporcionado.");
      }
    } catch (SQLException e) {
      throw new DataBaseException("Error al eliminar la película de la base de datos.", e);
    }
  }

}
