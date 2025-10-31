import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const authGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);

  // *Simulación de verificación de autenticación
  const usuarioLoggedIn = localStorage.getItem('usuarioActual');

  // *Si el usuario está autenticado, permite el acceso, de lo contrario redirige al login
  if (usuarioLoggedIn != null) {
    return true;
  } else {
    router.navigateByUrl('/login');
    return false;
  }
};
