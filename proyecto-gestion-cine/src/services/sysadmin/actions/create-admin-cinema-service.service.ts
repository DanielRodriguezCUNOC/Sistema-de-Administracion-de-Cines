import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../enviroments/enviroments';
import { CreateAdminCinemaDto } from '../../../models/dto/sysadmin/create-admin-cinema/create-admin-cinema-dto';

@Injectable({
  providedIn: 'root',
})
export class CreateAdminCinemaService {
  private apiUrl = `${environment.apiBaseUrl}/admin-cinema`;

  constructor(private http: HttpClient) {}

  createAdminCinema(adminData: CreateAdminCinemaDto): Observable<any> {
    const formData = new FormData();
    formData.append('idRol', adminData.idRol.toString());
    formData.append('nombreCompleto', adminData.nombreCompleto);
    formData.append('tipoUsuario', adminData.tipoUsuario);
    formData.append('usuario', adminData.usuario);
    formData.append('password', adminData.password);
    formData.append('correo', adminData.correo);
    formData.append('telefono', adminData.telefono);
    if (adminData.foto) {
      formData.append('foto', adminData.foto, adminData.foto.name);
    }
    return this.http.post(this.apiUrl, formData);
  }
}
