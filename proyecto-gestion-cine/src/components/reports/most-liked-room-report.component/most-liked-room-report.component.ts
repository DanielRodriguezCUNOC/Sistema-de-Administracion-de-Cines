import { Component } from '@angular/core';
import { MostLikedRoomResponseReportDTO } from '../../../models/dto/cinema-admin/most-liked-room-report/most-liked-room-response-report-dto';
import { MostLikedRoomReportService } from '../../../services/cinema-admin/reports/most-liked-room-report-service.service';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-most-liked-room-report.component',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './most-liked-room-report.component.html',
  styleUrl: './most-liked-room-report.component.scss',
})
export class MostLikedRoomReportComponent {
  reportForm: FormGroup;
  report: MostLikedRoomResponseReportDTO | null = null;
  isLoading: boolean = false;
  errorMessage: string = '';

  constructor(private fb: FormBuilder, private service: MostLikedRoomReportService) {
    const today = new Date();
    const firstDayOfMonth = new Date(today.getFullYear(), today.getMonth(), 1);

    this.reportForm = this.fb.group({
      fechaInicio: [''],
      fechaFin: [''],
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

    const startDate = fechaInicio || '';
    const endDate = fechaFin || '';

    this.service.generateReport(startDate, endDate, nombreSala).subscribe({
      next: (data: MostLikedRoomResponseReportDTO) => {
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
