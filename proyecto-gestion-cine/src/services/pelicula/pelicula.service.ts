import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Pelicula } from '../../models/movies/pelicula';
import { CreateMovieDto } from '../../models/dto/movie/create-movie-dto';
import { environment } from '../../enviroments/enviroments';
import { Injectable } from '@angular/core';
import { ListMovieDto } from '../../models/dto/movie/list-movie-dto';

@Injectable({
  providedIn: 'root',
})
export class PeliculaService {
  private apiUrl = `${environment.apiBaseUrl}/pelicula`;

  constructor(private httpClient: HttpClient) {}

  public createNewPelicula(pelicula: CreateMovieDto): Observable<any> {
    const formData = new FormData();
    formData.append('idUsuario', String(pelicula.idUsuario));
    formData.append('tituloPelicula', pelicula.tituloPelicula);
    formData.append('sinopsis', pelicula.sinopsis);
    formData.append('duracion', String(pelicula.duracion));
    formData.append('reparto', pelicula.reparto);
    formData.append('director', pelicula.director);
    formData.append('clasificacion', pelicula.clasificacion);
    formData.append('fechaEstreno', String(pelicula.fechaEstreno));
    formData.append('precioPelicula', String(pelicula.precioPelicula));
    if (pelicula.poster) {
      formData.append('poster', pelicula.poster, pelicula.poster.name);
    }
    if (pelicula.categorias && pelicula.categorias.length > 0) {
      pelicula.categorias.forEach((cat) => formData.append('categorias', cat));
    }

    return this.httpClient.post<any>(this.apiUrl, formData);
  }

  public getPeliculas(): Observable<ListMovieDto[]> {
    return this.httpClient.get<ListMovieDto[]>(`${this.apiUrl}/peliculas`);
  }

  public desactivarPelicula(idPelicula: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.apiUrl}/${idPelicula}`);
  }
}
