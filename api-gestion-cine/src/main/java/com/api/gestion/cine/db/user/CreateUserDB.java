package com.api.gestion.cine.db.user;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.api.gestion.cine.db.connection.DBConnectionSingleton;
import com.api.gestion.cine.dto.users.CreateUserDTO;

public class CreateUserDB {

  public void createUser(CreateUserDTO userDTO, int idRol) throws Exception {

    Connection conn = DBConnectionSingleton.getInstance().getConnection();

    String sql = "INSERT INTO usuario (id_rol, nombre_completo, usuario, password, correo, telefono, foto) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?)";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

      conn.setAutoCommit(false);

      pstmt.setInt(1, idRol);
      pstmt.setString(2, userDTO.getNombreCompleto());
      pstmt.setString(3, userDTO.getUsuario());
      pstmt.setString(4, userDTO.getPassword());
      pstmt.setString(5, userDTO.getCorreo());
      pstmt.setString(6, userDTO.getTelefono());

      if (userDTO.getFoto() != null) {
        pstmt.setBytes(7, userDTO.getFoto());
      } else {
        pstmt.setNull(7, java.sql.Types.BLOB);
      }

      int affectedRows = pstmt.executeUpdate();
      if (affectedRows == 0) {
        throw new Exception("No se pudo insertar el usuario");
      }

      conn.commit();

    } catch (Exception e) {
      if (conn != null) {
        try {
          conn.rollback();
        } catch (Exception ex) {

        }
      }
      throw e;
    } finally {
      if (conn != null) {
        try {
          conn.close();
        } catch (Exception ex) {

        }
      }
    }
  }

}
