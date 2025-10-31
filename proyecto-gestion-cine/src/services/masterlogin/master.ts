import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { UsuarioLoginDTO } from '../../models/dto/login/usuario-login-dto';

@Injectable({
  providedIn: 'root',
})
export class MasterLoginService {
  //* BehaviorSubject para manejar el estado de autenticación global
  private isLoggedInSubject = new BehaviorSubject<boolean>(false);

  //*Observable para exponer el estado de autenticación
  public isLoggedIn$: Observable<boolean> = this.isLoggedInSubject.asObservable();

  //* BehaviorSubject para manejar el usuario autenticado
  private currentUserSubject = new BehaviorSubject<any>(null);

  //*Observable para exponer el usuario autenticado
  public currentUser$: Observable<UsuarioLoginDTO | null> = this.currentUserSubject.asObservable();

  constructor(private http: HttpClient) {
    //Verificamos si hay sesiones activas

    this.checkLoginStatus();
  }

  //* Verificar si hay datos almacenados en localStorage
  private checkLoginStatus(): void {
    const userLoginData = localStorage.getItem('usuarioActual');
    if (userLoginData) {
      const user = JSON.parse(userLoginData) as UsuarioLoginDTO;
      this.currentUserSubject.next(user);
      this.isLoggedInSubject.next(true);
    }
  }

  //* Notificar el inicio de sesión
  setLogin(user: UsuarioLoginDTO): void {
    localStorage.setItem('usuarioActual', JSON.stringify(user));
    this.isLoggedInSubject.next(true);
    this.currentUserSubject.next(user);
  }

  //* Notificar el cierre de sesión
  setLogout(): void {
    localStorage.removeItem('usuarioActual');
    this.isLoggedInSubject.next(false);
    this.currentUserSubject.next(null);
  }

  //* Obtener el usuario de forma síncrona
  getCurrentUser(): UsuarioLoginDTO | null {
    return this.currentUserSubject.value;
  }

  //** Obtener el estado de autenticación de forma síncrona */
  isLoggedIn(): boolean {
    return this.isLoggedInSubject.value;
  }
}
