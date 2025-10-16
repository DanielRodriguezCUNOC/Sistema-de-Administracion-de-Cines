import { Component } from '@angular/core';
import { PageLayoutComponent } from '../page-layout.component/page-layout.component';

@Component({
  selector: 'app-user-dashboard-page.component',
  imports: [PageLayoutComponent],
  templateUrl: './user-dashboard-page.component.html',
  styleUrl: './user-dashboard-page.component.scss',
})
export class UserDashboardPageComponent {
  userName = 'Daniel Rodriguez';
}
