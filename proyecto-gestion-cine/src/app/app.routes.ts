import { Routes } from '@angular/router';
import { HomePageComponent } from '../pages/home-page.component/home-page.component';
import { UserDashboardPageComponent } from '../pages/user-dashboard-page.component/user-dashboard-page.component';
import { AdminCineDashboardComponent } from '../pages/admin-cine-dashboard.component/admin-cine-dashboard.component';
import { AdminSystemDashboardComponent } from '../pages/admin-system-dashboard.component/admin-system-dashboard.component';
import { UserSpecialDashboardComponent } from '../pages/user-special-dashboard.component/user-special-dashboard.component';
import { authGuard } from './guards/auth-guard';
import { LoginComponent } from '../components/login/login.component/login.component';
import { ShowCinemaComponent } from '../components/cinema/show-cinema.component/show-cinema.component';

export const routes: Routes = [
  {
    path: '',
    component: HomePageComponent,
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  //Ruta de pruebas
  {
    path: 'user-special',
    component: UserSpecialDashboardComponent,
    canActivate: [authGuard],

    children: [],
  },

  {
    path: 'admin-cine',
    component: AdminCineDashboardComponent,
    canActivate: [authGuard],

    children: [],
  },

  {
    path: 'admin-system',
    component: AdminSystemDashboardComponent,
    canActivate: [authGuard],

    children: [],
  },

  {
    path: 'show-cinemas',
    component: ShowCinemaComponent,
    //canActivate: [authGuard],
  },
];
