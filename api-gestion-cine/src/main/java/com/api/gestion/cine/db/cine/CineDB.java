package com.api.gestion.cine.db.cine;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.api.gestion.cine.db.connection.DBConnectionSingleton;
import com.api.gestion.cine.model.cine.CineModel;

public class CineDB {

  public void crearCinema(String nombreCine, LocalDate fechaCreacion,
      BigDecimal costoOcultacionAnuncio) throws Exception {
    Connection conn = DBConnectionSingleton.getInstance().getConnection();
    String sql = "INSERT INTO cine (nombre_cine, fecha_creacion, costo_ocultacion_anuncio) VALUES (?, ?, ?)";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, nombreCine);
      pstmt.setDate(2, Date.valueOf(fechaCreacion));
      pstmt.setBigDecimal(3, costoOcultacionAnuncio);

      int affectedRows = pstmt.executeUpdate();
      if (affectedRows == 0) {
        throw new Exception("No se pudo insertar el cine");
      }
    } catch (Exception e) {
      throw new Exception("Error al crear el cine: ", e);
    }
  }

  public void actualizarCinema(int idCine, String nombreCine) throws Exception {
    Connection conn = DBConnectionSingleton.getInstance().getConnection();
    String sql = "UPDATE cine SET nombre_cine = ? WHERE id_cine = ?";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, nombreCine);
      pstmt.setInt(2, idCine);

      int affectedRows = pstmt.executeUpdate();
      if (affectedRows == 0) {
        throw new Exception("No se encontró el cine con ID: " + idCine);
      }
    } catch (Exception e) {
      throw new Exception("Error al actualizar el cine: ", e);
    }
  }

  public List<CineModel> obtenerTodosCines() throws Exception {
    Connection conn = DBConnectionSingleton.getInstance().getConnection();
    String sql = "SELECT id_cine, nombre_cine, fecha_creacion, costo_ocultacion_anuncio FROM cine";
    List<CineModel> cines = new ArrayList<>();

    try (PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery()) {
      while (rs.next()) {
        CineModel cine = new CineModel();
        cine.setIdCine(rs.getInt("id_cine"));
        cine.setNombreCine(rs.getString("nombre_cine"));
        cine.setFechaCreacion(rs.getDate("fecha_creacion").toLocalDate());
        cine.setCostoOcultacionAnuncio(rs.getBigDecimal("costo_ocultacion_anuncio"));
        cines.add(cine);
      }
      return cines;
    } catch (Exception e) {
      throw new Exception("Error al obtener los cines: ", e);
    }
  }

  public CineModel obtenerCinePorId(int idCine) throws Exception {
    Connection conn = DBConnectionSingleton.getInstance().getConnection();
    String sql = "SELECT id_cine, nombre_cine, fecha_creacion, costo_ocultacion_anuncio FROM cine WHERE id_cine = ?";
    CineModel cine = null;

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, idCine);
      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          cine = new CineModel();
          cine.setIdCine(rs.getInt("id_cine"));
          cine.setNombreCine(rs.getString("nombre_cine"));
          cine.setFechaCreacion(rs.getDate("fecha_creacion").toLocalDate());
          cine.setCostoOcultacionAnuncio(rs.getBigDecimal("costo_ocultacion_anuncio"));
        }
      }
      return cine;
    } catch (Exception e) {
      throw new Exception("Error al obtener el cine por ID: ", e);
    }
  }

  public void desactivarCine(int idCine) throws Exception {
    Connection conn = DBConnectionSingleton.getInstance().getConnection();
    String sql = "UPDATE cine SET estado = 0 WHERE id_cine = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, idCine);
      int affectedRows = pstmt.executeUpdate();
      if (affectedRows == 0) {
        throw new Exception("No se encontró el cine con ID: " + idCine);
      }
    } catch (Exception e) {
      throw new Exception("Error al desactivar el cine: ", e);
    }
  }

  public List<CineModel> buscarCinesPorNombre(String nombre) throws Exception {
    Connection conn = DBConnectionSingleton.getInstance().getConnection();
    String sql = "SELECT id_cine, nombre_cine, fecha_creacion, costo_ocultacion_anuncio FROM cine WHERE nombre_cine LIKE ?";
    List<CineModel> cines = new ArrayList<>();

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, "%" + nombre + "%");
      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
          CineModel cine = new CineModel();
          cine.setIdCine(rs.getInt("id_cine"));
          cine.setNombreCine(rs.getString("nombre_cine"));
          cine.setFechaCreacion(rs.getDate("fecha_creacion").toLocalDate());
          cine.setCostoOcultacionAnuncio(rs.getBigDecimal("costo_ocultacion_anuncio"));
          cines.add(cine);
        }
      }
      return cines;
    } catch (Exception e) {
      throw new Exception("Error al buscar cines por nombre: ", e);
    }
  }
}