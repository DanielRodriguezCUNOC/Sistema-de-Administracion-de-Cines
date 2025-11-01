import { inject } from '@angular/core';
import { CanActivateFn, Router, UrlTree } from '@angular/router';

export const authSysadminGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);

  // *obtiene el usuario actual del localStorage
  const usuarioActual = localStorage.getItem('usuarioActual');

  // Si no hay usuario en el localStorage, denegar acceso
  if (!usuarioActual) {
    return router.createUrlTree(['/login']);
  } else {
    const usuario = JSON.parse(usuarioActual);
    return usuario.idRol === 1 ? true : router.createUrlTree(['/login']);
  }
};
