package com.api.gestion.cine.db.sysadmin;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.api.gestion.cine.db.connection.DBConnectionSingleton;
import com.api.gestion.cine.dto.reports.sysadmin.profit_report.AdvertisementProfitReport;
import com.api.gestion.cine.dto.reports.sysadmin.profit_report.CinemaCostReport;
import com.api.gestion.cine.exceptions.DataBaseException;

public class ProfitReportDB {

  public List<CinemaCostReport> getCinemaCosts(LocalDate fechaInicio, LocalDate fechaFin)
      throws Exception {

    List<CinemaCostReport> cinemaCosts = new ArrayList<>();

    Connection conn = DBConnectionSingleton.getInstance().getConnection();
    String sql = "SELECT c.id_cine, c.nombre_cine, " +
        "GROUP_CONCAT(DISTINCT cc.fecha_modificacion ORDER BY cc.fecha_modificacion) as fechas_modificacion, " +
        "GROUP_CONCAT(cc.costo_cine ORDER BY cc.fecha_modificacion) as costos " +
        "FROM cine c " +
        "INNER JOIN costo_cine cc ON c.id_cine = cc.id_cine " +
        "WHERE cc.fecha_modificacion BETWEEN ? AND ? " +
        "GROUP BY c.id_cine, c.nombre_cine " +
        "ORDER BY c.id_cine";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setDate(1, Date.valueOf(fechaInicio));
      pstmt.setDate(2, Date.valueOf(fechaFin));

      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
          int idCine = rs.getInt("id_cine");
          String nombreCine = rs.getString("nombre_cine");

          // Procesar fechas
          String fechasStr = rs.getString("fechas_modificacion");
          List<LocalDate> fechas = new ArrayList<>();
          if (fechasStr != null) {
            String[] fechaArray = fechasStr.split(",");
            for (String fecha : fechaArray) {
              fechas.add(LocalDate.parse(fecha.trim()));
            }
          }

          // Procesar costos
          String costosStr = rs.getString("costos");
          List<BigDecimal> costosList = new ArrayList<>();
          if (costosStr != null) {
            String[] costoArray = costosStr.split(",");
            for (String costo : costoArray) {
              costosList.add(new BigDecimal(costo.trim()));
            }
          }

          BigDecimal[] costosArray = costosList.toArray(new BigDecimal[0]);
          CinemaCostReport report = new CinemaCostReport(idCine, nombreCine, fechas, costosArray);
          cinemaCosts.add(report);
        }

      } catch (SQLException e) {
        throw new DataBaseException("Error al procesar los resultados de la consulta de costos de cines.", e);
      }
      return cinemaCosts;
    }
  }

  public List<AdvertisementProfitReport> getAdvertisementPayments(LocalDate fechaInicio, LocalDate fechaFin)
      throws Exception {

    List<AdvertisementProfitReport> reports = new ArrayList<>();

    Connection conn = DBConnectionSingleton.getInstance().getConnection();
    String sql = "SELECT pa.id_pago_anuncio as id, a.nombre_anuncio as nombre, " +
        "pa.fecha_pago, pa.monto_pago as montoPago " +
        "FROM pago_anuncio pa " +
        "INNER JOIN anuncio a ON pa.id_anuncio = a.id_anuncio " +
        "WHERE pa.fecha_pago BETWEEN ? AND ? " +
        "ORDER BY pa.fecha_pago";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setDate(1, Date.valueOf(fechaInicio));
      pstmt.setDate(2, Date.valueOf(fechaFin));

      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
          AdvertisementProfitReport report = new AdvertisementProfitReport();
          report.setId(rs.getInt("id"));
          report.setNombre(rs.getString("nombre"));
          report.setFechaPago(rs.getDate("fecha_pago").toLocalDate());
          report.setMontoPago(rs.getInt("amount"));
          reports.add(report);
        }
      }
    } catch (SQLException e) {
      throw new DataBaseException("Error al obtener los pagos de anuncios desde la base de datos.", e);
    }
    return reports;
  }

  public List<AdvertisementProfitReport> getAdBlockPayments(LocalDate fechaInicio, LocalDate fechaFin)
      throws Exception {

    List<AdvertisementProfitReport> reports = new ArrayList<>();

    Connection conn = DBConnectionSingleton.getInstance().getConnection();
    String sql = "SELECT poa.id_pago_ocultacion as id, u.nombre_completo as nombre, " +
        "poa.fecha_pago, poa.monto as amount " +
        "FROM pago_ocultacion_anuncios poa " +
        "INNER JOIN usuario u ON poa.id_usuario = u.id_usuario " +
        "WHERE poa.fecha_pago BETWEEN ? AND ? " +
        "ORDER BY poa.fecha_pago";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setDate(1, Date.valueOf(fechaInicio));
      pstmt.setDate(2, Date.valueOf(fechaFin));

      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
          AdvertisementProfitReport report = new AdvertisementProfitReport();
          report.setId(rs.getInt("id"));
          report.setNombre(rs.getString("nombre"));
          report.setFechaPago(rs.getDate("fecha_pago").toLocalDate());
          report.setMontoPago(rs.getInt("amount"));
          reports.add(report);
        }
      }
    } catch (SQLException e) {
      throw new DataBaseException("Error al obtener los pagos de bloques de anuncios desde la base de datos.", e);
    }
    return reports;
  }
}
