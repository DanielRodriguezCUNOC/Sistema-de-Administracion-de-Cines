package com.api.gestion.cine.db.cinema_admin;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.api.gestion.cine.db.connection.DBConnectionSingleton;
import com.api.gestion.cine.dto.reports.cinema_admin.most_liked_room_report.LikedData;
import com.api.gestion.cine.dto.reports.cinema_admin.most_liked_room_report.LikedRoomData;
import com.api.gestion.cine.exceptions.DataBaseException;

public class MostLikedRoomReportDB {

  public List<LikedRoomData> getMostLikedRoom(LocalDate startDate, LocalDate endDate, String nombreSala)
      throws Exception {

    List<LikedRoomData> likedRooms = new ArrayList<>();
    Connection conn = DBConnectionSingleton.getInstance().getConnection();

    StringBuilder sql = new StringBuilder(
        "SELECT s.id_sala, s.nombre_sala, " +
            "COUNT(vs.id_valoracion_sala) AS cantidad_valoraciones, " +
            "ROUND(AVG(vs.valoracion), 1) AS promedio_valoracion " +
            "FROM sala s " +
            "INNER JOIN valoracion_sala vs ON s.id_sala = vs.id_sala " +
            "WHERE 1=1 ");

    if (startDate != null)
      sql.append("AND vs.fecha_valoracion >= ? ");
    if (endDate != null)
      sql.append("AND vs.fecha_valoracion <= ? ");
    if (nombreSala != null && !nombreSala.trim().isEmpty())
      sql.append("AND s.nombre_sala LIKE ? ");

    sql.append("GROUP BY s.id_sala, s.nombre_sala ")
        .append("ORDER BY promedio_valoracion DESC, cantidad_valoraciones DESC ")
        .append("LIMIT 5");

    try (PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

      int paramIndex = 1;
      if (startDate != null)
        pstmt.setDate(paramIndex++, Date.valueOf(startDate));
      if (endDate != null)
        pstmt.setDate(paramIndex++, Date.valueOf(endDate));
      if (nombreSala != null && !nombreSala.trim().isEmpty())
        pstmt.setString(paramIndex++, "%" + nombreSala + "%");

      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
          int idSala = rs.getInt("id_sala");
          String nombreSalaResult = rs.getString("nombre_sala");
          int cantidadValoraciones = rs.getInt("cantidad_valoraciones");
          BigDecimal promedioValoracion = BigDecimal.valueOf(rs.getDouble("promedio_valoracion"));

          List<LikedData> valoraciones = getValoracionesBySala(idSala, startDate, endDate);

          LikedRoomData room = new LikedRoomData();
          room.setIdSala(idSala);
          room.setNombreSala(nombreSalaResult);
          room.setCantidadValoraciones(cantidadValoraciones);
          room.setPromedioValoracion(promedioValoracion);
          room.setValoraciones(valoraciones);

          likedRooms.add(room);
        }
      }
    } catch (SQLException e) {
      throw new DataBaseException("Error al obtener salas más valoradas", e);
    }

    return likedRooms;
  }

  private List<LikedData> getValoracionesBySala(int idSala, LocalDate startDate, LocalDate endDate) throws Exception {

    List<LikedData> valoraciones = new ArrayList<>();

    Connection conn = DBConnectionSingleton.getInstance().getConnection();

    StringBuilder sql = new StringBuilder(
        "SELECT u.nombre_completo, vs.valoracion, vs.fecha_valoracion " +
            "FROM valoracion_sala vs " +
            "INNER JOIN usuario u ON vs.id_usuario = u.id_usuario " +
            "WHERE vs.id_sala = ? ");

    if (startDate != null)
      sql.append("AND vs.fecha_valoracion >= ? ");
    if (endDate != null)
      sql.append("AND vs.fecha_valoracion <= ? ");
    sql.append("ORDER BY vs.fecha_valoracion DESC");

    try (PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

      int paramIndex = 1;
      pstmt.setInt(paramIndex++, idSala);
      if (startDate != null)
        pstmt.setDate(paramIndex++, Date.valueOf(startDate));
      if (endDate != null)
        pstmt.setDate(paramIndex++, Date.valueOf(endDate));

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
      throw new DataBaseException("Error al obtener valoraciones por sala", e);
    }

    return valoraciones;
  }
}
