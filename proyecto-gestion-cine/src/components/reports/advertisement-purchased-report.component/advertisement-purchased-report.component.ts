import { Component } from '@angular/core';
import { FormGroup, FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { PurchasedAdvertisementResponseDTO } from '../../../models/dto/sysadmin/purchased-advertisement-report/purchased-advertisement-response-dto';
import { PurchasedAdvertisementReportService } from '../../../services/sysadmin/reports/purchased-advertisement-report-service.service';
import { CommonModule } from '@angular/common';
import { PurchasedAdvertisementDTO } from '../../../models/dto/sysadmin/purchased-advertisement-report/purchased-advertisement-dto';
import { SharePopupComponent } from '../../../shared/share-popup.component/share-popup.component';

@Component({
  selector: 'app-advertisement-purchased-report',
  imports: [ReactiveFormsModule, CommonModule, SharePopupComponent],
  templateUrl: './advertisement-purchased-report.component.html',
  styleUrl: './advertisement-purchased-report.component.scss',
})
export class AdvertisementPurchasedReportComponent {
  reportForm: FormGroup;
  report: PurchasedAdvertisementDTO[] = [];
  isLoading = false;
  errorMessage: string | null = null;
  tiposAnuncios: string[] = [
    'Anuncio de texto',
    'Anuncio de texto e imagen',
    'Anuncio de video y texto',
  ];
  private offset = 0;
  private limit = 2;
  hayMasAnuncios = false;

  constructor(private fb: FormBuilder, private service: PurchasedAdvertisementReportService) {
    this.reportForm = this.fb.group({
      fechaInicio: [''],
      fechaFin: [''],
      tipoAnuncio: [''],
    });
  }

  generateReport(): void {
    this.errorMessage = null;
    this.isLoading = true;
    this.offset = 0; // Reiniciar el offset al generar un nuevo reporte
    this.report = []; // Limpiar el reporte anterior

    const { fechaInicio, fechaFin, tipoAnuncio } = this.reportForm.value;

    const startDate: string | null = fechaInicio?.toString().trim() || null;
    const endDate: string | null = fechaFin?.toString().trim() || null;
    const adType: string | null = tipoAnuncio?.toString().trim() || null;

    this.service.generateReport(startDate, endDate, adType, this.offset, this.limit).subscribe({
      next: (data: PurchasedAdvertisementResponseDTO) => {
        const nuevosAnuncios = data.purchasedAdvertisements || [];
        this.report = nuevosAnuncios;
        this.offset += nuevosAnuncios.length;
        this.hayMasAnuncios = nuevosAnuncios.length === this.limit;
        this.isLoading = false;
      },
      error: () => {
        this.errorMessage =
          'Error al generar el informe de anuncios comprados. Por favor, inténtelo de nuevo más tarde.';
        this.isLoading = false;
      },
    });
  }

  loadMore(): void {
    this.isLoading = true;
    const { fechaInicio, fechaFin, tipoAnuncio } = this.reportForm.value;
    const startDate: string | null = fechaInicio?.toString().trim() || null;
    const endDate: string | null = fechaFin?.toString().trim() || null;
    const adType: string | null = tipoAnuncio?.toString().trim() || null;

    this.offset += this.limit;

    this.service.generateReport(startDate, endDate, adType, this.offset, this.limit).subscribe({
      next: (data: PurchasedAdvertisementResponseDTO) => {
        const nuevosAnuncios = data.purchasedAdvertisements || [];
        this.report.push(...nuevosAnuncios);
        this.offset += nuevosAnuncios.length;
        this.hayMasAnuncios = nuevosAnuncios.length === this.limit;
        this.isLoading = false;
      },
      error: () => {
        this.errorMessage =
          'Error al cargar más anuncios comprados. Por favor, inténtelo de nuevo más tarde.';
        this.isLoading = false;
      },
    });
  }
}
