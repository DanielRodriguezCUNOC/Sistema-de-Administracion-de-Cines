import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from '../../../enviroments/enviroments';
import { MostLikedRoomResponseReportDTO } from '../../../models/dto/cinema-admin/most-liked-room-report/most-liked-room-response-report-dto';

@Injectable({
  providedIn: 'root',
})
export class MostLikedRoomReportService {
  private apiUrl = `${environment.apiBaseUrl}/cinema-admin/report/liked-room`;

  constructor(private http: HttpClient) {}

  generateReport(
    startDate: string | null,
    endDate: string | null,
    nombreSala?: string | null
  ): Observable<MostLikedRoomResponseReportDTO> {
    const url = `${this.apiUrl}/inicio/${startDate}/fin/${endDate}/nombreSala/${nombreSala}`;
    return this.http.get<MostLikedRoomResponseReportDTO>(url).pipe(catchError(this.handleError));
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    return throwError(() => new Error('Error al generar el reporte de salas más gustadas'));
  }
}
