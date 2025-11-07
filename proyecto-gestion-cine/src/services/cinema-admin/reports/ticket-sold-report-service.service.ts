import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from '../../../enviroments/enviroments';
import { SoldTicketResponseReportDTO } from '../../../models/dto/cinema-admin/sold-ticket-report/sold-ticket-response-report-dto';

@Injectable({
  providedIn: 'root',
})
export class TicketSoldReportService {
  private apiUrl = `${environment.apiBaseUrl}/admin-cinema/report/ticket-sold`;

  constructor(private http: HttpClient) {}

  generateReport(
    startDate: string | null,
    endDate: string | null,
    nombreSala: string | null
  ): Observable<SoldTicketResponseReportDTO> {
    const url = `${this.apiUrl}/inicio/${startDate}/fin/${endDate}/nombreSala/${nombreSala}`;
    return this.http.get<SoldTicketResponseReportDTO>(url).pipe(catchError(this.handleError));
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    return throwError(() => new Error('Error al generar el reporte de boletos vendidos'));
  }
}
