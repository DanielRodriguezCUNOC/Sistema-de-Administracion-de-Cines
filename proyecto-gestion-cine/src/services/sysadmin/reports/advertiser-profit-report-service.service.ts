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
    return throwError(() => new Error('Error al generar el reporte de anuncios comprados'));
  }
}
