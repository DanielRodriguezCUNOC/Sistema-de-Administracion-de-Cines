import { Component } from '@angular/core';
import { MostPopularRoomReportService } from '../../../services/sysadmin/reports/most-popular-room-report-service.service';
import { MostPopularRoomReportResponseDTO } from '../../../models/dto/sysadmin/most-popular-room-report/most-popular-room-report-response-dto';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-most-popular-room-report.component',
  imports: [ReactiveFormsModule],
  templateUrl: './most-popular-room-report.component.html',
  styleUrl: './most-popular-room-report.component.scss',
})
export class MostPopularRoomReportComponent {
  reportForm: FormGroup;
  report: MostPopularRoomReportResponseDTO | null = null;
  isLoading = false;
  errorMessage: string | null = null;

  constructor(private fb: FormBuilder, private service: MostPopularRoomReportService) {
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

    this.service.generateReport(startDate, endDate).subscribe({
      next: (data: MostPopularRoomReportResponseDTO) => {
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
