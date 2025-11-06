import { Component } from '@angular/core';
import { MostCommentedRoomReportResponseDTO } from '../../../models/dto/sysadmin/most-commented-room-report/most-commented-room-report-response-dto';
import { MostCommentedRoomReportService } from '../../../services/sysadmin/reports/most-commented-room-report-service.service';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-most-commented-room-report.component',
  imports: [ReactiveFormsModule],
  templateUrl: './most-commented-room-report.component.html',
  styleUrl: './most-commented-room-report.component.scss',
})
export class MostCommentedRoomReportComponent {
  reportForm: FormGroup;
  report: MostCommentedRoomReportResponseDTO | null = null;
  isLoading = false;
  errorMessage: string | null = null;

  constructor(private fb: FormBuilder, private service: MostCommentedRoomReportService) {
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
      next: (data: MostCommentedRoomReportResponseDTO) => {
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
