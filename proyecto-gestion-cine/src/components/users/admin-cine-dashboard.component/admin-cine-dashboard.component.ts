import { Component } from '@angular/core';
import { PageLayoutComponent } from '../../share/page-layout.component/page-layout.component';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-admin-cine-dashboard',
  imports: [RouterLink],
  templateUrl: './admin-cine-dashboard.component.html',
  styleUrl: './admin-cine-dashboard.component.scss',
})
export class AdminCineDashboardComponent {}
