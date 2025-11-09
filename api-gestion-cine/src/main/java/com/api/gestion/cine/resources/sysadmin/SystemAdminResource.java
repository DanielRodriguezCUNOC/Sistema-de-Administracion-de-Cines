package com.api.gestion.cine.resources.sysadmin;

import com.api.gestion.cine.dto.sysadmin.CreateCinemaAdminDTO;
import com.api.gestion.cine.services.sysadmin.SystemAdminService; // Asegúrate de que este servicio exista

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("/sysadmin")
public class SystemAdminResource {

  @POST
  @Path("/create-admin")
  @Consumes("multipart/form-data")
  public Response createAdminCinema(
      @FormDataParam("idRol") int idRol,
      @FormDataParam("nombreCompleto") String nombreCompleto,
      @FormDataParam("tipoUsuario") String tipoUsuario,
      @FormDataParam("usuario") String usuario,
      @FormDataParam("password") String password,
      @FormDataParam("correo") String correo,
      @FormDataParam("telefono") String telefono,
      @FormDataParam("foto") FormDataBodyPart foto) {
    SystemAdminService systemAdminService = new SystemAdminService();

    try {
      CreateCinemaAdminDTO adminDTO = new CreateCinemaAdminDTO();
      systemAdminService.convertFormDataToDTO(nombreCompleto, tipoUsuario, usuario, password, correo, telefono, foto);
      systemAdminService.createAdmin(adminDTO);
      return Response.ok().build();
    } catch (Exception e) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
          .entity("Error al crear el administrador: " + e.getMessage())
          .build();
    }
  }
}