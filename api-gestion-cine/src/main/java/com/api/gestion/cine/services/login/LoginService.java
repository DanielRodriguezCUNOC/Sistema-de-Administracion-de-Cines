package com.api.gestion.cine.services.login;

import com.api.gestion.cine.db.login.LoginDB;
import com.api.gestion.cine.dto.login.LoginDTO;
import com.api.gestion.cine.dto.users.UserResponseLoginDTO;
import com.api.gestion.cine.exceptions.UnauthorizedException;

public class LoginService {

    public UserResponseLoginDTO autenticar(LoginDTO loginDTO) throws UnauthorizedException {
        LoginDB loginDB = new LoginDB();

        try {
            boolean userExists = loginDB.userExist(loginDTO.getUsuario(), loginDTO.getPassword());
            if (userExists) {

                int[] userIdAndRole = loginDB.getUserIdAndRole(loginDTO.getUsuario(), loginDTO.getPassword());
                int userId = userIdAndRole[0];
                int roleId = userIdAndRole[1];

                UserResponseLoginDTO response = new UserResponseLoginDTO();
                response.setIdUsuario(userId);
                response.setIdRol(roleId);
                return response;

            } else {
                throw new UnauthorizedException("Credenciales inválidas");
            }
        } catch (Exception e) {
            throw new UnauthorizedException("Error al autenticar el usuario", e);
        }
    }

}
