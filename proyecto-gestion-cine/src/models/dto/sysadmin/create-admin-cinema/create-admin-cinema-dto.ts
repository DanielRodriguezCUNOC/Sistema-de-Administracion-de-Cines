export interface CreateAdminCinemaDto {
  idRol: number;
  nombreCompleto: string;
  tipoUsuario: string;
  usuario: string;
  password: string;
  correo: string;
  telefono: string;
  foto: File | null;
}
