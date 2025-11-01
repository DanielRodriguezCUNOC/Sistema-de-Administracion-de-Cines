import { Inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const authCinemaAdminGuard: CanActivateFn = (route, state) => {
  const router = Inject(Router);
  // *obtiene el usuario actual del localStorage
  const usuarioActual = localStorage.getItem('usuarioActual');
  if (!usuarioActual) {
    return router.createUrlTree(['/login']);
  }
  const usuario = JSON.parse(usuarioActual);
  // Verifica si el rol del usuario es 2 (administrador de cine)
  if (usuario.idRol === 2) {
    return true;
  }
  // Si no es un administrador de cine, deniega el acceso
  return router.createUrlTree(['/access-denied']);
};
