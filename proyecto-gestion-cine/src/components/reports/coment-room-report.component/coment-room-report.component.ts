import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommentedRoomResponseReportDTO } from '../../../models/dto/cinema-admin/commented-room-report/commented-room-response-report-dto';
import { CommentedRoomReportService } from '../../../services/cinema-admin/reports/comment-room-report-service.service';
import { RoomCommentDTO } from '../../../models/dto/sysadmin/most-commented-room-report/room-comment-dto';

@Component({
  selector: 'app-coment-room-report',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './coment-room-report.component.html',
  styleUrls: ['./coment-room-report.component.scss'],
})
export class ComentRoomReportComponent {
  reportForm: FormGroup;
  comentarios: RoomCommentDTO[] = [];
  errorMessage: string | null = null;
  isLoading = false;
  isLoadingMore = false;
  tieneMasComentarios = false;

  private offset = 0;
  private limit = 3;

  constructor(private fb: FormBuilder, private service: CommentedRoomReportService) {
    const today = new Date();
    const firstDay = new Date(today.getFullYear(), today.getMonth(), 1);

    this.reportForm = this.fb.group({
      fechaInicio: [''],
      fechaFin: [''],
      nombreSala: [''],
    });
  }

  private formatDate(date: Date): string {
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
    this.comentarios = [];

    const { fechaInicio, fechaFin, nombreSala } = this.reportForm.value;

    const startDate = fechaInicio || '';
    const endDate = fechaFin || '';

    this.service.getComments(startDate, endDate, nombreSala, this.offset, this.limit).subscribe({
      next: (data: CommentedRoomResponseReportDTO) => {
        const nuevos = data.salasComentadas || [];
        this.comentarios = nuevos;
        this.offset += nuevos.length;
        this.tieneMasComentarios = nuevos.length === this.limit;
        this.isLoading = false;
      },
      error: () => {
        this.errorMessage = 'Error al generar el reporte. Por favor, inténtelo de nuevo.';
        this.isLoading = false;
      },
    });
  }

  loadMore(): void {
    this.isLoadingMore = true;

    const { fechaInicio, fechaFin, nombreSala } = this.reportForm.value;
    this.service.getComments(fechaInicio, fechaFin, nombreSala, this.offset, this.limit).subscribe({
      next: (data: CommentedRoomResponseReportDTO) => {
        const nuevos = data.salasComentadas || [];
        this.comentarios.push(...nuevos);
        this.offset += nuevos.length;
        this.tieneMasComentarios = nuevos.length === this.limit;
        this.isLoadingMore = false;
      },
      error: () => {
        this.errorMessage = 'Error al cargar más comentarios.';
        this.isLoadingMore = false;
      },
    });
  }
}
