import { SoldTicketDataDTO } from './sold-ticket-data-dto';

export interface SoldTicketResponseReportDTO {
  boletosVendidos: SoldTicketDataDTO[];
  totalDinero: number;
}
