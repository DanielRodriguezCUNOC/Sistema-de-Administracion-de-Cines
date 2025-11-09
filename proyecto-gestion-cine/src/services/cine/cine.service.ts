import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cine } from '../../models/cinema/cine';
import { environment } from '../../enviroments/enviroments';
import { CreateCineDto } from '../../models/dto/cine/create-cine-dto';
import { ListadoCineDTO } from '../../models/dto/cine/listado-cine-dto';

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
  obtenerCines(): Observable<ListadoCineDTO> {
    return this.http.get<ListadoCineDTO>(this.apiUrl);
  }

  /*
   * Obtener un cine por ID
   */
  obtenerCinePorId(idCine: number): Observable<Cine> {
    const url = `${this.apiUrl}/obtener-cine/${idCine}`;
    return this.http.get<Cine>(url);
  }

  /*
   * Crear un nuevo cine
   */
  crearCine(cine: CreateCineDto): Observable<any> {
    return this.http.post(this.apiUrl, cine, this.httpOptions);
  }

  /*
   * Actualizar un cine existente
   */
  actualizarCine(idCine: number, cineData: { nombreCine: string }): Observable<any> {
    // Envía solo el string en lugar del objeto
    const nombreString =
      typeof cineData.nombreCine === 'string'
        ? cineData.nombreCine
        : JSON.stringify(cineData.nombreCine);

    console.log('Enviando solo string:', nombreString);

    return this.http.put(`${this.apiUrl}/${idCine}`, nombreString, this.httpOptions);
  }

  /*
   * Eliminar un cine
   */
  desactivarCine(idCine: number): Observable<void> {
    const url = `${this.apiUrl}/desactivar-cine/${idCine}`;
    return this.http.delete<void>(url);
  }

  /*
   * Buscar cines por nombre
   */
  buscarCinesPorNombre(nombre: string): Observable<Cine[]> {
    const url = `${this.apiUrl}/buscar/${nombre}`;
    return this.http.get<Cine[]>(url);
  }
}
