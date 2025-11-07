export interface CreateUserDto {
  nombreCompleto: string;
  tipoUsuario: string;
  usuario: string;
  password: string;
  correo: string;
  telefono: string;
  foto?: File;
}
