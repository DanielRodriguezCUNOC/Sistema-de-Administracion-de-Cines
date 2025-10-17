import { AfterViewInit, Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLinkActive, RouterLink, Router } from '@angular/router';
import { MasterLoginService } from '../../services/masterlogin/master';

@Component({
  selector: 'app-navbar',
  imports: [CommonModule, RouterLinkActive, RouterLink],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss',
})
export class NavbarComponent {
  router = inject(Router);
  isLoggedIn = false;

  constructor(private masterLoginService: MasterLoginService) {
    this.readDataLoggedIn();
    this.masterLoginService.onLogin.subscribe((data) => {
      this.readDataLoggedIn();
    });
  }

  readDataLoggedIn() {
    const dataLogged = localStorage.getItem('adminCine');
    console.log('Data logged desde el navbar: ' + dataLogged);
    if (dataLogged != null) {
      this.isLoggedIn = true;
    }
  }

  logout() {
    this.isLoggedIn = false;
    localStorage.removeItem('adminCine');
    // Redirigir al login
    this.router.navigateByUrl('/login');
  }
}
