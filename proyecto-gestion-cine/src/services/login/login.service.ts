import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, map, throwError } from 'rxjs';
import { environment } from '../../enviroments/enviroments';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../../models/users/user';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  // Declaramos la ruta hacia el endpoint de login
  private apiUrl = `${environment.apiBaseUrl}/login`;
  // Configuramos las opciones HTTP para las solicitudes
  private httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  };

  // Creamos un Subject para manejar el estado de autenticación
  private isLoggedInSubject = new BehaviorSubject<boolean>(false);
  // Exponemos el estado de autenticación como un Observable
  private isLoggedIn$ = this.isLoggedInSubject.asObservable();

  constructor(private http: HttpClient) {
    this.estadoLogin();
  }

  // Verificamos si el estado del login
  private estadoLogin(): void {
    const LOGGED = !!localStorage.getItem('usuarioActual');
    this.isLoggedInSubject.next(LOGGED);
  }

  // Método para autenticar al usuario con el backend, este devuelve un objeto de tipo Usuario
  autenticacionBackend(usuario: string, password: String) {
    return this.http
      .post<User>(`${this.apiUrl}/auth`, { usuario, password }, this.httpOptions)
      .pipe(
        map((user) => {
          //Almacenar al usuario en localStorage
          localStorage.setItem('usuarioActual', JSON.stringify(user));
          this.isLoggedInSubject.next(true);
          return user;
        }),
        catchError((error) => {
          // No guardamos nada en localStorage
          return throwError(() => error);
        })
      );
  }

  // Método para cerrar sesión
  logOut(): void {
    localStorage.removeItem('usuarioActual');
    this.isLoggedInSubject.next(false);
  }

  // Getter para obtener el estado de autenticación
  get isLoggedIn() {
    return this.isLoggedInSubject.value;
  }
}
