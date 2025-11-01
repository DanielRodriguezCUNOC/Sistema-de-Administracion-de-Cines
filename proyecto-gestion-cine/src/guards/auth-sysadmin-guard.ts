import { Inject } from '@angular/core';
import { CanActivateFn, Router, UrlTree } from '@angular/router';

export const authSysadminGuard: CanActivateFn = (route, state) => {
  const router = Inject(Router);

  // *obtiene el usuario actual del localStorage
  const usuarioActual = localStorage.getItem('usuarioActual');

  // Si no hay usuario en el localStorage, denegar acceso
  if (!usuarioActual) {
    return router.createUrlTree(['/login']);
  } else {
    const usuario = JSON.parse(usuarioActual);

    // *Verifica si el rol del usuario es 1 (superadministrador)
    if (usuario.idRol === 1) {
      return true;
    }

    return router.createUrlTree(['/access-denied']);
  }
};
