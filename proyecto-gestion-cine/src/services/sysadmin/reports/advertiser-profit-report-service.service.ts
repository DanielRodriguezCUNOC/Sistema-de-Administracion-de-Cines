import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../enviroments/enviroments';
import { AdvertiserProfitReportResponseDTO } from '../../../models/dto/sysadmin/advertiser-profit-report/advertiser-profit-report-response-dto';
import { catchError, Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AdvertiserProfitReportService {
  private apiUrl = `${environment.apiBaseUrl}/sysadmin/report/advertiser-profit`;

  constructor(private http: HttpClient) {}
  generateReport(
    fechaInicio: string | null,
    fechaFin: string | null,
    nombreAnunciante: string | null
  ): Observable<AdvertiserProfitReportResponseDTO> {
    const url = `${this.apiUrl}/inicio/${fechaInicio}/fin/${fechaFin}/anunciante/${nombreAnunciante}`;

    return this.http.get<AdvertiserProfitReportResponseDTO>(url).pipe(catchError(this.handleError));
  }
  private handleError(error: HttpErrorResponse): Observable<never> {
    let errorMessage = 'Error desconocido al generar el reporte.';

    if (error.error instanceof ErrorEvent) {
      errorMessage = `Error del cliente: ${error.error.message}`;
    } else {
      if (error.error?.message) {
        errorMessage = error.error.message;
      } else if (error.status === 0) {
        errorMessage = 'No se pudo conectar con el servidor.';
      } else {
        errorMessage = `Error ${error.status}: ${error.statusText}`;
      }
    }

    return throwError(() => new Error(errorMessage));
  }
}
