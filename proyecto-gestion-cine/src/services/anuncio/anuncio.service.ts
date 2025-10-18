import { Injectable } from '@angular/core';
import { RestConstants } from '../../shared/rest-constants';
import { Anuncio } from '../../models/advertisements/anuncio';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AnuncioService {
  restConstants = new RestConstants();
  constructor(private httpClient: HttpClient) {}

  public createNewAnuncio(anuncio: Anuncio): Observable<void> {
    return this.httpClient.post<void>(`${this.restConstants.getApiURL()}anuncios`, anuncio);
  }

  public getAnuncio(): Observable<Anuncio> {
    return this.httpClient.get<Anuncio>(`${this.restConstants.getApiURL()}anuncios`);
  }
}
