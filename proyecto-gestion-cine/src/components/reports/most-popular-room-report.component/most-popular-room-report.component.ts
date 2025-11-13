import { Component } from '@angular/core';
import { MostPopularRoomReportService } from '../../../services/sysadmin/reports/most-popular-room-report-service.service';
import { MostPopularRoomReportResponseDTO } from '../../../models/dto/sysadmin/most-popular-room-report/most-popular-room-report-response-dto';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { SharePopupComponent } from '../../../shared/share-popup.component/share-popup.component';

@Component({
  selector: 'app-most-popular-room-report.component',
  imports: [ReactiveFormsModule, SharePopupComponent],
  templateUrl: './most-popular-room-report.component.html',
  styleUrl: './most-popular-room-report.component.scss',
})
export class MostPopularRoomReportComponent {
  reportForm: FormGroup;
  report: MostPopularRoomReportResponseDTO | null = null;
  isLoading = false;
  infoMessage: string | null = null;
  popupTipo: 'error' | 'success' | 'info' = 'info';
  popupMostrar = false;

  constructor(private fb: FormBuilder, private service: MostPopularRoomReportService) {
    this.reportForm = this.fb.group({
      fechaInicio: [''],
      fechaFin: [''],
    });
  }

  generateReport(): void {
    this.isLoading = true;
    this.infoMessage = '';

    const { fechaInicio, fechaFin } = this.reportForm.value;

    const startDate: string | null = fechaInicio?.toString().trim() || null;
    const endDate: string | null = fechaFin?.toString().trim() || null;

    this.service.generateReport(startDate, endDate).subscribe({
      next: (data: MostPopularRoomReportResponseDTO) => {
        this.report = data;
        console.log(data);
        this.infoMessage = 'Informe generado exitosamente';
        this.popupTipo = 'success';
        this.popupMostrar = true;
        this.isLoading = false;
      },
      error: (error: Error) => {
        this.infoMessage = `Error al generar el informe de salas más populares: ${error.message}`;
        this.popupTipo = 'error';
        this.popupMostrar = true;
        this.isLoading = false;
      },
    });
  }
}
