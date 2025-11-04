import { Component } from '@angular/core';
import { SoldTicketResponseReportDTO } from '../../../models/dto/cinema-admin/sold-ticket-report/sold-ticket-response-report-dto';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { TicketSoldReportService } from '../../../services/cinema-admin/reports/ticket-sold-report-service.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-ticket-sold-report.component',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './ticket-sold-report.component.html',
  styleUrl: './ticket-sold-report.component.scss',
})
export class TicketSoldReportComponent {
  reportForm: FormGroup;
  report: SoldTicketResponseReportDTO | null = null;
  isLoading: boolean = false;
  errorMessage: string = '';

  constructor(private fb: FormBuilder, private service: TicketSoldReportService) {
    const today = new Date();
    const firstDayOfMonth = new Date(today.getFullYear(), today.getMonth(), 1);

    this.reportForm = this.fb.group({
      fechaInicio: [this.formatDate(firstDayOfMonth), Validators.required],
      fechaFin: [this.formatDate(today), Validators.required],
      nombreSala: [''],
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

    const { fechaInicio, fechaFin, nombreSala } = this.reportForm.value;

    this.service.generateReport(fechaInicio, fechaFin, nombreSala).subscribe({
      next: (data: SoldTicketResponseReportDTO) => {
        this.report = data;
        this.isLoading = false;
      },
      error: (error) => {
        this.errorMessage = 'Error al generar el informe. Por favor, inténtelo de nuevo más tarde.';
        this.isLoading = false;
      },
    });
  }
}
