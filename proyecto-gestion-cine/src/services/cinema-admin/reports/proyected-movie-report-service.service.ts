import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from '../../../enviroments/enviroments';
import { ProyectedMoviesResponseReportDTO } from '../../../models/dto/cinema-admin/proyected-movies-report/proyected-movies-response-report-dto';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class ProyectedMovieReportService {
  private apiUrl = `${environment.apiBaseUrl}/cinema-admin/report/proyected-movies`;

  constructor(private http: HttpClient) {}

  getMovies(
    fechaInicio: string | null,
    fechaFin: string | null,
    nombreSala: string | null,
    offset: number,
    limit: number
  ): Observable<ProyectedMoviesResponseReportDTO> {
    const url = `${this.apiUrl}/inicio/${fechaInicio}/fin/${fechaFin}/nombreSala/${nombreSala}/offset/${offset}/limit/${limit}`;
    return this.http.get<ProyectedMoviesResponseReportDTO>(url);
  }
}
