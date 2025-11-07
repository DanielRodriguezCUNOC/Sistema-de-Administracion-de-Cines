import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AdvertiserProfitReportResponseDTO } from '../../../models/dto/sysadmin/advertiser-profit-report/advertiser-profit-report-response-dto';
import { AdvertiserProfitReportService } from '../../../services/sysadmin/reports/advertiser-profit-report-service.service';
import { CommonModule } from '@angular/common';
import { SharePopupComponent } from '../../../shared/share-popup.component/share-popup.component';

@Component({
  selector: 'app-advertiser-profit-report.component',
  imports: [ReactiveFormsModule, CommonModule, SharePopupComponent],
  templateUrl: './advertiser-profit-report.component.html',
  styleUrl: './advertiser-profit-report.component.scss',
})
export class AdvertiserProfitReportComponent {
  reportForm: FormGroup;
  report: AdvertiserProfitReportResponseDTO | null = null;
  isLoading = false;
  infoMessage: string | null = null;
  errorMessage: string | null = null;

  constructor(private fb: FormBuilder, private service: AdvertiserProfitReportService) {
    this.reportForm = this.fb.group({
      fechaInicio: [''],
      fechaFin: [''],
      nombreAnunciante: [''],
    });
  }

  generateReport(): void {
    if (this.reportForm.invalid) {
      this.infoMessage = 'Por favor complete todos los campos requeridos.';
      return;
    }
    this.isLoading = true;
    this.infoMessage = '';

    const { fechaInicio, fechaFin, nombreAnunciante } = this.reportForm.value;

    const startDate: string | null = fechaInicio?.toString().trim() || null;
    const endDate: string | null = fechaFin?.toString().trim() || null;
    const advertiserName: string | null = nombreAnunciante?.toString().trim() || null;

    this.service.generateReport(startDate, endDate, advertiserName).subscribe({
      next: (data: AdvertiserProfitReportResponseDTO) => {
        this.report = data;
        this.isLoading = false;
        this.infoMessage = 'Informe generado con éxito.';
      },
      error: (error) => {
        this.errorMessage = error.message || 'Error al generar el informe.';
        this.isLoading = false;
      },
    });
  }
}
