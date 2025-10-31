import { CanActivateFn } from '@angular/router';

export const authNormalUserGuard: CanActivateFn = (route, state) => {
  // *obtiene el usuario actual del localStorage
  const usuarioActual = localStorage.getItem('usuarioActual');
  if (usuarioActual) {
    const usuario = JSON.parse(usuarioActual);

    // *Verifica si el rol del usuario es 4 (usuario normal)
    if (usuario.idRol === '4') {
      return true;
    }
  }
  // *Si no es un usuario normal, deniega el acceso
  return false;
};
