package com.api.gestion.cine.resources.users;

import com.api.gestion.cine.model.UsuarioModel;

import jakarta.websocket.server.PathParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("usuarios")
public class UsuarioResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearUsuario(UsuarioModel usuario) {
        return Response.status(Response.Status.CREATED).entity(usuario).build();
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
