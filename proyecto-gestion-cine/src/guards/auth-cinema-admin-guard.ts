import { CanActivateFn } from '@angular/router';

export const authCinemaAdminGuard: CanActivateFn = (route, state) => {
  const usuarioActual = localStorage.getItem('usuarioActual');
  if (usuarioActual) {
    const usuario = JSON.parse(usuarioActual);
    // Verifica si el rol del usuario es 2 (administrador de cine)
    if (usuario.idRol === '2') {
      return true;
    }
  }
  // Si no es un administrador de cine, deniega el acceso
  return false;
};
