package com.api.gestion.cine.db.sysadmin;

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

public class MostCommentedRoomDB {
  public List<RoomComment> getMostCommentedRooms(LocalDate startDate, LocalDate endDate) throws Exception {

    List<RoomComment> roomComments = new ArrayList<>();

    Connection conn = DBConnectionSingleton.getInstance().getConnection();
    String sql = "WITH SalasMasComentadas AS ( " +
        "    SELECT s.id_sala, s.nombre_sala, " +
        "           COUNT(cs.id_comentario_sala) as total_comentarios " +
        "    FROM sala s " +
        "    INNER JOIN comentario_sala cs ON s.id_sala = cs.id_sala " +
        "    WHERE (? IS NULL OR cs.fecha_publicacion >= ?) " +
        "      AND (? IS NULL OR cs.fecha_publicacion <= ?) " +
        "    GROUP BY s.id_sala, s.nombre_sala " +
        "    ORDER BY total_comentarios DESC " +
        "    LIMIT 5 " +
        ") " +
        "SELECT smc.id_sala, smc.nombre_sala, " +
        "       u.nombre_completo as nombre_usuario, " +
        "       cs.comentario, " +
        "       cs.fecha_publicacion, " +
        "       smc.total_comentarios " +
        "FROM SalasMasComentadas smc " +
        "INNER JOIN comentario_sala cs ON smc.id_sala = cs.id_sala " +
        "INNER JOIN usuario u ON cs.id_usuario = u.id_usuario " +
        "WHERE (? IS NULL OR cs.fecha_publicacion >= ?) " +
        "  AND (? IS NULL OR cs.fecha_publicacion <= ?) " +
        "ORDER BY smc.total_comentarios DESC, cs.fecha_publicacion DESC";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {

      setDateParameters(stmt, startDate, endDate, 1);

      setDateParameters(stmt, startDate, endDate, 5);

      try (ResultSet rs = stmt.executeQuery()) {
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

  private void setDateParameters(PreparedStatement stmt, LocalDate startDate, LocalDate endDate, int startIndex)
      throws SQLException {
    if (startDate != null) {
      stmt.setDate(startIndex, Date.valueOf(startDate));
      stmt.setDate(startIndex + 1, Date.valueOf(startDate));
    } else {
      stmt.setNull(startIndex, Types.DATE);
      stmt.setNull(startIndex + 1, Types.DATE);
    }

    if (endDate != null) {
      stmt.setDate(startIndex + 2, Date.valueOf(endDate));
      stmt.setDate(startIndex + 3, Date.valueOf(endDate));
    } else {
      stmt.setNull(startIndex + 2, Types.DATE);
      stmt.setNull(startIndex + 3, Types.DATE);
    }
  }

}
