import { Component } from '@angular/core';
import { ProyectedMoviesResponseReportDTO } from '../../../models/dto/cinema-admin/proyected-movies-report/proyected-movies-response-report-dto';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { ProyectedMovieReportService } from '../../../services/cinema-admin/reports/proyected-movie-report-service.service';
import { MovieProyectedRoomDTO } from '../../../models/dto/cinema-admin/proyected-movies-report/movie-proyected-room-dto';

@Component({
  selector: 'app-movie-shown-report.component',
  imports: [ReactiveFormsModule],
  templateUrl: './movie-shown-report.component.html',
  styleUrl: './movie-shown-report.component.scss',
})
export class MovieShownReportComponent {
  reportForm: FormGroup;
  peliculasProyectadas: MovieProyectedRoomDTO[] = [];
  errorMessage: string | null = null;
  isLoading = false;
  hayMasPeliculas = false;
  private offset = 0;
  private limit = 2;

  constructor(private fb: FormBuilder, private service: ProyectedMovieReportService) {
    const today = new Date();
    const firstDay = new Date(today.getFullYear(), today.getMonth(), 1);

    this.reportForm = this.fb.group({
      fechaInicio: [this.formatDate(firstDay), Validators.required],
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

    this.errorMessage = null;
    this.isLoading = true;
    this.offset = 0;
    this.peliculasProyectadas = [];

    const { fechaInicio, fechaFin, nombreSala } = this.reportForm.value;

    this.service.getMovies(fechaInicio, fechaFin, nombreSala, this.offset, this.limit).subscribe({
      next: (data: ProyectedMoviesResponseReportDTO) => {
        const nuevasPeliculas = data.peliculasProyectadas || [];
        this.peliculasProyectadas = nuevasPeliculas;
        this.offset += nuevasPeliculas.length;
        this.hayMasPeliculas = nuevasPeliculas.length === this.limit;
        this.isLoading = false;
      },
      error: () => {
        this.errorMessage = 'Error al generar el reporte. Por favor, inténtelo de nuevo.';
        this.isLoading = false;
      },
    });
  }

  loadMore(): void {
    this.isLoading = true;

    const { fechaInicio, fechaFin, nombreSala } = this.reportForm.value;
    this.service.getMovies(fechaInicio, fechaFin, nombreSala, this.offset, this.limit).subscribe({
      next: (data: ProyectedMoviesResponseReportDTO) => {
        const nuevasPeliculas = data.peliculasProyectadas || [];
        this.peliculasProyectadas.push(...nuevasPeliculas);
        this.offset += nuevasPeliculas.length;
        this.hayMasPeliculas = nuevasPeliculas.length === this.limit;
        this.isLoading = false;
      },
      error: () => {
        this.errorMessage = 'Error al cargar más películas.';
        this.isLoading = false;
      },
    });
  }
}
