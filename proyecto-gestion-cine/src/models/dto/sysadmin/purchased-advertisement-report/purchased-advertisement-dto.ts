export interface PurchasedAdvertisementDTO {
  idAnuncio: number;
  idUsuario: number;
  nombreAnuncio: string;
  fechaPago: Date;
  montoPago: number;
  tipoAnuncio: string;
}
