import { AdvertiserListDTO } from './advertiser-list';

export interface AdvertiserProfitReportResponseDTO {
  advertiserList: AdvertiserListDTO[];
  totalProfit: number;
}
