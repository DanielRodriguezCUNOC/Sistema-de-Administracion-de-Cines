import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const authSpecialUserGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  // *Obtiene el usuario actual del localStorage
  const usuarioActual = localStorage.getItem('usuarioActual');
  if (!usuarioActual) {
    return router.createUrlTree(['/login']);
  }
  const usuario = JSON.parse(usuarioActual);

  return usuario.idRol === 3 ? true : router.createUrlTree(['/access-denied']);
};
