import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, Observable, throwError } from 'rxjs';
import { Cine } from '../../models/cinema/cine';

@Injectable({
  providedIn: 'root',
})
export class CineService {
  private apiUrl = 'http://localhost:8080/api/cines';

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    }),
  };

  constructor(private http: HttpClient) {}

  //* Obtener la lista de cines */

  obtenerCines(): Observable<Cine[]> {
    return this.http.get<Cine[]>(this.apiUrl).pipe(
      map((cines) =>
        cines.map((cine) => ({
          ...cine,
          fechaCreacion: new Date(cine.fechaCreacion),
        }))
      ),
      catchError(this.handleError)
    );
  }

  /**
   * Obtener un cine por ID
   */
  obtenerCinePorId(idCine: number): Observable<Cine> {
    const url = `${this.apiUrl}/${idCine}`;
    return this.http.get<Cine>(url).pipe(
      map((cine) => ({
        ...cine,
        fechaCreacion: new Date(cine.fechaCreacion),
      })),
      catchError(this.handleError)
    );
  }

  /**
   * Crear un nuevo cine
   */
  crearCine(cine: Cine): Observable<Cine> {
    return this.http
      .post<Cine>(this.apiUrl, cine, this.httpOptions)
      .pipe(catchError(this.handleError));
  }

  /**
   * Actualizar un cine existente
   */
  actualizarCine(idCine: number, cine: Cine): Observable<Cine> {
    const url = `${this.apiUrl}/${idCine}`;
    return this.http.put<Cine>(url, cine, this.httpOptions).pipe(catchError(this.handleError));
  }

  /**
   * Eliminar un cine
   */
  eliminarCine(idCine: number): Observable<void> {
    const url = `${this.apiUrl}/${idCine}`;
    return this.http.delete<void>(url).pipe(catchError(this.handleError));
  }

  /**
   * Buscar cines por nombre
   */
  buscarCinesPorNombre(nombre: string): Observable<Cine[]> {
    const url = `${this.apiUrl}/buscar?nombre=${nombre}`;
    return this.http.get<Cine[]>(url).pipe(
      map((cines) =>
        cines.map((cine) => ({
          ...cine,
          fechaCreacion: new Date(cine.fechaCreacion),
        }))
      ),
      catchError(this.handleError)
    );
  }

  /**
   * Manejo centralizado de errores
   */
  private handleError(error: HttpErrorResponse): Observable<never> {
    let errorMessage = 'Ha ocurrido un error desconocido';

    if (error.error instanceof ErrorEvent) {
      // Error del lado del cliente
      errorMessage = `Error: ${error.error.message}`;
    } else {
      // Error del lado del servidor
      switch (error.status) {
        case 400:
          errorMessage = 'Solicitud incorrecta';
          break;
        case 401:
          errorMessage = 'No autorizado';
          break;
        case 403:
          errorMessage = 'Acceso prohibido';
          break;
        case 404:
          errorMessage = 'Cine no encontrado';
          break;
        case 500:
          errorMessage = 'Error interno del servidor';
          break;
        default:
          errorMessage = `Error del servidor: ${error.status} - ${error.message}`;
      }
    }

    console.error(errorMessage, error);
    return throwError(() => new Error(errorMessage));
  }
}
