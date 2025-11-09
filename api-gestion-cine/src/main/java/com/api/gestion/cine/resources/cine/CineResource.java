package com.api.gestion.cine.resources.cine;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.glassfish.jersey.media.multipart.FormDataParam;

import com.api.gestion.cine.dto.cine.CreateCinemaDTO;
import com.api.gestion.cine.dto.cine.ListadoCineDTO;
import com.api.gestion.cine.services.cine.CineService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("cine")
public class CineResource {

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createCinema(CreateCinemaDTO createCinemaDTO) {

    CineService cineService = new CineService();
    try {
      cineService.crearCinema(createCinemaDTO);
      return Response.ok().build();
    } catch (Exception e) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
          .entity(e.getMessage())
          .build();
    }

  }

  @PUT
  @Path("/{idCine}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response updateCinema(@PathParam("idCine") int idCine,
      @FormDataParam("nombreCine") String nombreCine,
      @FormDataParam("fechaCreacion") LocalDate fechaCreacion,
      @FormDataParam("costoOcultacionAnuncio") BigDecimal costoOcultacionAnuncio) {

    CineService cineService = new CineService();
    try {
      cineService.actualizarCinema(idCine, nombreCine, fechaCreacion, costoOcultacionAnuncio);

      return Response.ok().build();
    } catch (Exception e) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
          .entity(e.getMessage())
          .build();
    }
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAllCines() {

    CineService cineService = new CineService();
    ListadoCineDTO listado = new ListadoCineDTO();

    try {
      listado = cineService.obtenerTodosCines();
      return Response.ok(listado).build();
    } catch (Exception e) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
          .entity(e.getMessage())
          .build();
    }
  }

}
