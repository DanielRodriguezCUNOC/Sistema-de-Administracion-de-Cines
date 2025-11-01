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
    const url = `${this.apiUrl}?startDate=${startDate}&endDate=${endDate}`;
    console.log('URL de la solicitud:', url);
    return this.http.get<ProfitReportResponseDTO>(url).pipe(
      map((report) => ({
        ...report,
        costoCinema: report.costoCinema.map((cine) => ({
          ...cine,
          fechaModificacion: new Date(cine.fechaModificacion),
        })),
        advertisementPaymentAmount: report.advertisementPaymentAmount.map((ad) => ({
          ...ad,
          fechaPago: new Date(ad.fechaPago),
        })),
        amountAdBlock: report.amountAdBlock.map((ab) => ({
          ...ab,
          fechaPago: new Date(ab.fechaPago),
        })),
      })),
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.error('Error en el reporte de ganancias:', error);
    return throwError(() => new Error('Error al generar el reporte de ganancias'));
  }
}
