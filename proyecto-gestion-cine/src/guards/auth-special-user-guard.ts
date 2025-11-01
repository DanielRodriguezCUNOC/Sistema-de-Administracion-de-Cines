import { Inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const authSpecialUserGuard: CanActivateFn = (route, state) => {
  const router = Inject(Router);
  // *Obtiene el usuario actual del localStorage
  const usuarioActual = localStorage.getItem('usuarioActual');
  if (!usuarioActual) {
    return router.createUrlTree(['/login']);
  }
  const usuario = JSON.parse(usuarioActual);
  // *Verifica si el rol del usuario es 3 (usuario especial)
  if (usuario.idRol === 3) {
    return true;
  }
  // *Si no es un usuario especial, deniega el acceso
  return router.createUrlTree(['/access-denied']);
};
