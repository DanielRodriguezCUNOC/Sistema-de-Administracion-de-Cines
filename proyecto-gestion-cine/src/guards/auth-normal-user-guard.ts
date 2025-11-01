import { Inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const authNormalUserGuard: CanActivateFn = (route, state) => {
  const router = Inject(Router);
  // *obtiene el usuario actual del localStorage
  const usuarioActual = localStorage.getItem('usuarioActual');
  if (!usuarioActual) {
    return router.createUrlTree(['/login']);
  }
  const usuario = JSON.parse(usuarioActual);

  // *Verifica si el rol del usuario es 4 (usuario normal)
  if (usuario.idRol === 4) {
    return true;
  }

  // *Si no es un usuario normal, deniega el acceso
  return router.createUrlTree(['/access-denied']);
};
