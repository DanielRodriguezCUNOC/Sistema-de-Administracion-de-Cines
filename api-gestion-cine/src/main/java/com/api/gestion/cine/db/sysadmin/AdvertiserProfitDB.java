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
import com.api.gestion.cine.dto.reports.sysadmin.advertiser_profit_report.AdvertiserList;
import com.api.gestion.cine.dto.reports.sysadmin.purchased_advertisement_report.PurchasedAdvertisement;
import com.api.gestion.cine.exceptions.DataBaseException;

public class AdvertiserProfitDB {

  public List<AdvertiserList> getAdvertiserReport(LocalDate startDate, LocalDate endDate, String nombreAnunciante)
      throws Exception {

    List<AdvertiserList> advertisers = new ArrayList<>();

    Connection conn = DBConnectionSingleton.getInstance().getConnection();

    // * Obtenemos el nombre del anunciante */

    String sql = "SELECT DISTINCT u.id_usuario, u.nombre_completo " +
        "FROM usuario u " +
        "INNER JOIN pago_anuncio pa ON u.id_usuario = pa.id_usuario " +
        "WHERE pa.fecha_pago BETWEEN ? AND ? " +
        "AND (? IS NULL OR u.nombre_completo LIKE CONCAT('%', ?, '%')) " +
        "ORDER BY u.nombre_completo";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setDate(1, Date.valueOf(startDate));
      pstmt.setDate(2, Date.valueOf(endDate));

      if (nombreAnunciante != null && !nombreAnunciante.trim().isEmpty() && !nombreAnunciante.equals("Todo")) {
        pstmt.setString(3, nombreAnunciante);
        pstmt.setString(4, nombreAnunciante);
      } else {
        pstmt.setNull(3, Types.VARCHAR);
        pstmt.setNull(4, Types.VARCHAR);
      }

      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
          int userId = rs.getInt("id_usuario");
          String userName = rs.getString("nombre_completo");

          // * Para cada anunciante, obtener sus anuncios */
          List<PurchasedAdvertisement> ads = getAdsByAdvertiser(userId, startDate, endDate);

          // * Solo agregar anunciantes que tengan anuncios en el período */
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

    Connection conn = DBConnectionSingleton.getInstance().getConnection();

    // * Obtenemos los anuncios que ha pagado el anunciante */
    String sql = "SELECT a.id_anuncio, a.nombre_anuncio, ta.tipo_anuncio, " +
        "pa.fecha_pago, pa.monto_pago " +
        "FROM pago_anuncio pa " +
        "INNER JOIN anuncio a ON pa.id_anuncio = a.id_anuncio " +
        "INNER JOIN configuracion_anuncio ca ON a.id_configuracion_anuncio = ca.id_configuracion_anuncio " +
        "INNER JOIN tipo_anuncio ta ON ca.id_tipo_anuncio = ta.id_tipo_anuncio " +
        "WHERE pa.id_usuario = ? AND pa.fecha_pago BETWEEN ? AND ? " +
        "ORDER BY pa.fecha_pago DESC";

    try (
        PreparedStatement stmt = conn.prepareStatement(sql)) {

      stmt.setInt(1, userId);
      stmt.setDate(2, Date.valueOf(startDate));
      stmt.setDate(3, Date.valueOf(endDate));

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
