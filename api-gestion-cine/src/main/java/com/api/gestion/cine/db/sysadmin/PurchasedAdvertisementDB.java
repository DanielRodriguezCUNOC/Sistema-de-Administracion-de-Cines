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
import com.api.gestion.cine.dto.reports.sysadmin.purchased_advertisement_report.PurchasedAdvertisement;
import com.api.gestion.cine.exceptions.DataBaseException;

public class PurchasedAdvertisementDB {

  public List<PurchasedAdvertisement> getPurchasedAdvertisements(LocalDate fechaInicio, LocalDate fechaFin,
      String tipoAnuncio, int offset, int limit) throws Exception {

    List<PurchasedAdvertisement> advertisements = new ArrayList<>();

    Connection conn = DBConnectionSingleton.getInstance().getConnection();

    String sql = "SELECT a.id_anuncio, a.nombre_anuncio, ta.tipo_anuncio, " +
        "pa.fecha_pago, pa.monto_pago " +
        "FROM anuncio a " +
        "INNER JOIN pago_anuncio pa ON a.id_anuncio = pa.id_anuncio " +
        "INNER JOIN configuracion_anuncio ca ON a.id_configuracion_anuncio = ca.id_configuracion_anuncio " +
        "INNER JOIN tipo_anuncio ta ON ca.id_tipo_anuncio = ta.id_tipo_anuncio " +
        "WHERE 1=1 " +
        "AND (? IS NULL OR pa.fecha_pago >= ?) " +
        "AND (? IS NULL OR pa.fecha_pago <= ?) " +
        "AND (? IS NULL OR ta.tipo_anuncio = ?) " +
        "ORDER BY pa.fecha_pago DESC " +
        "LIMIT ? OFFSET ?";

    try (
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      int paramIndex = 1;

      // * Parámetros para fecha inicio
      if (fechaInicio != null) {
        pstmt.setDate(paramIndex++, Date.valueOf(fechaInicio));
        pstmt.setDate(paramIndex++, Date.valueOf(fechaInicio));
      } else {
        pstmt.setNull(paramIndex++, Types.DATE);
        pstmt.setNull(paramIndex++, Types.DATE);
      }

      // * Parámetros para fecha fin
      if (fechaFin != null) {
        pstmt.setDate(paramIndex++, Date.valueOf(fechaFin));
        pstmt.setDate(paramIndex++, Date.valueOf(fechaFin));
      } else {
        pstmt.setNull(paramIndex++, Types.DATE);
        pstmt.setNull(paramIndex++, Types.DATE);
      }

      // * Parámetros para tipo de anuncio
      pstmt.setString(paramIndex++, tipoAnuncio);
      pstmt.setString(paramIndex++, tipoAnuncio);

      // * Parámetros para LIMIT y OFFSET
      pstmt.setInt(paramIndex++, limit);
      pstmt.setInt(paramIndex++, offset);

      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
          PurchasedAdvertisement ad = new PurchasedAdvertisement();
          ad.setIdAnuncio(rs.getInt("id_anuncio"));
          ad.setNombreAnuncio(rs.getString("nombre_anuncio"));
          ad.setTipoAnuncio(rs.getString("tipo_anuncio"));
          ad.setFechaPago(rs.getDate("fecha_pago").toLocalDate());
          ad.setMontoPago(rs.getBigDecimal("monto_pago"));
          advertisements.add(ad);
        }
      }
    } catch (SQLException e) {
      throw new DataBaseException("Error al obtener anuncios comprados", e);
    }
    return advertisements;
  }
}
