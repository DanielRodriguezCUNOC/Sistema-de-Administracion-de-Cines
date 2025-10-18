import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PagoBoleto } from '../../models/payments/pago-boleto';

@Injectable({
  providedIn: 'root',
})
export class PaymentTicketService {
  private apiURL = 'http://localhost:8080/api//v1/pago-boleto';

  constructor(private http: HttpClient) {}

  createPayment(paymentData: PagoBoleto): Observable<any> {
    return this.http.post(this.apiURL, paymentData);
  }
}
