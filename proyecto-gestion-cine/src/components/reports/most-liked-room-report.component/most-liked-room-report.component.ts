import { Component } from '@angular/core';
import { MostLikedRoomResponseReportDTO } from '../../../models/dto/cinema-admin/most-liked-room-report/most-liked-room-response-report-dto';
import { MostLikedRoomReportService } from '../../../services/cinema-admin/reports/most-liked-room-report-service.service';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { SharePopupComponent } from '../../../shared/share-popup.component/share-popup.component';

@Component({
  selector: 'app-most-liked-room-report.component',
  imports: [ReactiveFormsModule, CommonModule, SharePopupComponent],
  templateUrl: './most-liked-room-report.component.html',
  styleUrl: './most-liked-room-report.component.scss',
})
export class MostLikedRoomReportComponent {
  reportForm: FormGroup;
  report: MostLikedRoomResponseReportDTO | null = null;
  isLoading: boolean = false;
  infoMessage: string | null = null;
  popupTipo: 'error' | 'success' | 'info' = 'info';
  popupMostrar = false;

  constructor(private fb: FormBuilder, private service: MostLikedRoomReportService) {
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
    this.isLoading = true;
    this.infoMessage = null;

    const { fechaInicio, fechaFin, nombreSala } = this.reportForm.value;

    const startDate: string | null = fechaInicio?.toString().trim() || null;
    const endDate: string | null = fechaFin?.toString().trim() || null;
    const roomName: string | null = nombreSala?.toString().trim() || null;

    this.service.generateReport(startDate, endDate, roomName).subscribe({
      next: (data: MostLikedRoomResponseReportDTO) => {
        this.report = data;
        this.infoMessage = 'Informe generado exitosamente';
        this.popupTipo = 'success';
        this.popupMostrar = true;
        this.isLoading = false;
      },
      error: (error: Error) => {
        this.infoMessage = `Error al generar el informe de salas más gustadas: ${error.message}`;
        this.popupTipo = 'error';
        this.popupMostrar = true;
        this.isLoading = false;
      },
    });
  }
}
