package com.api.gestion.cine.db.categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.api.gestion.cine.db.connection.DBConnectionSingleton;
import com.api.gestion.cine.exceptions.DataBaseException;

public class CategoriaDB {

  public List<Integer> getCategoryIdsByNames(List<String> categoryNames) throws Exception {

    List<Integer> categoryIds = new ArrayList<>();
    Connection conn = DBConnectionSingleton.getInstance().getConnection();
    String sql = "SELECT id_categoria FROM categoria WHERE categoria = ?";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      for (String categoryName : categoryNames) {
        stmt.setString(1, categoryName);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
          int categoryId = rs.getInt("id_categoria");
          categoryIds.add(categoryId);
        }
      }
    } catch (SQLException e) {
      throw new DataBaseException("Error al obtener los IDs de las categorías desde la base de datos.", e);
    }
    return categoryIds;
  }

  public boolean insertarPeliculaPorCategoria(int idPelicula, int idCategoria) throws Exception {
    Connection conn = DBConnectionSingleton.getInstance().getConnection();
    String sql = "INSERT INTO categoria_pelicula (id_pelicula, id_categoria) VALUES (?, ?)";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setInt(1, idPelicula);
      stmt.setInt(2, idCategoria);
      stmt.executeUpdate();
      return true;
    } catch (SQLException e) {
      throw new DataBaseException("Error al insertar la relación entre película y categoría en la base de datos.", e);
    }
  }
}
