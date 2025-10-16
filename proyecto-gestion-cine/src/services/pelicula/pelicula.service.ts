import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Pelicula } from '../../models/pelicula';
import { RestConstants } from '../../shared/rest-constants';

export class PeliculaService {
  restConstants = new RestConstants();

  constructor(private httpClient: HttpClient) {}

  public createNewPelicula(pelicula: Pelicula): Observable<void> {
    return this.httpClient.post<void>(`${this.restConstants.getApiURL()}peliculas`, pelicula);
  }

  public getPeliculas(): Observable<Pelicula[]> {
    return this.httpClient.get<Pelicula[]>(`${this.restConstants.getApiURL()}peliculas`);
  }
}
