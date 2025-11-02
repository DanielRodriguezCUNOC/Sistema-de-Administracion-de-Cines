import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { ProfitReportResponseDTO } from '../../../models/dto/sysadmin/profit-report/profit-report-response-dto';
import { catchError, map, Observable, throwError } from 'rxjs';
import { Injectable } from '@angular/core';
import { environment } from '../../../enviroments/enviroments';

@Injectable({
  providedIn: 'root',
})
export class ProfitReportService {
  private apiUrl = `${environment.apiBaseUrl}/sysadmin/report/profit`;

  constructor(private http: HttpClient) {}

  generateProfitReport(startDate: string, endDate: string) {
    const url = `${this.apiUrl}/inicio/${startDate}/fin/${endDate}`;
    return this.http.get<ProfitReportResponseDTO>(url).pipe(catchError(this.handleError));
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.error('Error en el reporte de ganancias:', error);
    return throwError(() => new Error('Error al generar el reporte de ganancias'));
  }
}
