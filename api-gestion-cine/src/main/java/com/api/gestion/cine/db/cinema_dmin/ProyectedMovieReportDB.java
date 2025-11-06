package com.api.gestion.cine.db.cinema_dmin;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.api.gestion.cine.db.connection.DBConnectionSingleton;
import com.api.gestion.cine.dto.reports.cinema_admin.proyected_movies_report.MovieProyectedRoom;
import com.api.gestion.cine.exceptions.DataBaseException;

public class ProyectedMovieReportDB {

  public List<MovieProyectedRoom> getProyectedMovies(LocalDate startDate, LocalDate endDate, int offset, int limit,
      String nombreSala) throws Exception {

    List<MovieProyectedRoom> proyectedRooms = new ArrayList<>();

    Connection conn = DBConnectionSingleton.getInstance().getConnection();

    String sql = "SELECT s.id_sala, s.nombre_sala, " +
        "GROUP_CONCAT(DISTINCT p.titulo ORDER BY pp.fecha_proyeccion DESC) as titulos_peliculas, " +
        "COUNT(DISTINCT pp.id_pelicula) as cantidad_peliculas_proyectadas " +
        "FROM sala s " +
        "INNER JOIN pelicula_proyectada pp ON s.id_sala = pp.id_sala " +
        "INNER JOIN pelicula p ON pp.id_pelicula = p.id_pelicula " +
        "WHERE (? IS NULL OR pp.fecha_proyeccion >= ?) " +
        "  AND (? IS NULL OR pp.fecha_proyeccion <= ?) " +
        "  AND (? IS NULL OR s.nombre_sala LIKE CONCAT('%', ?, '%')) " +
        "GROUP BY s.id_sala, s.nombre_sala " +
        "ORDER BY cantidad_peliculas_proyectadas DESC, s.nombre_sala " +
        "LIMIT ? OFFSET ?";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {

      int paramIndex = 1;

      // * Parámetros de fecha inicio
      if (startDate != null) {
        stmt.setDate(paramIndex++, Date.valueOf(startDate));
        stmt.setDate(paramIndex++, Date.valueOf(startDate));
      } else {
        stmt.setNull(paramIndex++, Types.DATE);
        stmt.setNull(paramIndex++, Types.DATE);
      }

      // * Parámetros de fecha fin
      if (endDate != null) {
        stmt.setDate(paramIndex++, Date.valueOf(endDate));
        stmt.setDate(paramIndex++, Date.valueOf(endDate));
      } else {
        stmt.setNull(paramIndex++, Types.DATE);
        stmt.setNull(paramIndex++, Types.DATE);
      }

      // * Parámetro de nombre de sala
      if (nombreSala != null && !nombreSala.trim().isEmpty() && !nombreSala.equals("Todo")) {
        stmt.setString(paramIndex++, nombreSala);
        stmt.setString(paramIndex++, nombreSala);
      } else {
        stmt.setNull(paramIndex++, Types.VARCHAR);
        stmt.setNull(paramIndex++, Types.VARCHAR);
      }

      // * Parámetros que servirán para limitar los resultados
      stmt.setInt(paramIndex++, limit);
      stmt.setInt(paramIndex++, offset);

      try (ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
          MovieProyectedRoom room = new MovieProyectedRoom();
          room.setIdSala(rs.getInt("id_sala"));
          room.setNombreSala(rs.getString("nombre_sala"));

          // * Procesar los títulos de películas desde GROUP_CONCAT
          String titulosStr = rs.getString("titulos_peliculas");
          if (titulosStr != null) {
            String[] titulosArray = titulosStr.split(",");
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
