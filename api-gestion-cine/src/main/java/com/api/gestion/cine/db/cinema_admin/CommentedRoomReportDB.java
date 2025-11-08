package com.api.gestion.cine.db.cinema_admin;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.api.gestion.cine.db.connection.DBConnectionSingleton;
import com.api.gestion.cine.dto.reports.sysadmin.most_commented_room_report.RoomComment;
import com.api.gestion.cine.exceptions.DataBaseException;

public class CommentedRoomReportDB {

  public List<RoomComment> getCommentedRooms(LocalDate startDate, LocalDate endDate,
      String nombreSala, int offset, int limit) throws Exception {

    List<RoomComment> roomComments = new ArrayList<>();

    StringBuilder sql = new StringBuilder(
        "SELECT s.id_sala, s.nombre_sala, u.nombre_completo AS nombre_usuario, " +
            "cs.comentario, cs.fecha_publicacion, " +
            "(SELECT COUNT(*) FROM comentario_sala cs2 WHERE cs2.id_sala = s.id_sala) AS total_comentarios " +
            "FROM sala s " +
            "INNER JOIN comentario_sala cs ON s.id_sala = cs.id_sala " +
            "INNER JOIN usuario u ON cs.id_usuario = u.id_usuario " +
            "WHERE 1=1 ");

    if (startDate != null)
      sql.append("AND cs.fecha_publicacion >= ? ");
    if (endDate != null)
      sql.append("AND cs.fecha_publicacion <= ? ");
    if (nombreSala != null && !nombreSala.trim().isEmpty() && !nombreSala.equalsIgnoreCase("Todo")) {
      sql.append("AND s.nombre_sala LIKE ? ");
    }

    sql.append("ORDER BY cs.fecha_publicacion DESC LIMIT ? OFFSET ?");

    try (Connection conn = DBConnectionSingleton.getInstance().getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

      int paramIndex = 1;

      if (startDate != null)
        pstmt.setDate(paramIndex++, Date.valueOf(startDate));
      if (endDate != null)
        pstmt.setDate(paramIndex++, Date.valueOf(endDate));
      if (nombreSala != null && !nombreSala.trim().isEmpty() && !nombreSala.equalsIgnoreCase("Todo")) {
        pstmt.setString(paramIndex++, "%" + nombreSala.trim() + "%");
      }

      pstmt.setInt(paramIndex++, limit);
      pstmt.setInt(paramIndex++, offset);

      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
          RoomComment roomComment = new RoomComment();
          roomComment.setIdSala(rs.getInt("id_sala"));
          roomComment.setNombreSala(rs.getString("nombre_sala"));
          roomComment.setNombreUsuario(rs.getString("nombre_usuario"));
          roomComment.setComentario(rs.getString("comentario"));
          roomComment.setFechaPublicacion(rs.getDate("fecha_publicacion").toLocalDate());
          roomComment.setTotalComentarios(rs.getInt("total_comentarios"));
          roomComments.add(roomComment);
        }
      }
    } catch (SQLException e) {
      throw new DataBaseException("Error al obtener salas comentadas", e);
    }

    return roomComments;
  }

  public int getTotalCommentedRooms(LocalDate startDate, LocalDate endDate, String nombreSala) throws Exception {
    int total = 0;

    StringBuilder sql = new StringBuilder(
        "SELECT COUNT(DISTINCT s.id_sala) AS total " +
            "FROM sala s " +
            "INNER JOIN comentario_sala cs ON s.id_sala = cs.id_sala " +
            "INNER JOIN usuario u ON cs.id_usuario = u.id_usuario " +
            "WHERE 1=1 ");

    if (startDate != null)
      sql.append("AND cs.fecha_publicacion >= ? ");
    if (endDate != null)
      sql.append("AND cs.fecha_publicacion <= ? ");
    if (nombreSala != null && !nombreSala.trim().isEmpty() && !nombreSala.equalsIgnoreCase("Todo")) {
      sql.append("AND s.nombre_sala LIKE ? ");
    }

    try (Connection conn = DBConnectionSingleton.getInstance().getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

      int paramIndex = 1;

      if (startDate != null)
        pstmt.setDate(paramIndex++, Date.valueOf(startDate));
      if (endDate != null)
        pstmt.setDate(paramIndex++, Date.valueOf(endDate));
      if (nombreSala != null && !nombreSala.trim().isEmpty()) {
        pstmt.setString(paramIndex++, "%" + nombreSala.trim() + "%");
      }

      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          total = rs.getInt("total");
        }
      }
    } catch (SQLException e) {
      throw new DataBaseException("Error al obtener total de salas comentadas", e);
    }

    return total;
  }
}
