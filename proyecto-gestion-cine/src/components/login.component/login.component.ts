import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../../services/login.service';
@Component({
  selector: 'app-login',
  imports: [],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
})
export class LoginComponent {
  email: string = '';
  password: string = '';

  constructor(private loginService: LoginService, private router: Router) {}

  login() {
    const success = this.loginService.login(this.email, this.password);
    if (success) {
      this.router.navigate(['/movies']); // Redirige a películas
    } else {
      alert('Correo o contraseña incorrectos');
    }
  }
}
