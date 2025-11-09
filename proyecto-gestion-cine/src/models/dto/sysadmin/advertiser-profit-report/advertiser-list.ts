import { PurchasedAdvertisement } from './purchased-advertisement';
export interface AdvertiserListDTO {
  idUsuario: number;
  nombreUsuario: string;
  purchasedAdvertisement: PurchasedAdvertisement[];
  totalPurchasedAmount: number;
}
