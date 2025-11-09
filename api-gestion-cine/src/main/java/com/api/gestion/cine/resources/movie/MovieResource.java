package com.api.gestion.cine.resources.movie;

import java.util.List;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.api.gestion.cine.dto.movie.ListMovieDTO;
import com.api.gestion.cine.services.movie.PeliculaService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("pelicula")
public class MovieResource {

  @POST
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  public Response crearPelicula(
      @FormDataParam("idUsuario") String idUsuario,
      @FormDataParam("tituloPelicula") String tituloPelicula,
      @FormDataParam("sinopsis") String sinopsis,
      @FormDataParam("duracion") String duracion,
      @FormDataParam("reparto") String reparto,
      @FormDataParam("director") String director,
      @FormDataParam("clasificacion") String clasificacion,
      @FormDataParam("fechaEstreno") String fechaEstreno,
      @FormDataParam("precioPelicula") String precioPelicula,
      @FormDataParam("poster") FormDataBodyPart posterPart,
      @FormDataParam("categorias") List<String> categorias) {

    PeliculaService peliculaService = new PeliculaService();
    try {
      peliculaService.crearPelicula(idUsuario, tituloPelicula, sinopsis, duracion, reparto, director,
          clasificacion, fechaEstreno, precioPelicula, posterPart, categorias);
      return Response.status(Response.Status.CREATED).build();
    } catch (Exception e) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
          .entity("Error interno al crear la película")
          .build();
    }
  }

  @GET
  public Response obtenerPeliculas() {
    PeliculaService peliculaService = new PeliculaService();
    try {
      List<ListMovieDTO> peliculas = peliculaService.obtenerTodasLasPeliculas();
      return Response.ok(peliculas).build();
    } catch (Exception e) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
          .entity("Error interno al obtener las películas")
          .build();
    }
  }

  @DELETE
  @Path("/{idPelicula}")
  public Response eliminarPelicula(@PathParam("idPelicula") String idPelicula) {
    PeliculaService peliculaService = new PeliculaService();
    try {
      peliculaService.eliminarPelicula(idPelicula);
      return Response.ok().build();
    } catch (Exception e) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
          .entity("Error interno al eliminar la película")
          .build();
    }
  }

  @PUT
  @Path("/{idPelicula}")
  public Response actualizarPelicula(@PathParam("idPelicula") String idPelicula,
      @FormDataParam("tituloPelicula") String tituloPelicula,
      @FormDataParam("sinopsis") String sinopsis,
      @FormDataParam("duracion") String duracion,
      @FormDataParam("reparto") String reparto,
      @FormDataParam("director") String director,
      @FormDataParam("clasificacion") String clasificacion,
      @FormDataParam("fechaEstreno") String fechaEstreno,
      @FormDataParam("precioPelicula") String precioPelicula,
      @FormDataParam("poster") FormDataBodyPart posterPart,
      @FormDataParam("categorias") List<String> categorias) {
    PeliculaService peliculaService = new PeliculaService();
    try {
      peliculaService.actualizarPelicula(idPelicula, tituloPelicula, sinopsis, duracion, reparto, director,
          clasificacion, fechaEstreno, precioPelicula, posterPart, categorias);
      return Response.ok().build();
    } catch (Exception e) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
          .entity("Error interno al actualizar la película")
          .build();
    }
  }
}
