import { Component } from '@angular/core';

import { CommentedRoomResponseReportDTO } from '../../../models/dto/cinema-admin/commented-room-report/commented-room-response-report-dto';
import { CommentedRoomReportService } from '../../../services/cinema-admin/reports/comment-room-report-service.service';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-coment-room-report.component',
  imports: [ReactiveFormsModule],
  templateUrl: './coment-room-report.component.html',
  styleUrl: './coment-room-report.component.scss',
})
export class ComentRoomReportComponent {
  reportForm: FormGroup;
  report: CommentedRoomResponseReportDTO | null = null;
  isLoading = false;
  errorMessage: string | null = null;
  comentarios: any[] = [];
  tieneMasComentarios: boolean = true;
  private comentariosCargados: number = 0;
  private comentarioPorCarga: number = 3;
  salas: any[] = [];

  constructor(private fb: FormBuilder, private service: CommentedRoomReportService) {
    const today = new Date();
    const firstDayOfMonth = new Date(today.getFullYear(), today.getMonth(), 1);
    this.reportForm = this.fb.group({
      fechaInicio: [this.formatDate(firstDayOfMonth), Validators.required],
      fechaFin: [this.formatDate(today), Validators.required],
      sala: ['', Validators.required],
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

    const { fechaInicio, fechaFin, sala } = this.reportForm.value;

    this.service.generateReport(fechaInicio, fechaFin, sala).subscribe({
      next: (data: CommentedRoomResponseReportDTO) => {
        this.report = data;
        this.comentarios = data.salasComentadas.slice(0, this.comentarioPorCarga);
        this.tieneMasComentarios = data.salasComentadas.length > this.comentarioPorCarga;
        this.comentariosCargados = this.comentarioPorCarga;
        this.isLoading = false;
      },
      error: (error) => {
        this.errorMessage = 'Error al generar el reporte. Por favor, inténtelo de nuevo más tarde.';
        this.isLoading = false;
      },
    });
  }

  loadSalas(): void {}

  loadMore(): void {
    const nextComments = this.report?.salasComentadas.slice(
      this.comentariosCargados,
      this.comentariosCargados + this.comentarioPorCarga
    );
    if (nextComments) {
      this.comentarios.push(...nextComments);
      this.comentariosCargados += this.comentarioPorCarga;
      this.tieneMasComentarios =
        this.comentariosCargados < (this.report?.salasComentadas.length || 0);
    }
  }
}
