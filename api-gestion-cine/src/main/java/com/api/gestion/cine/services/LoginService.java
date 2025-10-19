package com.api.gestion.cine.services;

import com.api.gestion.cine.dto.login.LoginDTO;
import com.api.gestion.cine.dto.users.UserResponseLoginDTO;
import com.api.gestion.cine.exceptions.UnauthorizedException;

public class LoginService {

    public UserResponseLoginDTO autenticar(LoginDTO loginDTO) throws UnauthorizedException {
        // *Lógica de autenticación (simulada para este ejemplo)

        if ("admin".equals(loginDTO.getUsuario()) && "password".equals(loginDTO.getPassword())) {

            // *Se retorna un objeto de tipo UserResponseLoginDTO */
            return new UserResponseLoginDTO(1, 2);

        } else {

            throw new UnauthorizedException("Usuario o contraseña incorrectos");

        }
    }

}
