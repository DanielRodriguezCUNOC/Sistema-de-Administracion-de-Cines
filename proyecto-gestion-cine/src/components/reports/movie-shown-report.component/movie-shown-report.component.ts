import { Component } from '@angular/core';
import { ProyectedMoviesResponseReportDTO } from '../../../models/dto/cinema-admin/proyected-movies-report/proyected-movies-response-report-dto';
import { FormGroup, FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { ProyectedMovieReportService } from '../../../services/cinema-admin/reports/proyected-movie-report-service.service';
import { MovieProyectedRoomDTO } from '../../../models/dto/cinema-admin/proyected-movies-report/movie-proyected-room-dto';
import { SharePopupComponent } from '../../../shared/share-popup.component/share-popup.component';

@Component({
  selector: 'app-movie-shown-report',
  imports: [ReactiveFormsModule, SharePopupComponent],
  templateUrl: './movie-shown-report.component.html',
  styleUrl: './movie-shown-report.component.scss',
})
export class MovieShownReportComponent {
  reportForm: FormGroup;
  report: MovieProyectedRoomDTO[] = [];
  infoMessage: string | null = null;
  popupTipo: 'error' | 'success' | 'info' = 'info';
  popupMostrar = false;
  isLoading = false;
  hayMasPeliculas = false;
  private offset = 0;
  private limit = 2;

  constructor(private fb: FormBuilder, private service: ProyectedMovieReportService) {
    this.reportForm = this.fb.group({
      fechaInicio: [''],
      fechaFin: [''],
      nombreSala: [''],
    });
  }

  generateReport(): void {
    this.infoMessage = null;
    this.isLoading = true;
    this.offset = 0;
    this.report = [];

    const { fechaInicio, fechaFin, nombreSala } = this.reportForm.value;

    const startDate: string | null = fechaInicio?.toString().trim() || null;
    const endDate: string | null = fechaFin?.toString().trim() || null;
    const roomName: string | null = nombreSala?.toString().trim() || null;

    this.service.getMovies(startDate, endDate, roomName, this.offset, this.limit).subscribe({
      next: (data: ProyectedMoviesResponseReportDTO) => {
        this.report = data.peliculasProyectadas || [];
        this.offset += this.report.length;
        this.hayMasPeliculas = this.report.length === this.limit;
        this.infoMessage = 'Informe generado exitosamente';
        this.popupTipo = 'success';
        this.popupMostrar = true;
        this.isLoading = false;
      },
      error: (error: Error) => {
        this.infoMessage = `Error al generar el reporte: ${error.message}`;
        this.popupTipo = 'error';
        this.popupMostrar = true;
        this.isLoading = false;
      },
    });
  }

  loadMore(): void {
    this.isLoading = true;

    const { fechaInicio, fechaFin, nombreSala } = this.reportForm.value;
    const startDate: string | null = fechaInicio?.toString().trim() || null;
    const endDate: string | null = fechaFin?.toString().trim() || null;
    const roomName: string | null = nombreSala?.toString().trim() || null;

    this.service.getMovies(startDate, endDate, roomName, this.offset, this.limit).subscribe({
      next: (data: ProyectedMoviesResponseReportDTO) => {
        const nuevasPeliculas = data.peliculasProyectadas || [];
        this.report.push(...nuevasPeliculas);
        this.offset += nuevasPeliculas.length;
        this.hayMasPeliculas = nuevasPeliculas.length === this.limit;
        this.isLoading = false;
      },
      error: () => {
        this.infoMessage = 'Error al cargar más películas.';
        this.isLoading = false;
      },
    });
  }
}
