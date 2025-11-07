package com.api.gestion.cine.db.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.api.gestion.cine.db.connection.DBConnectionSingleton;
import com.api.gestion.cine.exceptions.DataBaseException;

public class UserDB {

  public boolean usuarioExiste(String usuario) throws Exception {

    Connection conn = DBConnectionSingleton.getInstance().getConnection();
    String sql = "SELECT COUNT(*) FROM usuario WHERE usuario = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, usuario);
      ResultSet rs = pstmt.executeQuery();
      return rs.next() && rs.getInt(1) > 0;
    }
  }

  public boolean correoExiste(String correo) throws Exception {
    Connection conn = DBConnectionSingleton.getInstance().getConnection();
    String sql = "SELECT COUNT(*) FROM usuario WHERE correo = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, correo);
      ResultSet rs = pstmt.executeQuery();
      return rs.next() && rs.getInt(1) > 0;
    }
  }

  public boolean telefonoExiste(String telefono) throws Exception {
    Connection conn = DBConnectionSingleton.getInstance().getConnection();
    String sql = "SELECT COUNT(*) FROM usuario WHERE telefono = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, telefono);
      ResultSet rs = pstmt.executeQuery();
      return rs.next() && rs.getInt(1) > 0;
    }
  }

  public int getIdRol(String tipoUsuario) throws Exception {
    Connection conn = DBConnectionSingleton.getInstance().getConnection();
    String sql = "SELECT id_rol FROM rol WHERE tipo_rol = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, tipoUsuario);
      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        return rs.getInt("id_rol");
      } else {
        throw new DataBaseException("Tipo de usuario no encontrado");
      }
    }
  }

}
