import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Pelicula } from '../../models/movies/pelicula';
import { RestConstants } from '../../shared/rest-constants';
import { CreateMovieDto } from '../../models/dto/movie/create-movie-dto';
import { environment } from '../../enviroments/enviroments';

export class PeliculaService {
  private apiUrl = `${environment.apiBaseUrl}/pelicula`;

  constructor(private httpClient: HttpClient) {}

  public createNewPelicula(pelicula: CreateMovieDto): Observable<any> {
    const formData = new FormData();
    formData.append('tituloPelicula', pelicula.tituloPelicula);
    formData.append('sinopsis', pelicula.sinopsis);
    formData.append('duracion', pelicula.duracion.toString());
    formData.append('cast', pelicula.cast);
    formData.append('director', pelicula.director);
    formData.append('clasificacion', pelicula.clasificacion);
    formData.append('fechaEstreno', pelicula.fechaEstreno);
    formData.append('precioPelicula', pelicula.precioPelicula.toString());
    if (pelicula.poster) {
      formData.append('poster', pelicula.poster, pelicula.poster.name);
    }
    return this.httpClient.post(this.apiUrl, formData);
  }

  public getPeliculas(): Observable<Pelicula[]> {
    return this.httpClient.get<Pelicula[]>(`${this.apiUrl}/peliculas`);
  }
}
