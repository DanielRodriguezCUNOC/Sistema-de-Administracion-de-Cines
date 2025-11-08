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
import { ProfitReportComponent } from '../components/reports/profit-report.component/profit-report.component';
import { AdvertisementPurchasedReportComponent } from '../components/reports/advertisement-purchased-report.component/advertisement-purchased-report.component';
import { SelectAdminReportComponent } from '../components/sysadmin/select-admin-report.component/select-admin-report.component';
import { ComentRoomReportComponent } from '../components/reports/coment-room-report.component/coment-room-report.component';
import { MovieShownReportComponent } from '../components/reports/movie-shown-report.component/movie-shown-report.component';
import { MostLikedRoomReportComponent } from '../components/reports/most-liked-room-report.component/most-liked-room-report.component';
import { TicketSoldReportComponent } from '../components/reports/ticket-sold-report.component/ticket-sold-report.component';
import { SelectAdminCinemaReportComponent } from '../components/cinema-admin/select-admin-cinema-report.component/select-admin-cinema-report.component';
import { CreateUserPageComponent } from '../pages/create-user-page.component/create-user-page.component';
import { CreateUserComponent } from '../components/users/create-user.component/create-user.component';
import { MostCommentedRoomReportComponent } from '../components/reports/most-commented-room-report.component/most-commented-room-report.component';
import { MostPopularRoomReportComponent } from '../components/reports/most-popular-room-report.component/most-popular-room-report.component';
import { AdvertiserProfitReportComponent } from '../components/reports/advertiser-profit-report.component/advertiser-profit-report.component';

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
    path: 'register',
    component: CreateUserPageComponent,

    children: [
      {
        path: 'form',
        component: CreateUserComponent,
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
        path: 'dashboard-normal-user',
        component: UserDashboardComponent,
      },
      {
        path: '',
        redirectTo: 'dashboard-normal-user',
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
        path: 'dashboard-special-user',
        component: UserSpecialDashboardComponent,
      },
      {
        path: '',
        redirectTo: 'dashboard-special-user',
        pathMatch: 'full',
      },
    ],
  },

  {
    path: 'admin-cinema',
    component: CinemaAdminDashboardPageComponent,
    canActivate: [authGuard],
    canActivateChild: [authGuard, authCinemaAdminGuard],

    children: [
      {
        path: 'dashboard-admin-cinema',
        component: AdminCineDashboardComponent,
      },
      {
        path: '',
        redirectTo: 'dashboard-admin-cinema',
        pathMatch: 'full',
      },
      {
        path: 'dashboard-reports',
        component: SelectAdminCinemaReportComponent,
      },
      {
        path: 'reports',
        children: [
          {
            path: 'comments-report',
            component: ComentRoomReportComponent,
          },
          {
            path: 'proyected-movies-report',
            component: MovieShownReportComponent,
          },
          {
            path: 'top-liked-rooms',
            component: MostLikedRoomReportComponent,
          },
          {
            path: 'sold-tickets-report',
            component: TicketSoldReportComponent,
          },
        ],
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
        path: 'dashboard-sysadmin',
        component: AdminSystemDashboardComponent,
      },
      {
        path: '',
        redirectTo: 'dashboard-sysadmin',
        pathMatch: 'full',
      },
      {
        path: 'dashboard-reports',
        component: SelectAdminReportComponent,
      },
      {
        path: 'reports',
        children: [
          {
            path: 'profit-report',
            component: ProfitReportComponent,
          },
          {
            path: 'advertisement-purchased-report',
            component: AdvertisementPurchasedReportComponent,
          },
          {
            path: 'most-commented-room-report',
            component: MostCommentedRoomReportComponent,
          },
          {
            path: 'most-popular-room-report',
            component: MostPopularRoomReportComponent,
          },
          {
            path: 'advertiser-profit-report',
            component: AdvertiserProfitReportComponent,
          },
        ],
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
  {
    path: 'register',
    component: CreateUserPageComponent,

    children: [
      {
        path: 'form',
        component: CreateUserComponent,
      },
      {
        path: '',
        redirectTo: 'form',
        pathMatch: 'full',
      },
    ],
  },
];
