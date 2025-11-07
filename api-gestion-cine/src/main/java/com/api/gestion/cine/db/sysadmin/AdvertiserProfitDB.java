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
import com.api.gestion.cine.dto.reports.sysadmin.advertiser_profit_report.AdvertiserList;
import com.api.gestion.cine.dto.reports.sysadmin.purchased_advertisement_report.PurchasedAdvertisement;
import com.api.gestion.cine.exceptions.DataBaseException;

public class AdvertiserProfitDB {

  public List<AdvertiserList> getAdvertiserReport(LocalDate startDate, LocalDate endDate, String nombreAnunciante)
      throws Exception {

    List<AdvertiserList> advertisers = new ArrayList<>();

    StringBuilder sql = new StringBuilder(
        "SELECT DISTINCT u.id_usuario, u.nombre_completo " +
            "FROM usuario u " +
            "INNER JOIN pago_anuncio pa ON u.id_usuario = pa.id_usuario " +
            "WHERE 1=1 ");

    // Agregar dinámicamente filtros
    if (startDate != null) {
      sql.append("AND pa.fecha_pago >= ? ");
    }
    if (endDate != null) {
      sql.append("AND pa.fecha_pago <= ? ");
    }
    if (nombreAnunciante != null && !nombreAnunciante.trim().isEmpty()) {
      sql.append("AND u.nombre_completo LIKE ? ");
    }

    sql.append("ORDER BY u.nombre_completo");

    try (Connection conn = DBConnectionSingleton.getInstance().getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

      int paramIndex = 1;

      if (startDate != null) {
        pstmt.setDate(paramIndex++, Date.valueOf(startDate));
      }
      if (endDate != null) {
        pstmt.setDate(paramIndex++, Date.valueOf(endDate));
      }
      if (nombreAnunciante != null && !nombreAnunciante.trim().isEmpty()) {
        pstmt.setString(paramIndex++, "%" + nombreAnunciante + "%");
      }

      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
          int userId = rs.getInt("id_usuario");
          String userName = rs.getString("nombre_completo");

          // Traer los anuncios de ese anunciante
          List<PurchasedAdvertisement> ads = getAdsByAdvertiser(userId, startDate, endDate);

          if (!ads.isEmpty()) {
            AdvertiserList advertiser = new AdvertiserList();
            advertiser.setIdUsuario(userId);
            advertiser.setNombreUsuario(userName);
            advertiser.setPurchasedAdvertisement(ads.toArray(new PurchasedAdvertisement[0]));
            advertiser.calculateTotalPurchasedAmount();

            advertisers.add(advertiser);
          }
        }
      }
    } catch (SQLException e) {
      throw new DataBaseException("Error al obtener informe de anunciantes", e);
    }

    return advertisers;
  }

  private List<PurchasedAdvertisement> getAdsByAdvertiser(int userId, LocalDate startDate, LocalDate endDate)
      throws Exception {

    List<PurchasedAdvertisement> ads = new ArrayList<>();

    StringBuilder sql = new StringBuilder(
        "SELECT a.id_anuncio, a.nombre_anuncio, ta.tipo_anuncio, " +
            "pa.fecha_pago, pa.monto_pago " +
            "FROM pago_anuncio pa " +
            "INNER JOIN anuncio a ON pa.id_anuncio = a.id_anuncio " +
            "INNER JOIN configuracion_anuncio ca ON a.id_configuracion_anuncio = ca.id_configuracion_anuncio " +
            "INNER JOIN tipo_anuncio ta ON ca.id_tipo_anuncio = ta.id_tipo_anuncio " +
            "WHERE pa.id_usuario = ? ");

    if (startDate != null) {
      sql.append("AND pa.fecha_pago >= ? ");
    }
    if (endDate != null) {
      sql.append("AND pa.fecha_pago <= ? ");
    }

    sql.append("ORDER BY pa.fecha_pago DESC");

    try (Connection conn = DBConnectionSingleton.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

      int paramIndex = 1;
      stmt.setInt(paramIndex++, userId);

      if (startDate != null) {
        stmt.setDate(paramIndex++, Date.valueOf(startDate));
      }
      if (endDate != null) {
        stmt.setDate(paramIndex++, Date.valueOf(endDate));
      }

      try (ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
          PurchasedAdvertisement ad = new PurchasedAdvertisement();
          ad.setIdAnuncio(rs.getInt("id_anuncio"));
          ad.setNombreAnuncio(rs.getString("nombre_anuncio"));
          ad.setTipoAnuncio(rs.getString("tipo_anuncio"));
          ad.setFechaPago(rs.getDate("fecha_pago").toLocalDate());
          ad.setMontoPago(rs.getBigDecimal("monto_pago"));
          ads.add(ad);
        }
      }
    } catch (SQLException e) {
      throw new DataBaseException("Error al obtener anuncios del anunciante", e);
    }

    return ads;
  }

}
