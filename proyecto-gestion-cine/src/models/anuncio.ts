export interface Anuncio {
  idAnuncio: number;
  idUsuario: number;
  idConfiguracion: number;
  nombreAnuncio: string;
  fechaCompra: string;
  fechaCaducidad: string;
  texto: string;
  imagen: string;
  linkVideo: string;
  estado: string;
}
