import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AdvertiserProfitReportResponseDTO } from '../../../models/dto/sysadmin/advertiser-profit-report/advertiser-profit-report-response-dto';
import { advertiserProfitReportService } from '../../../services/sysadmin/reports/advertiser-profit-report-service.service';

@Component({
  selector: 'app-advertiser-profit-report.component',
  imports: [],
  templateUrl: './advertiser-profit-report.component.html',
  styleUrl: './advertiser-profit-report.component.scss',
})
export class AdvertiserProfitReportComponent {
  reportForm: FormGroup;
  report: AdvertiserProfitReportResponseDTO | null = null;
  isLoading = false;
  errorMessage: string | null = null;

  constructor(
    private fb: FormBuilder,
    private advertiserProfitReportService: advertiserProfitReportService
  ) {
    const today = new Date();
    const firstDayOfMonth = new Date(today.getFullYear(), today.getMonth(), 1);
    this.reportForm = this.fb.group({
      fechaInicio: [this.formatDate(firstDayOfMonth), Validators.required],
      fechaFin: [this.formatDate(today), Validators.required],
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

    const { fechaInicio, fechaFin } = this.reportForm.value;

    this.advertiserProfitReportService.generateReport(fechaInicio, fechaFin).subscribe({
      next: (data: AdvertiserProfitReportResponseDTO) => {
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
