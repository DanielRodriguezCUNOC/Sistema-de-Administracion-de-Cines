import { AdvertisementReportDTO } from '../profit-report/advertisement-report-dto';

export interface AdvertiserListDTO {
  idUsuario: number;
  nombreUsuario: string;
  purchasedAdvertisementList: AdvertisementReportDTO[];
  totalPurchasedAmount: number;
}
