package com.api.gestion.cine.db.sysadmin;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.api.gestion.cine.db.connection.DBConnectionSingleton;
import com.api.gestion.cine.dto.reports.sysadmin.most_popular_room_report.RoomData;
import com.api.gestion.cine.exceptions.DataBaseException;

public class MostPopularRoomReportDB {

  public List<RoomData> getMostPopularRooms(LocalDate fechaInicio, LocalDate fechaFin) throws Exception {

    List<RoomData> popularRooms = new ArrayList<>();

    Connection conn = DBConnectionSingleton.getInstance().getConnection();

    String sql = "SELECT s.id_sala, s.nombre_sala, " +
        "u.nombre_completo as nombre_usuario, " +
        "SUM(pb.cantidad_boleto) as total_boletos_vendidos " +
        "FROM sala s " +
        "INNER JOIN pago_boleto pb ON s.id_sala = pb.id_sala " +
        "INNER JOIN usuario u ON pb.id_usuario = u.id_usuario " +
        "WHERE pb.fecha_pago BETWEEN ? AND ? " +
        "GROUP BY s.id_sala, s.nombre_sala, u.nombre_completo " +
        "ORDER BY total_boletos_vendidos DESC " +
        "LIMIT 5";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setDate(1, Date.valueOf(fechaInicio));
      pstmt.setDate(2, Date.valueOf(fechaFin));

      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
          RoomData room = new RoomData();
          room.setIdSala(rs.getInt("id_sala"));
          room.setNombreSala(rs.getString("nombre_sala"));
          room.setNombreUsuario(rs.getString("nombre_usuario"));
          room.setTotalBoletosVendidos(rs.getInt("total_boletos_vendidos"));

          popularRooms.add(room);
        }
      }

    } catch (SQLException e) {
      throw new DataBaseException("Error al obtener las salas más populares", e);
    }
    return popularRooms;
  }
}
