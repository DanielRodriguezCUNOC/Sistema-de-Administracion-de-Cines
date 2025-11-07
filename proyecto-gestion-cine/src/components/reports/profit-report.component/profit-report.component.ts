import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ProfitReportResponseDTO } from '../../../models/dto/sysadmin/profit-report/profit-report-response-dto';
import { ProfitReportService } from '../../../services/sysadmin/reports/profit-report-service.service';
import { CommonModule } from '@angular/common';
import { SharePopupComponent } from '../../../shared/share-popup.component/share-popup.component';

@Component({
  selector: 'app-profit-report',
  imports: [ReactiveFormsModule, CommonModule, SharePopupComponent],
  templateUrl: './profit-report.component.html',
  styleUrl: './profit-report.component.scss',
})
export class ProfitReportComponent {
  reportForm: FormGroup;
  report: ProfitReportResponseDTO | null = null;
  isLoading: boolean = false;
  errorMessage: string | null = null;

  constructor(private fb: FormBuilder, private profitReportService: ProfitReportService) {
    const today = new Date();
    const firstDayOfMonth = new Date(today.getFullYear(), today.getMonth(), 1);

    this.reportForm = this.fb.group({
      fechaInicio: [''],
      fechaFin: [''],
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

    const startDate = fechaInicio || '';
    const endDate = fechaFin || '';

    this.profitReportService.generateReport(startDate, endDate).subscribe({
      next: (data: ProfitReportResponseDTO) => {
        this.report = data;
        this.isLoading = false;
      },
      error: (error) => {
        this.errorMessage =
          'Error al generar el informe de ganancias. Por favor, inténtelo de nuevo más tarde.';
        this.isLoading = false;
      },
    });
  }
}
