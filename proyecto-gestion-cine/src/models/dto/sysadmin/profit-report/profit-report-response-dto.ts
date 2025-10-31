import { AdvertisementReportDTO } from './advertisement-report-dto';
import { CinemaCostReportDTO } from './cinema-cost-report-dto';

export interface ProfitReportResponseDTO {
  costoCinema: CinemaCostReportDTO[];
  advertisementPaymentAmount: AdvertisementReportDTO[];
  amountAdBlock: AdvertisementReportDTO[];
  totalProfit: number;
  totalRevenue: number;
  totalExpenses: number;
}
