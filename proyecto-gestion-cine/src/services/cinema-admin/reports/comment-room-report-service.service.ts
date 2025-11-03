import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from '../../../enviroments/enviroments';
import { CommentedRoomResponseReportDTO } from '../../../models/dto/cinema-admin/commented-room-report/commented-room-response-report-dto';

@Injectable({
  providedIn: 'root',
})
export class CommentedRoomReportService {
  private apiUrl = `${environment.apiBaseUrl}/admin-cinema/report/commented-room`;

  constructor(private http: HttpClient) {}
  generateReport(fechaInicio: string, fechaFin: string, idSala: number) {
    const url = `${this.apiUrl}/inicio/${fechaInicio}/fin/${fechaFin}/sala/${idSala}`;
    return this.http.get<CommentedRoomResponseReportDTO>(url).pipe(catchError(this.handleError));
  }
  private handleError(error: HttpErrorResponse): Observable<never> {
    return throwError(() => new Error('Error al generar el reporte de salas comentadas'));
  }
}
