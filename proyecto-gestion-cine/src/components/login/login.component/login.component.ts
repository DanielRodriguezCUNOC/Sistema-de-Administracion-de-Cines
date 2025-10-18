import { Component, inject } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { LoginService } from '../../../services/login/login.service';
import { FormGroup, ReactiveFormsModule } from '@angular/forms';
import { UsuarioLoginDTO } from '../../../services/users/usuario-login-dto';

@Component({
  selector: 'app-login',
  imports: [RouterModule, ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
})
export class LoginComponent {
  router = inject(Router); // Para realizar redirecciones
  loginForm: FormGroup = new FormGroup({});

  usuarioIngresado = new UsuarioLoginDTO('', '');

  email: string = '';
  password: string = '';

  constructor(private loginService: LoginService) {}

  submit(): void {
    if (this.loginForm.valid) {
      this.usuarioIngresado = this.loginForm.value as UsuarioLoginDTO;

      console.log(
        'Usuario ingresado:',
        this.usuarioIngresado.email + ' ' + this.usuarioIngresado.password
      );
      //En lugar de email y password, usar el dto

      /* this.loginService.validarUsuario(this.email, this.password).subscribe({
     next:() => this.reset(), 
     
     error: (error: any) => console.error('Error al validar usuario', error)
     
     }); */

      // Simular diferentes roles de usuario para la demostración
      localStorage.setItem('adminCine', 'Danoo');

      //Guardar la misma clave que en el navbar
      // localStorage.setItem('angularUserCinema', 'Danoo');

      // Redirigir a la ruta protegida después del inicio de sesión exitoso
      this.router.navigateByUrl('user-special');
    }
  }
}
