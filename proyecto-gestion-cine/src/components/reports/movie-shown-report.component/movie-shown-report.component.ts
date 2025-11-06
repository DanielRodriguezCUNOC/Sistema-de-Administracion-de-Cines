import { Component } from '@angular/core';
import { ProyectedMoviesResponseReportDTO } from '../../../models/dto/cinema-admin/proyected-movies-report/proyected-movies-response-report-dto';
import {
  FormGroup,
  FormBuilder,
  Validators,
  ReactiveFormsModule,
  AbstractControl,
  ValidationErrors,
} from '@angular/forms';
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
  peliculasProyectadas: MovieProyectedRoomDTO[] = [];
  errorMessage: string | null = null;
  isLoading = false;
  hayMasPeliculas = false;
  private offset = 0;
  private limit = 2;

  private readonly SALA_PATTERN = /^[A-Za-zÁÉÍÓÚÜÑáéíóúüñ0-9\s\-_.]*$/;

  constructor(private fb: FormBuilder, private service: ProyectedMovieReportService) {
    this.reportForm = this.fb.group(
      {
        fechaInicio: ['', [this.dateFormatValidator]],
        fechaFin: ['', [this.dateFormatValidator]],
        nombreSala: ['', [Validators.maxLength(50), Validators.pattern(this.SALA_PATTERN)]],
      },
      { validators: [this.dateRangeValidator()] }
    );
  }

  formatDate(date: Date): string {
    return date.toISOString().split('T')[0];
  }

  //* Valida formato yyyy-MM-dd solo si hay valor
  private dateFormatValidator(control: AbstractControl): ValidationErrors | null {
    const v: string = control.value;
    if (!v) return null;
    const isValid = /^\d{4}-\d{2}-\d{2}$/.test(v);
    return isValid ? null : { dateFormat: true };
  }

  // *Si ambas fechas vienen, inicio <= fin
  private dateRangeValidator() {
    return (group: AbstractControl): ValidationErrors | null => {
      const inicio = group.get('fechaInicio')?.value;
      const fin = group.get('fechaFin')?.value;
      if (!inicio || !fin) return null;
      const start = new Date(inicio);
      const end = new Date(fin);
      if (isNaN(start.getTime()) || isNaN(end.getTime())) return null;
      return start <= end ? null : { invalidRange: true };
    };
  }

  private buildValidationMessages(): string[] {
    const msgs: string[] = [];
    const f = this.reportForm;

    const inicio = f.get('fechaInicio');
    const fin = f.get('fechaFin');
    const sala = f.get('nombreSala');

    if (inicio?.errors?.['dateFormat'])
      msgs.push('La fecha de inicio debe tener formato yyyy-MM-dd.');
    if (fin?.errors?.['dateFormat']) msgs.push('La fecha de fin debe tener formato yyyy-MM-dd.');
    if (f.errors?.['invalidRange'])
      msgs.push('La fecha de inicio no puede ser mayor que la fecha de fin.');
    if (sala?.errors?.['maxlength']) msgs.push('El nombre de sala no debe superar 50 caracteres.');
    if (sala?.errors?.['pattern'])
      msgs.push('El nombre de sala contiene caracteres no permitidos.');

    return msgs;
  }

  generateReport(): void {
    // Marcar todo como tocado y validar
    this.reportForm.markAllAsTouched();
    const validationMessages = this.buildValidationMessages();
    if (validationMessages.length > 0) {
      this.errorMessage = validationMessages.join('\n');
      return;
    }

    this.errorMessage = null;
    this.isLoading = true;
    this.offset = 0;
    this.peliculasProyectadas = [];

    const { fechaInicio, fechaFin, nombreSala } = this.reportForm.value;

    //* Enviar vacío si no hay valor
    const startDate: string = (fechaInicio || '').trim();
    const endDate: string = (fechaFin || '').trim();
    const roomName: string = (nombreSala || '').trim();

    this.service.getMovies(startDate, endDate, roomName, this.offset, this.limit).subscribe({
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
    //* Validar también antes de cargar más
    const validationMessages = this.buildValidationMessages();
    if (validationMessages.length > 0) {
      this.errorMessage = validationMessages.join('\n');
      return;
    }

    this.isLoading = true;

    const { fechaInicio, fechaFin, nombreSala } = this.reportForm.value;
    const startDate: string = (fechaInicio || '').trim();
    const endDate: string = (fechaFin || '').trim();
    const roomName: string = (nombreSala || '').trim();

    this.service.getMovies(startDate, endDate, roomName, this.offset, this.limit).subscribe({
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
