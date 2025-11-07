package com.api.gestion.cine.resources.users;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.api.gestion.cine.dto.users.CreateUserDTO;
import com.api.gestion.cine.exceptions.UserAlreadyExists;
import com.api.gestion.cine.model.user.UsuarioModel;
import com.api.gestion.cine.services.user.CreateUserService;

import jakarta.websocket.server.PathParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("usuario/create-user")
public class UsuarioResource {

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response crearUsuario(
            @FormDataParam("userData") FormDataBodyPart userDataPart,
            @FormDataParam("foto") FormDataBodyPart fotoPart) {
        CreateUserService service = new CreateUserService();

        try {
            CreateUserDTO usuario = service.convertFormDataToDTO(userDataPart, fotoPart);
            service.createUser(usuario);
            return Response.status(Response.Status.CREATED).entity(usuario).build();

        } catch (UserAlreadyExists e) {
            return Response.status(Response.Status.CONFLICT)
                    .entity(e.getMessage())
                    .build();

        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error interno al crear usuario")
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerUsuario() {

        UsuarioModel usuario = new UsuarioModel(
                1,
                1,
                "Juanito Perez",
                " Junito23",
                "asdasdasd",
                "juanito.perez@example.com",
                "123456789",
                null);
        return Response.ok(usuario).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerUsuario(@PathParam("id") int id) {
        UsuarioModel usuario = new UsuarioModel();
        return Response.ok(usuario).build();
    }
}
