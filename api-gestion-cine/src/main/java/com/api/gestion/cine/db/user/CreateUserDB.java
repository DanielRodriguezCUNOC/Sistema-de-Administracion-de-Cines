package com.api.gestion.cine.db.user;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.api.gestion.cine.db.connection.DBConnectionSingleton;
import com.api.gestion.cine.dto.users.CreateUserDTO;

public class CreateUserDB {

  public void createUser(CreateUserDTO userDTO, int idRol) throws Exception {
    System.out.println("Ingresando al método de creación de usuario en la base de datos");

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
      pstmt.setBytes(7, userDTO.getFoto());

      int affectedRows = pstmt.executeUpdate();
      if (affectedRows == 0) {
        System.out.println("No se pudo insertar el usuario");
        throw new Exception("No se pudo insertar el usuario");
      }
      System.out.println("Usuario insertado correctamente en la base de datos");

      conn.commit();

    } catch (Exception e) {
      if (conn != null) {
        try {
          conn.rollback();
        } catch (Exception ex) {
          System.out.println("Error al hacer rollback: " + ex.getMessage());
        }
      }
      throw e;
    } finally {
      if (conn != null) {
        try {
          conn.close();
        } catch (Exception ex) {
          System.out.println("Error al cerrar la conexión: " + ex.getMessage());
        }
      }
    }
  }

}
