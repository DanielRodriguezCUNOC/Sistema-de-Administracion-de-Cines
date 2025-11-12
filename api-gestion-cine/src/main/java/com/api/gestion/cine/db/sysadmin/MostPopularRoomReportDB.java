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

    // 🔹 Construcción dinámica del SQL
    StringBuilder sql = new StringBuilder(
        "SELECT s.id_sala, s.nombre_sala, " +
            "u.nombre_completo AS nombre_usuario, " +
            "SUM(pb.cantidad_boleto) AS total_boletos_vendidos " +
            "FROM sala s " +
            "INNER JOIN pago_boleto pb ON s.id_sala = pb.id_sala " +
            "INNER JOIN usuario u ON pb.id_usuario = u.id_usuario ");

    // Solo agregamos filtro si ambas fechas están presentes
    if (fechaInicio != null && fechaFin != null) {
      sql.append("WHERE pb.fecha_pago BETWEEN ? AND ? ");
    }

    sql.append("GROUP BY s.id_sala, s.nombre_sala, u.nombre_completo ")
        .append("ORDER BY total_boletos_vendidos DESC ")
        .append("LIMIT 5");

    try (PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
      // Si hay fechas válidas, las pasamos como parámetros
      if (fechaInicio != null && fechaFin != null) {
        pstmt.setDate(1, Date.valueOf(fechaInicio));
        pstmt.setDate(2, Date.valueOf(fechaFin));
      }

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
      throw new DataBaseException("Error al obtener las salas más populares.", e);
    }

    return popularRooms;
  }

}
