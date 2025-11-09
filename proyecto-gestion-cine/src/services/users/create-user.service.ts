import { Injectable } from '@angular/core';
import { environment } from '../../enviroments/enviroments';
import { HttpClient } from '@angular/common/http';
import { CreateUserDto } from '../../models/dto/user/create-user-dto';
import { Observable, catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CreateUserService {
  private apiUrl = `${environment.apiBaseUrl}/usuario`;

  constructor(private http: HttpClient) {}

  createUser(userData: CreateUserDto): Observable<any> {
    const formData = new FormData();
    formData.append('nombreCompleto', userData.nombreCompleto);
    formData.append('tipoUsuario', userData.tipoUsuario);
    formData.append('usuario', userData.usuario);
    formData.append('password', userData.password);
    formData.append('correo', userData.correo);
    formData.append('telefono', userData.telefono);
    if (userData.foto) {
      formData.append('foto', userData.foto, userData.foto.name);
    }

    return this.http.post(this.apiUrl, formData);
  }
}
