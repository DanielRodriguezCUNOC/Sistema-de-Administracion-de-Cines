package com.api.gestion.cine.db.cinema_admin;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.api.gestion.cine.db.connection.DBConnectionSingleton;
import com.api.gestion.cine.dto.reports.cinema_admin.proyected_movies_report.MovieProyectedRoom;
import com.api.gestion.cine.exceptions.DataBaseException;

public class ProyectedMovieReportDB {

  public List<MovieProyectedRoom> getProyectedMovies(LocalDate startDate, LocalDate endDate,
      int offset, int limit, String nombreSala) throws Exception {

    List<MovieProyectedRoom> proyectedRooms = new ArrayList<>();

    StringBuilder sql = new StringBuilder(
        "SELECT s.id_sala, s.nombre_sala, " +
            "GROUP_CONCAT(DISTINCT p.titulo ORDER BY pp.fecha_proyeccion DESC SEPARATOR ', ') AS titulos_peliculas, " +
            "COUNT(DISTINCT pp.id_pelicula) AS cantidad_peliculas_proyectadas " +
            "FROM sala s " +
            "INNER JOIN pelicula_proyectada pp ON s.id_sala = pp.id_sala " +
            "INNER JOIN pelicula p ON pp.id_pelicula = p.id_pelicula " +
            "WHERE 1=1 ");

    if (startDate != null) {
      sql.append("AND pp.fecha_proyeccion >= ? ");
    }
    if (endDate != null) {
      sql.append("AND pp.fecha_proyeccion <= ? ");
    }
    if (nombreSala != null && !nombreSala.trim().isEmpty()) {
      sql.append("AND s.nombre_sala LIKE ? ");
    }

    sql.append("GROUP BY s.id_sala, s.nombre_sala ")
        .append("ORDER BY cantidad_peliculas_proyectadas DESC, s.nombre_sala ")
        .append("LIMIT ? OFFSET ?");

    try (Connection conn = DBConnectionSingleton.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

      int paramIndex = 1;

      if (startDate != null) {
        stmt.setDate(paramIndex++, Date.valueOf(startDate));
      }
      if (endDate != null) {
        stmt.setDate(paramIndex++, Date.valueOf(endDate));
      }
      if (nombreSala != null && !nombreSala.trim().isEmpty()) {
        stmt.setString(paramIndex++, "%" + nombreSala.trim() + "%");
      }

      stmt.setInt(paramIndex++, limit);
      stmt.setInt(paramIndex++, offset);

      try (ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
          MovieProyectedRoom room = new MovieProyectedRoom();
          room.setIdSala(rs.getInt("id_sala"));
          room.setNombreSala(rs.getString("nombre_sala"));

          String titulosConcat = rs.getString("titulos_peliculas");
          if (titulosConcat != null && !titulosConcat.isEmpty()) {
            String[] titulosArray = titulosConcat.split("\\s*,\\s*");
            room.setTitulosPeliculas(titulosArray);
          } else {
            room.setTitulosPeliculas(new String[0]);
          }

          room.setCantidadPeliculasProyectadas(rs.getInt("cantidad_peliculas_proyectadas"));
          proyectedRooms.add(room);
        }
      }

    } catch (SQLException e) {
      throw new DataBaseException("Error al obtener las películas proyectadas desde la base de datos", e);
    }

    return proyectedRooms;
  }
}
