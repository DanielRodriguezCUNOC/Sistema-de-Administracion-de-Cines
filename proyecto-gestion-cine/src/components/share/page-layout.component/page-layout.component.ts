import { Component } from '@angular/core';
import { FooterComponent } from '../footer.component/footer.component';
import { NavbarComponent } from '../navbar.component/navbar.component';
import { AdvertisementComponent } from '../../advertisements/advertisement.component/advertisement.component';

@Component({
  selector: 'app-page-layout',
  imports: [FooterComponent, NavbarComponent, AdvertisementComponent],
  templateUrl: './page-layout.component.html',
  styleUrl: './page-layout.component.scss',
})
export class PageLayoutComponent {}
