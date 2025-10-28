import { AdvertisementProfitReportDTO } from './advertisement-profit-report-dto';
import { CinemaCostReportDTO } from './cinema-cost-report-dto';

export interface ProfitReportResponseDTO {
  costoCinema: CinemaCostReportDTO[];
  advertisementPaymentAmount: AdvertisementProfitReportDTO[];
  amountAdBlock: AdvertisementProfitReportDTO[];
  totalProfit: number;
  totalRevenue: number;
  totalExpenses: number;
}
