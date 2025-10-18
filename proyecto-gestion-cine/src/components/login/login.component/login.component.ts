import { Component, inject } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { LoginService } from '../../../services/login/login.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { UsuarioLoginDTO } from '../../../services/users/usuario-login-dto';

@Component({
  selector: 'app-login',
  imports: [RouterModule, ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
})
export class LoginComponent {
  loginForm: FormGroup;

  constructor(private fb: FormBuilder, private loginService: LoginService, private router: Router) {
    this.loginForm = this.fb.group({
      usuario: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  submit(): void {
    if (this.loginForm.invalid) return;

    const usuarioLoginDto = new UsuarioLoginDTO(
      this.loginForm.value.usuario,
      this.loginForm.value.password
    );
    this.loginService
      .autenticacionBackend(usuarioLoginDto.usuario, usuarioLoginDto.password)
      .subscribe({
        next: (user) => {
          //Redirigir al dashboard del usuario
          this.router.navigateByUrl('user-special');
        },
        error: (err) => {
          console.error('Error en la autenticación:', err);
        },
      });
  }
}
