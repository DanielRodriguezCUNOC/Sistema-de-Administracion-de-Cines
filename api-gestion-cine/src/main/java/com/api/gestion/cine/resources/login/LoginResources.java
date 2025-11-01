package com.api.gestion.cine.resources.login;

import com.api.gestion.cine.dto.login.LoginDTO;
import com.api.gestion.cine.dto.users.UserResponseLoginDTO;
import com.api.gestion.cine.services.login.LoginService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("login")
public class LoginResources {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response autenticarUsuario(LoginDTO loginDto) {

        try {
            // * Creación del servicio de login */
            LoginService loginService = new LoginService();

            // * Llamada al servicio de autenticación */
            UserResponseLoginDTO userResponse = loginService.autenticar(loginDto);

            // * Retorno de la respuesta exitosa */
            return Response.ok(userResponse).build();

        } catch (Exception e) {
            // ! Retorno de la respuesta en caso de error */
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
    }
}
