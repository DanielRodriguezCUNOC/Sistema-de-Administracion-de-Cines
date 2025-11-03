import { Component } from '@angular/core';

import { CommentedRoomResponseReportDTO } from '../../../models/dto/cinema-admin/commented-room-report/commented-room-response-report-dto';
import { CommentedRoomReportService } from '../../../services/cinema-admin/reports/comment-room-report-service.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-coment-room-report.component',
  imports: [],
  templateUrl: './coment-room-report.component.html',
  styleUrl: './coment-room-report.component.scss',
})
export class ComentRoomReportComponent {
  reportForm: FormGroup;
  report: CommentedRoomResponseReportDTO | null = null;
  isLoading = false;
  errorMessage: string | null = null;

  constructor(private fb: FormBuilder, private service: CommentedRoomReportService) {
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

    this.service.generateReport(fechaInicio, fechaFin).subscribe({
      next: (data: CommentedRoomResponseReportDTO) => {
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
