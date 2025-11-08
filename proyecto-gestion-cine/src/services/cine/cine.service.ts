import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, Observable, throwError } from 'rxjs';
import { Cine } from '../../models/cinema/cine';
import { environment } from '../../enviroments/enviroments';

@Injectable({
  providedIn: 'root',
})
export class CineService {
  private apiUrl = `${environment.apiBaseUrl}/cine`;

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    }),
  };

  constructor(private http: HttpClient) {}

  //* Obtener la lista de cines */

  obtenerCines(): Observable<Cine[]> {
    return this.http.get<Cine[]>(this.apiUrl);
  }

  /**
   * Obtener un cine por ID
   */
  obtenerCinePorId(idCine: number): Observable<Cine> {
    const url = `${this.apiUrl}/obtener-cine/${idCine}`;
    return this.http.get<Cine>(url);
  }

  /**
   * Crear un nuevo cine
   */
  crearCine(cine: Cine): Observable<any> {
    const formData = new FormData();
    formData.append('nombreCine', cine.nombreCine);
    formData.append('fechaCreacion', cine.fechaCreacion.toISOString());
    formData.append('costoOcultacionAnuncios', cine.costoOcultacionAnuncios.toString());

    return this.http.post(this.apiUrl, formData);
  }

  /**
   * Actualizar un cine existente
   */
  actualizarCine(idCine: number, cine: Cine): Observable<Cine> {
    const url = `${this.apiUrl}/actualizar-cine/${idCine}`;
    return this.http.put<Cine>(url, cine, this.httpOptions);
  }

  /**
   * Eliminar un cine
   */
  eliminarCine(idCine: number): Observable<void> {
    const url = `${this.apiUrl}/eliminar-cine/${idCine}`;
    return this.http.delete<void>(url);
  }

  /**
   * Buscar cines por nombre
   */
  buscarCinesPorNombre(nombre: string): Observable<Cine[]> {
    const url = `${this.apiUrl}/buscar-cine/{nombre}`;
    return this.http.get<Cine[]>(url);
  }
}
