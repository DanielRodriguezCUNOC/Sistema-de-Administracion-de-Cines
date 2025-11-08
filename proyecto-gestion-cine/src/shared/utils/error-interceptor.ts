// error.interceptor.ts
import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { catchError } from 'rxjs';
import { throwError } from 'rxjs';

export const errorInterceptor: HttpInterceptorFn = (req, next) => {
  return next(req).pipe(
    catchError((error: HttpErrorResponse) => {
      let message = 'Ocurrió un error inesperado.';
      if (error.status === 0) {
        message = 'No se pudo conectar con el servidor. Verifica tu conexión.';
      } else {
        switch (error.status) {
          case 400:
            message = 'Los datos enviados no son válidos.';
            break;
          case 401:
            message = 'No tienes autorización para esta acción.';
            break;
          case 404:
            message = 'No se encontró el recurso solicitado.';
            break;
          case 409:
            message = 'El usuario o correo ya existe.';
            break;
          case 500:
            message = 'Error interno del servidor. Intenta más tarde.';
            break;
        }
      }
      return throwError(() => new Error(message));
    })
  );
};
