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
import com.api.gestion.cine.dto.reports.cinema_admin.most_liked_room_report.LikedData;
import com.api.gestion.cine.dto.reports.cinema_admin.most_liked_room_report.LikedRoomData;

public class MostLikedRoomReportDB {

  public List<LikedRoomData> getMostLikedRoom(LocalDate startDate, LocalDate endDate, String nombreSala)
      throws Exception {

    List<LikedRoomData> likedRooms = new ArrayList<>();
    Connection conn = DBConnectionSingleton.getInstance().getConnection();

    // * Obtenemos las 5 primeras salas más valoradas
    String sql = "SELECT s.id_sala, s.nombre_sala, " +
        "COUNT(vs.id_valoracion_sala) as cantidad_valoraciones, " +
        "ROUND(AVG(vs.valoracion)) as promedio_valoracion " +
        "FROM sala s " +
        "INNER JOIN valoracion_sala vs ON s.id_sala = vs.id_sala " +
        "WHERE (? IS NULL OR vs.fecha_valoracion >= ?) " +
        "  AND (? IS NULL OR vs.fecha_valoracion <= ?) " +
        "  AND (? IS NULL OR s.nombre_sala LIKE CONCAT('%', ?, '%')) " +
        "GROUP BY s.id_sala, s.nombre_sala " +
        "ORDER BY promedio_valoracion DESC, cantidad_valoraciones DESC " +
        "LIMIT 5";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      int paramIndex = 1;

      if (startDate != null) {
        pstmt.setDate(paramIndex++, Date.valueOf(startDate));
        pstmt.setDate(paramIndex++, Date.valueOf(startDate));
      } else {
        pstmt.setNull(paramIndex++, Types.DATE);
        pstmt.setNull(paramIndex++, Types.DATE);

      }

      if (endDate != null) {
        pstmt.setDate(paramIndex++, Date.valueOf(endDate));
        pstmt.setDate(paramIndex++, Date.valueOf(endDate));
      } else {
        pstmt.setNull(paramIndex++, Types.DATE);
        pstmt.setNull(paramIndex++, Types.DATE);
      }

      if (nombreSala != null) {
        pstmt.setString(paramIndex++, nombreSala);
        pstmt.setString(paramIndex++, nombreSala);
      } else {
        pstmt.setNull(paramIndex++, Types.VARCHAR);
        pstmt.setNull(paramIndex++, Types.VARCHAR);
      }

      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
          int idSala = rs.getInt("id_sala");
          String nombreSalaResult = rs.getString("nombre_sala");
          int cantidadValoraciones = rs.getInt("cantidad_valoraciones");
          int promedioValoracion = rs.getInt("promedio_valoracion");

          // * Para cada sala, obtener sus valoraciones detalladas
          List<LikedData> valoraciones = getValoracionesBySala(idSala, startDate, endDate);

          LikedRoomData room = new LikedRoomData();
          room.setIdSala(idSala);
          room.setNombreSala(nombreSalaResult);
          room.setCantidadValoraciones(cantidadValoraciones);
          room.setPromedioValoracion(promedioValoracion);
          room.setValoraciones(valoraciones.toArray(new LikedData[0]));

          likedRooms.add(room);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return likedRooms;
  }

  private List<LikedData> getValoracionesBySala(int idSala, LocalDate startDate, LocalDate endDate) throws Exception {
    List<LikedData> valoraciones = new ArrayList<>();
    Connection conn = DBConnectionSingleton.getInstance().getConnection();

    // * Obtener valoraciones por sala
    String sql = "SELECT u.nombre_completo, vs.valoracion, vs.fecha_valoracion " +
        "FROM valoracion_sala vs " +
        "INNER JOIN usuario u ON vs.id_usuario = u.id_usuario " +
        "WHERE vs.id_sala = ? " +
        "AND (? IS NULL OR vs.fecha_valoracion >= ?) " +
        "AND (? IS NULL OR vs.fecha_valoracion <= ?) " +
        "ORDER BY vs.fecha_valoracion DESC";

    try (
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setInt(1, idSala);

      int paramIndex = 2;

      if (startDate != null) {
        pstmt.setDate(paramIndex++, Date.valueOf(startDate));
        pstmt.setDate(paramIndex++, Date.valueOf(startDate));
      } else {
        pstmt.setNull(paramIndex++, Types.DATE);
        pstmt.setNull(paramIndex++, Types.DATE);
      }
      if (endDate != null) {
        pstmt.setDate(paramIndex++, Date.valueOf(endDate));
        pstmt.setDate(paramIndex++, Date.valueOf(endDate));
      } else {
        pstmt.setNull(paramIndex++, Types.DATE);
        pstmt.setNull(paramIndex++, Types.DATE);
      }

      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
          LikedData likedData = new LikedData();
          likedData.setNombreUsuario(rs.getString("nombre_completo"));
          likedData.setValoracion(rs.getInt("valoracion"));
          likedData.setFechaValoracion(rs.getDate("fecha_valoracion").toLocalDate());

          valoraciones.add(likedData);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return valoraciones;
  }

}
