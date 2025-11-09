import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from '../../../enviroments/enviroments';
import { MostCommentedRoomReportResponseDTO } from '../../../models/dto/sysadmin/most-commented-room-report/most-commented-room-report-response-dto';

@Injectable({
  providedIn: 'root',
})
export class MostCommentedRoomReportService {
  private apiUrl = `${environment.apiBaseUrl}/sysadmin/report/commented-room`;

  constructor(private http: HttpClient) {}
  generateReport(
    fechaInicio: string | null,
    fechaFin: string | null
  ): Observable<MostCommentedRoomReportResponseDTO> {
    const url = `${this.apiUrl}/inicio/${fechaInicio}/fin/${fechaFin}`;

    return this.http
      .get<MostCommentedRoomReportResponseDTO>(url)
      .pipe(catchError(this.handleError));
  }
  private handleError(error: HttpErrorResponse): Observable<never> {
    return throwError(() => new Error('Error al generar el reporte de salas comentadas'));
  }
}
