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
import com.api.gestion.cine.dto.reports.sysadmin.most_commented_room_report.RoomComment;

public class CommentedRoomReportDB {
  public List<RoomComment> getCommentedRooms(LocalDate startDate, LocalDate endDate,
      String nombreSala, int offset, int limit) throws Exception {
    List<RoomComment> roomComments = new ArrayList<>();

    Connection conn = DBConnectionSingleton.getInstance().getConnection();

    String sql = "SELECT s.id_sala, s.nombre_sala, " +
        "u.nombre_completo as nombre_usuario, " +
        "cs.comentario, cs.fecha_publicacion, " +
        "COUNT(*) OVER() as total_comentarios " +
        "FROM sala s " +
        "INNER JOIN comentario_sala cs ON s.id_sala = cs.id_sala " +
        "INNER JOIN usuario u ON cs.id_usuario = u.id_usuario " +
        "WHERE (? IS NULL OR cs.fecha_publicacion >= ?) " +
        "  AND (? IS NULL OR cs.fecha_publicacion <= ?) " +
        "  AND (? IS NULL OR s.nombre_sala LIKE CONCAT('%', ?, '%')) " +
        "ORDER BY cs.fecha_publicacion DESC " +
        "LIMIT ? OFFSET ?";

    try (
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      int paramIndex = 1;

      // Parámetros de fecha inicio
      if (startDate != null) {
        pstmt.setDate(paramIndex++, Date.valueOf(startDate));
        pstmt.setDate(paramIndex++, Date.valueOf(startDate));
      } else {
        pstmt.setNull(paramIndex++, Types.DATE);
        pstmt.setNull(paramIndex++, Types.DATE);
      }

      // * Parámetros de fecha fin
      if (endDate != null) {
        pstmt.setDate(paramIndex++, Date.valueOf(endDate));
        pstmt.setDate(paramIndex++, Date.valueOf(endDate));
      } else {
        pstmt.setNull(paramIndex++, Types.DATE);
        pstmt.setNull(paramIndex++, Types.DATE);
      }

      // *Parámetro de nombre de sala
      if (nombreSala != null && !nombreSala.trim().isEmpty() && !nombreSala.equals("Todo")) {
        pstmt.setString(paramIndex++, nombreSala);
        pstmt.setString(paramIndex++, nombreSala);
      } else {
        pstmt.setNull(paramIndex++, Types.VARCHAR);
        pstmt.setNull(paramIndex++, Types.VARCHAR);
      }

      // * Parámetros de paginación
      pstmt.setInt(paramIndex++, limit);
      pstmt.setInt(paramIndex++, offset);

      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
          RoomComment roomComment = new RoomComment();
          roomComment.setIdSala(rs.getInt("id_sala"));
          roomComment.setNombreSala(rs.getString("nombre_sala"));
          roomComment.setNombreUsuario(rs.getString("nombre_usuario"));
          roomComment.setComentario(rs.getString("comentario"));
          roomComment.setTotalComentarios(rs.getInt("total_comentarios"));

          roomComments.add(roomComment);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return roomComments;
  }

  public int getTotalCommentedRooms(LocalDate startDate, LocalDate endDate, String nombreSala) throws Exception {

    int total = 0;

    Connection conn = DBConnectionSingleton.getInstance().getConnection();
    String sql = "SELECT COUNT(*) as total " +
        "FROM sala s " +
        "INNER JOIN comentario_sala cs ON s.id_sala = cs.id_sala " +
        "INNER JOIN usuario u ON cs.id_usuario = u.id_usuario " +
        "WHERE (? IS NULL OR cs.fecha_publicacion >= ?) " +
        "  AND (? IS NULL OR cs.fecha_publicacion <= ?) " +
        "  AND (? IS NULL OR s.nombre_sala LIKE CONCAT('%', ?, '%'))";

    try (
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      int paramIndex = 1;

      // * Parámetros de fecha inicio
      if (startDate != null) {
        pstmt.setDate(paramIndex++, Date.valueOf(startDate));
        pstmt.setDate(paramIndex++, Date.valueOf(startDate));
      } else {
        pstmt.setNull(paramIndex++, Types.DATE);
        pstmt.setNull(paramIndex++, Types.DATE);
      }

      // * Parámetros de fecha fin
      if (endDate != null) {
        pstmt.setDate(paramIndex++, Date.valueOf(endDate));
        pstmt.setDate(paramIndex++, Date.valueOf(endDate));
      } else {
        pstmt.setNull(paramIndex++, Types.DATE);
        pstmt.setNull(paramIndex++, Types.VARCHAR);
      }

      // * Parámetro de nombre de sala
      if (nombreSala != null && !nombreSala.trim().isEmpty() && !nombreSala.equals("Todo")) {
        pstmt.setString(paramIndex++, nombreSala);
        pstmt.setString(paramIndex++, nombreSala);
      } else {
        pstmt.setNull(paramIndex++, Types.VARCHAR);
        pstmt.setNull(paramIndex++, Types.VARCHAR);
      }

      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          total = rs.getInt("total");
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return total;
  }
}
