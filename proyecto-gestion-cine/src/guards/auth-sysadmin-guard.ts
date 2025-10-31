import { CanActivateFn } from '@angular/router';

export const authSysadminGuard: CanActivateFn = (route, state) => {
  // *obtiene el usuario actual del localStorage
  const usuarioActual = localStorage.getItem('usuarioActual');
  if (usuarioActual) {
    const usuario = JSON.parse(usuarioActual);

    // *Verifica si el rol del usuario es 1 (superadministrador)
    if (usuario.idRol === '1') {
      return true;
    }
  }

  // *Si no es un superadministrador, deniega el acceso
  return false;
};
