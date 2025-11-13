import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from '../../../enviroments/enviroments';
import { MostPopularRoomReportResponseDTO } from '../../../models/dto/sysadmin/most-popular-room-report/most-popular-room-report-response-dto';

@Injectable({
  providedIn: 'root',
})
export class MostPopularRoomReportService {
  private apiUrl = `${environment.apiBaseUrl}/sysadmin/report/popular-room`;

  constructor(private http: HttpClient) {}
  generateReport(fechaInicio: string | null, fechaFin: string | null) {
    const url = `${this.apiUrl}/inicio/${fechaInicio}/fin/${fechaFin}`;

    return this.http.get<MostPopularRoomReportResponseDTO>(url).pipe(catchError(this.handleError));
  }
  private handleError(error: HttpErrorResponse): Observable<never> {
    return throwError(() => new Error('Error al generar el reporte de salas populares'));
  }
}
