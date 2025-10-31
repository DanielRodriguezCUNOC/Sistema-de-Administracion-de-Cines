import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ProfitReportResponseDTO } from '../../../models/dto/sysadmin/profit-report/profit-report-response-dto';
import { ProfitReportService } from '../../../services/sysadmin/reports/profit-report-service.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-profit-report',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './profit-report.component.html',
  styleUrl: './profit-report.component.scss',
})
export class ProfitReportComponent implements OnInit {
  reportForm: FormGroup;
  report: ProfitReportResponseDTO | null = null;
  isLoading: boolean = false;
  errorMessage: string = '';

  constructor(private fb: FormBuilder, private profitReportService: ProfitReportService) {
    const today = new Date();
    const firstDay = new Date(today.getFullYear());

    this.reportForm = this.fb.group({
      fechaInicio: [this.formatDate(firstDay), Validators.required],
      fechaFin: [this.formatDate(today), Validators.required],
    });
  }

  ngOnInit(): void {
    this.generateReport();
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

    this.profitReportService.generateProfitReport(fechaInicio, fechaFin).subscribe({
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
  getTotalCinemaCosts(costos: number[]): number {
    return costos.reduce((sum, cost) => sum + cost, 0);
  }
}
