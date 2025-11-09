import { LikedDataDTO } from './liked-data-dto';

export interface LikedRoomDataDTO {
  idSala: number;
  nombreSala: string;
  cantidadValoraciones: number;
  promedioValoracion: number;
  valoraciones: LikedDataDTO[];
}
