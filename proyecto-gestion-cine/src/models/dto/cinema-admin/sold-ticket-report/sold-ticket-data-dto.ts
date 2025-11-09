import { UserDataDTO } from './user-data-dto';

export interface SoldTicketDataDTO {
  idSala: number;
  nombreSala: string;
  usuarios: UserDataDTO[];
}
