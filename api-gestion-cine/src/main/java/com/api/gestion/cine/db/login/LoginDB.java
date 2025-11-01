package com.api.gestion.cine.db.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.api.gestion.cine.db.connection.DBConnectionSingleton;
import com.api.gestion.cine.exceptions.DataBaseException;

public class LoginDB {

    private static final String AUTENTICAR_USUARIO_QUERY = "SELECT id_usuario, id_rol FROM usuario WHERE usuario = ? AND password = ?";

    public boolean userExist(String usuario, String password) throws Exception {
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement ps = connection.prepareStatement(AUTENTICAR_USUARIO_QUERY)) {

            ps.setString(1, usuario);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            throw new DataBaseException("Error al consultar el usuario en la base de datos", e);
        }
    }

    public int[] getUserIdAndRole(String usuario, String password) throws Exception {
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement ps = connection.prepareStatement(AUTENTICAR_USUARIO_QUERY)) {

            ps.setString(1, usuario);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int userId = rs.getInt("id_usuario");
                    int roleId = rs.getInt("id_rol");
                    return new int[] { userId, roleId };
                } else {
                    throw new DataBaseException("Usuario no encontrado");
                }
            }

        } catch (SQLException e) {
            throw new DataBaseException("Error al consultar el usuario en la base de datos", e);
        }
    }
}
