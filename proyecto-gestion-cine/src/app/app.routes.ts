import { Routes } from '@angular/router';
import { HomePageComponent } from '../pages/home-page.component/home-page.component';
import { LoginComponent } from '../components/login.component/login.component';
import { UserDashboardPageComponent } from '../pages/user-dashboard-page.component/user-dashboard-page.component';

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
    path: 'user-dashboard',
    component: UserDashboardPageComponent,
  },
];
