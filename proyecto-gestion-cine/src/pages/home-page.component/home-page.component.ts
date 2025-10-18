import { Component } from '@angular/core';
import { NavbarComponent } from '../../components/share/navbar.component/navbar.component';
import { FooterComponent } from '../../components/share/footer.component/footer.component';
import { PageLayoutComponent } from '../../components/share/page-layout.component/page-layout.component';

@Component({
  selector: 'app-home-page.component',
  imports: [PageLayoutComponent],
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss'],
})
export class HomePageComponent {}
