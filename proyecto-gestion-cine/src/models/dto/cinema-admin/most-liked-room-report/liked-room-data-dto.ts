import { LikedDataDTO } from './liked-data-dto';

export interface LikedRoomDataDTO {
  idSala: number;
  nombreSala: string;
  cantidadValoraciones: number;
  promedioValoraciones: number;
  valoraciones: LikedDataDTO[];
}
