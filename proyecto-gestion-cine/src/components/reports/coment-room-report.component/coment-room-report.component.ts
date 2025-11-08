import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommentedRoomResponseReportDTO } from '../../../models/dto/cinema-admin/commented-room-report/commented-room-response-report-dto';
import { CommentedRoomReportService } from '../../../services/cinema-admin/reports/comment-room-report-service.service';
import { RoomCommentDTO } from '../../../models/dto/sysadmin/most-commented-room-report/room-comment-dto';
import { SharePopupComponent } from '../../../shared/share-popup.component/share-popup.component';

@Component({
  selector: 'app-coment-room-report',
  standalone: true,
  imports: [ReactiveFormsModule, SharePopupComponent],
  templateUrl: './coment-room-report.component.html',
  styleUrls: ['./coment-room-report.component.scss'],
})
export class ComentRoomReportComponent {
  reportForm: FormGroup;
  comentarios: RoomCommentDTO[] = [];
  infoMessage: string | null = null;
  popupTipo: 'error' | 'success' | 'info' = 'info';
  popupMostrar = false;
  isLoading = false;
  isLoadingMore = false;
  tieneMasComentarios = false;

  private offset = 0;
  private limit = 3;

  constructor(private fb: FormBuilder, private service: CommentedRoomReportService) {
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
    this.comentarios = [];

    const { fechaInicio, fechaFin, nombreSala } = this.reportForm.value;

    const startDate: string | null = fechaInicio?.toString().trim() || null;
    const endDate: string | null = fechaFin?.toString().trim() || null;
    const roomName: string | null = nombreSala?.toString().trim() || null;

    this.service.getComments(startDate, endDate, roomName, this.offset, this.limit).subscribe({
      next: (data: CommentedRoomResponseReportDTO) => {
        const nuevos = data.salasComentadas || [];
        this.comentarios = nuevos;
        this.offset += nuevos.length;
        this.tieneMasComentarios = nuevos.length === this.limit;
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
    this.isLoadingMore = true;

    const { fechaInicio, fechaFin, nombreSala } = this.reportForm.value;

    const startDate: string | null = fechaInicio?.toString().trim() || null;
    const endDate: string | null = fechaFin?.toString().trim() || null;
    const roomName: string | null = nombreSala?.toString().trim() || null;

    this.service.getComments(startDate, endDate, roomName, this.offset, this.limit).subscribe({
      next: (data: CommentedRoomResponseReportDTO) => {
        const nuevos = data.salasComentadas || [];
        this.comentarios.push(...nuevos);
        this.offset += nuevos.length;
        this.tieneMasComentarios = nuevos.length === this.limit;
        this.isLoadingMore = false;
      },
      error: (err: Error) => {
        this.infoMessage = `Error al cargar más comentarios: ${err.message}`;
        this.popupTipo = 'error';
        this.popupMostrar = true;
        this.isLoadingMore = false;
      },
    });
  }
}
