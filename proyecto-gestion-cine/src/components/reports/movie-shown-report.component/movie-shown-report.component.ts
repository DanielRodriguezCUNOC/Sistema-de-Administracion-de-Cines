import { Component } from '@angular/core';
import { ProyectedMoviesResponseReportDTO } from '../../../models/dto/cinema-admin/proyected-movies-report/proyected-movies-response-report-dto';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ProyectedMovieReportService } from '../../../services/cinema-admin/reports/proyected-movie-report-service.service';

@Component({
  selector: 'app-movie-shown-report.component',
  imports: [],
  templateUrl: './movie-shown-report.component.html',
  styleUrl: './movie-shown-report.component.scss',
})
export class MovieShownReportComponent {
  reportForm: FormGroup;
  report: ProyectedMoviesResponseReportDTO | null = null;
  isLoading: boolean = false;
  errorMessage: string = '';

  constructor(private fb: FormBuilder, private profitReportService: ProyectedMovieReportService) {
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

    this.profitReportService.generateReport(fechaInicio, fechaFin).subscribe({
      next: (data: ProyectedMoviesResponseReportDTO) => {
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
