import { AfterViewInit, Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLinkActive, RouterLink, Router } from '@angular/router';
import { MasterLoginService } from '../../../services/masterlogin/master';

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
  }

  readDataLoggedIn() {
    this.isLoggedIn = this.masterLoginService.isLoggedIn();
  }

  logout() {
    this.masterLoginService.setLogout();
    this.router.navigateByUrl('/login');
  }
}
