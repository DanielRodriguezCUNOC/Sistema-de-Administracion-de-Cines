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
          //Redirigir al dashboard del usuario
          this.router.navigateByUrl('user');
          console.log('Usuario autenticado:', user);
        },
        error: (err) => {
          this.isLoading = false;
          console.error('Error en la autenticación:', err);

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
}
