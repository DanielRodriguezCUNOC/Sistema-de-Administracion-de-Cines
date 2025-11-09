package com.api.gestion.cine.resources.cine;

import java.util.List;

import com.api.gestion.cine.dto.cine.CreateCinemaDTO;
import com.api.gestion.cine.dto.cine.ListadoCineDTO;
import com.api.gestion.cine.model.cine.CineModel;
import com.api.gestion.cine.services.cine.CineService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
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
  public Response updateCinema(@PathParam("idCine") int idCine, String nombreCine) {
    CineService cineService = new CineService();
    try {
      cineService.actualizarCinema(idCine, nombreCine);
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

  @GET
  @Path("/obtener-cine/{idCine}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getCinePorId(@PathParam("idCine") int idCine) {
    CineService cineService = new CineService();
    try {
      CineModel cine = cineService.obtenerCinePorId(idCine);
      return Response.ok(cine).build();
    } catch (Exception e) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
          .entity(e.getMessage())
          .build();
    }
  }

  @DELETE
  @Path("/desactivar-cine/{idCine}")
  public Response desactivarCine(@PathParam("idCine") int idCine) {
    CineService cineService = new CineService();
    try {
      cineService.desactivarCine(idCine);
      return Response.noContent().build();
    } catch (Exception e) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
          .entity(e.getMessage())
          .build();
    }
  }

  @GET
  @Path("/buscar/{nombre}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response buscarCinesPorNombre(@PathParam("nombre") String nombre) {
    CineService cineService = new CineService();
    try {
      List<CineModel> cines = cineService.buscarCinesPorNombre(nombre);
      return Response.ok(cines).build();
    } catch (Exception e) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
          .entity(e.getMessage())
          .build();
    }
  }
}