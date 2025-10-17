import { Component } from '@angular/core';
import { FooterComponent } from '../../components/footer.component/footer.component';
import { NavbarComponent } from '../../components/navbar.component/navbar.component';
import { AdvertisementComponent } from '../../components/advertisement.component/advertisement.component';

@Component({
  selector: 'app-page-layout',
  imports: [FooterComponent, NavbarComponent, AdvertisementComponent],
  templateUrl: './page-layout.component.html',
  styleUrl: './page-layout.component.scss',
})
export class PageLayoutComponent {}
