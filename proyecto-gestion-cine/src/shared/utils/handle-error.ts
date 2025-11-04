import { HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';

export class HandleError {
  private handleError(error: HttpErrorResponse): Observable<never> {
    console.error('Error en el reporte de ganancias:', error);
    return throwError(() => new Error('Error al generar el reporte de ganancias'));
  }
}
