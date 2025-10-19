import { Component } from '@angular/core';
import { PageLayoutComponent } from '../../components/share/page-layout.component/page-layout.component';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-cinema-admin-dashboard-page.component',
  imports: [PageLayoutComponent, RouterOutlet],
  templateUrl: './cinema-admin-dashboard-page.component.html',
  styleUrl: './cinema-admin-dashboard-page.component.scss',
})
export class CinemaAdminDashboardPageComponent {}
