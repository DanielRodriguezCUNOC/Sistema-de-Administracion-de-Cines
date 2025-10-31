import { SoldTicketDataDTO } from './sold-ticket-data-dto';

export interface SoldTicketResponseReportDTO {
  listadoVentas: SoldTicketDataDTO[];
  totalDineroRecaudado: number;
}
