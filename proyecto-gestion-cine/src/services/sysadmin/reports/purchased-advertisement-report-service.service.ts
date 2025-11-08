import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from '../../../enviroments/enviroments';
import { PurchasedAdvertisementResponseDTO } from '../../../models/dto/sysadmin/purchased-advertisement-report/purchased-advertisement-response-dto';

@Injectable({
  providedIn: 'root',
})
export class PurchasedAdvertisementReportService {
  private apiUrl = `${environment.apiBaseUrl}/sysadmin/report/purchased-advertisements`;

  constructor(private http: HttpClient) {}

  generateReport(
    fechaInicio: string | null,
    fechaFin: string | null,
    tipoAnuncio: string | null,
    offset: number,
    limit: number
  ): Observable<PurchasedAdvertisementResponseDTO> {
    const url = `${this.apiUrl}/inicio/${fechaInicio}/fin/${fechaFin}/tipo-anuncio/${tipoAnuncio}/offset/${offset}/limit/${limit}`;

    return this.http.get<PurchasedAdvertisementResponseDTO>(url);
  }
}
