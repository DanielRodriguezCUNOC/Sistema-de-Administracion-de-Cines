import { Component, inject } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { LoginService } from '../../../services/login/login.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { UsuarioLoginDTO } from '../../../models/dto/login/usuario-login-dto';

@Component({
  selector: 'app-login',
  imports: [RouterModule, ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
})
export class LoginComponent {
  loginForm: FormGroup;
  errorMessage: string = '';
  isLoading: boolean = false;

  constructor(private fb: FormBuilder, private loginService: LoginService, private router: Router) {
    this.loginForm = this.fb.group({
      usuario: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  submit(): void {
    if (this.loginForm.invalid) {
      this.errorMessage = 'Por favor, complete todos los campos.';
      return;
    }
    this.isLoading = true;
    this.errorMessage = '';
    const usuarioLoginDto = new UsuarioLoginDTO(
      this.loginForm.value.usuario,
      this.loginForm.value.password
    );
    this.loginService
      .autenticacionBackend(usuarioLoginDto.usuario, usuarioLoginDto.password)
      .subscribe({
        next: (user) => {
          //*Redirigir al dashboard del usuario
          this.redirectDashboard(user.idRol);
        },
        error: (err) => {
          this.isLoading = false;

          if (err.status === 401) {
            this.errorMessage = 'Credenciales inválidas. Por favor, inténtelo de nuevo.';
          } else if (err.status === 0) {
            this.errorMessage = 'Error de conexión. Por favor, verifique su conexión a Internet.';
          } else if (err.status === 500) {
            this.errorMessage = 'Error interno del servidor. Por favor, inténtelo más tarde.';
          } else {
            this.errorMessage = 'Ocurrió un error inesperado. Por favor, inténtelo de nuevo.';
          }
        },
        complete: () => {
          this.isLoading = false;
        },
      });
  }

  //* Metodo para redirigir al dashboard segun el rol del usuario

  redirectDashboard(rol: number): void {
    switch (rol) {
      case 1:
        this.router.navigateByUrl('/admin-system');
        break;
      case 2:
        this.router.navigateByUrl('/admin-cine');
        break;
      case 3:
        this.router.navigateByUrl('/user-special');
        break;
      case 4:
        this.router.navigateByUrl('/user');
        break;
      default:
        this.router.navigateByUrl('/login');
    }
  }
}
