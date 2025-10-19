import { Component } from '@angular/core';
import { PageLayoutComponent } from '../../components/share/page-layout.component/page-layout.component';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-system-admin-dashboard-page.component',
  imports: [PageLayoutComponent, RouterModule],
  templateUrl: './system-admin-dashboard-page.component.html',
  styleUrl: './system-admin-dashboard-page.component.scss',
})
export class SystemAdminDashboardPageComponent {}
