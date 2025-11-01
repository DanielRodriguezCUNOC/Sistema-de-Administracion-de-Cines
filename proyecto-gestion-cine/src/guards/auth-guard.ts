import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const authGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);

  const usuarioLoggedIn = localStorage.getItem('usuarioActual');

  // *Si el usuario está autenticado, permite el acceso, de lo contrario redirige al login
  return usuarioLoggedIn != null ? true : router.createUrlTree(['/login']);
};
