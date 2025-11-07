package com.api.gestion.cine.db.cinema_dmin;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.api.gestion.cine.db.connection.DBConnectionSingleton;
import com.api.gestion.cine.dto.reports.cinema_admin.sold_ticket_report.SoldTicketData;
import com.api.gestion.cine.dto.reports.cinema_admin.sold_ticket_report.UserData;
import com.api.gestion.cine.exceptions.DataBaseException;

public class SoldTicketReportDB {

  public List<SoldTicketData> getSoldTicket(LocalDate startDate, LocalDate endDate, String nombreSala)
      throws Exception, DataBaseException {

    List<SoldTicketData> listaSalas = new ArrayList<>();
    Connection conn = DBConnectionSingleton.getInstance().getConnection();

    // * Construcción dinámica de la consulta
    StringBuilder sql = new StringBuilder(
        "SELECT s.id_sala, s.nombre_sala, " +
            "u.id_usuario, u.nombre_completo, " +
            "p.id_pago_boleto, p.cantidad_boleto, p.monto_pago, p.fecha_pago " +
            "FROM pago_boleto p " +
            "INNER JOIN sala s ON p.id_sala = s.id_sala " +
            "INNER JOIN usuario u ON p.id_usuario = u.id_usuario " +
            "WHERE 1=1 ");

    // * Agregar filtros según los parámetros proporcionados
    if (startDate != null && endDate != null) {
      sql.append("AND p.fecha_pago BETWEEN ? AND ? ");
    }
    if (nombreSala != null && !nombreSala.isEmpty()) {
      sql.append("AND s.nombre_sala = ? ");
    }

    sql.append("ORDER BY s.id_sala, u.id_usuario");

    try (PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

      int index = 1;
      if (startDate != null && endDate != null) {
        stmt.setDate(index++, Date.valueOf(startDate));
        stmt.setDate(index++, Date.valueOf(endDate));
      }
      if (nombreSala != null) {
        stmt.setString(index++, nombreSala);
      }

      try (ResultSet rs = stmt.executeQuery()) {
        int salaActual = -1;
        SoldTicketData salaData = null;
        List<UserData> usuarios = null;

        while (rs.next()) {
          int idSala = rs.getInt("id_sala");

          // * Si cambia de sala, creamos un nuevo grupo
          if (idSala != salaActual) {
            if (salaData != null) {
              salaData.setUsuarios(usuarios.toArray(new UserData[0]));
              listaSalas.add(salaData);
            }

            salaActual = idSala;
            salaData = new SoldTicketData();
            usuarios = new ArrayList<>();

            salaData.setIdBoleto(rs.getInt("id_pago_boleto"));
            salaData.setNombreSala(rs.getString("nombre_sala"));
          }

          // * Crear usuario asociado
          UserData user = new UserData(
              rs.getInt("id_usuario"),
              rs.getString("nombre_completo"),
              rs.getInt("cantidad_boleto"),
              rs.getBigDecimal("monto_pago"),
              rs.getDate("fecha_pago").toLocalDate());

          usuarios.add(user);
        }

        // * Agregar el último grupo
        if (salaData != null) {
          salaData.setUsuarios(usuarios.toArray(new UserData[0]));
          listaSalas.add(salaData);
        }
      }
    }

    return listaSalas;
  }

}
