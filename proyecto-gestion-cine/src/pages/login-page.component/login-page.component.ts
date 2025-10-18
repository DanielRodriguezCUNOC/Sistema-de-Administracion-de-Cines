import { Component } from '@angular/core';
import { LoginComponent } from '../../components/login/login.component/login.component';

@Component({
  selector: 'app-login-page',
  imports: [LoginComponent],
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss'],
})
export class LoginPageComponent {}
