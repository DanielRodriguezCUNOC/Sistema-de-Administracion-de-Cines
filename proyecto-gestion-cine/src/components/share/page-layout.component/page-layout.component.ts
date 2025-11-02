import { Component, OnInit } from '@angular/core';
import { FooterComponent } from '../footer.component/footer.component';
import { NavbarComponent } from '../navbar.component/navbar.component';
import { AdvertisementComponent } from '../../advertisements/advertisement.component/advertisement.component';
import { LoginService } from '../../../services/login/login.service';

@Component({
  selector: 'app-page-layout',
  imports: [FooterComponent, NavbarComponent, AdvertisementComponent],
  templateUrl: './page-layout.component.html',
  styleUrl: './page-layout.component.scss',
})
export class PageLayoutComponent implements OnInit {
  isSysAdmin: boolean = false;
  currentUser: any;

  constructor(private loginService: LoginService) {}

  ngOnInit(): void {
    this.currentUser = this.loginService.currentUser;

    this.isSysAdmin = this.currentUser?.idRol === 1;
  }
}
