import { Component } from '@angular/core';
import { FormGroup, FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { PurchasedAdvertisementResponseDTO } from '../../../models/dto/sysadmin/purchased-advertisement-report/purchased-advertisement-response-dto';
import { PurchasedAdvertisementReportService } from '../../../services/sysadmin/reports/purchased-advertisement-report-service.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-advertisement-purchased-report.component',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './advertisement-purchased-report.component.html',
  styleUrl: './advertisement-purchased-report.component.scss',
})
export class AdvertisementPurchasedReportComponent {
  reportForm: FormGroup;
  report: PurchasedAdvertisementResponseDTO | null = null;
  isLoading = false;
  errorMessage: string | null = null;
  tiposAnuncios: string[] = [
    'Todo',
    'Anuncio de texto',
    'Anuncio de texto e imagen',
    'Anuncio de video y texto',
  ];

  constructor(
    private fb: FormBuilder,
    private purchasedAdvertisementReportService: PurchasedAdvertisementReportService
  ) {
    const today = new Date();
    const firstDayOfMonth = new Date(today.getFullYear(), today.getMonth(), 1);
    this.reportForm = this.fb.group({
      fechaInicio: [this.formatDate(firstDayOfMonth), Validators.required],
      fechaFin: [this.formatDate(today), Validators.required],
      tipoAnuncio: [''],
    });
  }

  formatDate(date: Date): string {
    return date.toISOString().split('T')[0];
  }

  generateReport(): void {
    if (this.reportForm.invalid) {
      this.errorMessage = 'Por favor complete todos los campos requeridos.';
      return;
    }
    this.isLoading = true;
    this.errorMessage = '';

    const { fechaInicio, fechaFin, tipoAnuncio } = this.reportForm.value;

    this.purchasedAdvertisementReportService
      .generateReport(fechaInicio, fechaFin, tipoAnuncio)
      .subscribe({
        next: (data: PurchasedAdvertisementResponseDTO) => {
          this.report = data;
          this.isLoading = false;
        },
        error: (error) => {
          this.errorMessage =
            'Error al generar el informe de anuncios comprados. Por favor, inténtelo de nuevo más tarde.';
          this.isLoading = false;
        },
      });
  }
}
