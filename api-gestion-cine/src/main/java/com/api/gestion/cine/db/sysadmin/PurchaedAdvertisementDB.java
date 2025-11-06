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

public class PurchaedAdvertisementDB {

  public List<PurchasedAdvertisement> getPurchasedAdvertisements(LocalDate fechaInicio, LocalDate fechaFin,
      String tipoAnuncio) throws Exception {

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
        "AND (? = 'Todo' OR ta.tipo_anuncio = ?) " +
        "ORDER BY pa.fecha_pago DESC";

    try (
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      if (fechaInicio != null) {
        pstmt.setDate(1, Date.valueOf(fechaInicio));
        pstmt.setDate(2, Date.valueOf(fechaInicio));
      } else {
        pstmt.setNull(1, Types.DATE);
        pstmt.setNull(2, Types.DATE);
      }

      // Parámetros para fecha fin
      if (fechaFin != null) {
        pstmt.setDate(3, Date.valueOf(fechaFin));
        pstmt.setDate(4, Date.valueOf(fechaFin));
      } else {
        pstmt.setNull(3, Types.DATE);
        pstmt.setNull(4, Types.DATE);
      }

      // Para los tipos de anuncio
      pstmt.setString(5, tipoAnuncio);
      pstmt.setString(6, tipoAnuncio);

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
