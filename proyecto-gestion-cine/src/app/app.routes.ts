import { Routes } from '@angular/router';
import { HomePageComponent } from '../pages/home-page.component/home-page.component';
import { UserDashboardPageComponent } from '../pages/user-dashboard-page.component/user-dashboard-page.component';
import { AdminCineDashboardComponent } from '../components/users/admin-cine-dashboard.component/admin-cine-dashboard.component';
import { UserSpecialDashboardComponent } from '../components/users/user-special-dashboard.component/user-special-dashboard.component';
import { authGuard } from '../guards/auth-guard';
import { LoginComponent } from '../components/login/login.component/login.component';
import { ShowCinemaComponent } from '../components/cinema/show-cinema.component/show-cinema.component';
import { UserSpecialDashboardPageComponent } from '../pages/user-special-dashboard-page.component/user-special-dashboard-page.component';
import { AdminSystemDashboardComponent } from '../components/users/admin-system-dashboard.component/admin-system-dashboard.component';
import { CinemaAdminDashboardPageComponent } from '../pages/cinema-admin-dashboard-page.component/cinema-admin-dashboard-page.component';
import { SystemAdminDashboardPageComponent } from '../pages/system-admin-dashboard-page.component/system-admin-dashboard-page.component';
import { UserDashboardComponent } from '../components/users/user-dashboard.component/user-dashboard.component';
import { LoginPageComponent } from '../pages/login-page.component/login-page.component';
import { authSysadminGuard } from '../guards/auth-sysadmin-guard';
import { authCinemaAdminGuard } from '../guards/auth-cinema-admin-guard';
import { authSpecialUserGuard } from '../guards/auth-special-user-guard';
import { authNormalUserGuard } from '../guards/auth-normal-user-guard';
import { AccessDeniedComponent } from '../components/access-denied/access-denied.component/access-denied.component';

export const routes: Routes = [
  {
    path: '',
    component: HomePageComponent,
  },
  {
    path: 'login',
    component: LoginPageComponent,

    children: [
      {
        path: 'form',
        component: LoginComponent,
      },
      {
        path: '',
        redirectTo: 'form',
        pathMatch: 'full',
      },
    ],
  },
  {
    path: 'user',
    component: UserDashboardPageComponent,
    canActivate: [authGuard],
    canActivateChild: [authGuard, authNormalUserGuard],
    children: [
      {
        path: 'dashboard',
        component: UserDashboardComponent,
      },
      {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full',
      },
    ],
  },
  {
    path: 'user-special',
    component: UserSpecialDashboardPageComponent,
    canActivate: [authGuard],
    canActivateChild: [authGuard, authSpecialUserGuard],

    children: [
      {
        path: 'dashboard',
        component: UserSpecialDashboardComponent,
      },
      {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full',
      },
    ],
  },

  {
    path: 'admin-cine',
    component: CinemaAdminDashboardPageComponent,
    canActivate: [authGuard],
    canActivateChild: [authGuard, authCinemaAdminGuard],

    children: [
      {
        path: 'dashboard',
        component: AdminCineDashboardComponent,
      },
      {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full',
      },
    ],
  },

  {
    path: 'admin-system',
    component: SystemAdminDashboardPageComponent,
    canActivate: [authGuard],
    canActivateChild: [authGuard, authSysadminGuard],

    children: [
      {
        path: '',
        component: AdminSystemDashboardComponent,
      },
      {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full',
      },
    ],
  },

  {
    path: 'show-cinemas',
    component: ShowCinemaComponent,
    canActivate: [authGuard],
  },

  {
    path: 'access-denied',
    component: AccessDeniedComponent,
  },
];
