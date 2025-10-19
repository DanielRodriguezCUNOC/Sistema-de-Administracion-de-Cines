import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { PageLayoutComponent } from '../../components/share/page-layout.component/page-layout.component';

@Component({
  selector: 'app-user-special-dashboard-page.component',
  imports: [RouterOutlet, PageLayoutComponent],
  templateUrl: './user-special-dashboard-page.component.html',
  styleUrl: './user-special-dashboard-page.component.scss',
})
export class UserSpecialDashboardPageComponent {}
