import { Component } from '@angular/core';
import { PageLayoutComponent } from '../../../components/share/page-layout.component/page-layout.component';
import { RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-admin-system-dashboard.component',
  imports: [RouterLink],
  templateUrl: './admin-system-dashboard.component.html',
  styleUrl: './admin-system-dashboard.component.scss',
})
export class AdminSystemDashboardComponent {}
