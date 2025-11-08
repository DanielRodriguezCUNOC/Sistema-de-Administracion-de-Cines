import { Component } from '@angular/core';
import { SoldTicketResponseReportDTO } from '../../../models/dto/cinema-admin/sold-ticket-report/sold-ticket-response-report-dto';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { TicketSoldReportService } from '../../../services/cinema-admin/reports/ticket-sold-report-service.service';
import { CommonModule } from '@angular/common';
import { SharePopupComponent } from '../../../shared/share-popup.component/share-popup.component';

@Component({
  selector: 'app-ticket-sold-report.component',
  imports: [ReactiveFormsModule, CommonModule, SharePopupComponent],
  templateUrl: './ticket-sold-report.component.html',
  styleUrl: './ticket-sold-report.component.scss',
})
export class TicketSoldReportComponent {
  reportForm: FormGroup;
  report: SoldTicketResponseReportDTO | null = null;
  isLoading: boolean = false;
  infoMessage: string | null = null;
  popupTipo: 'error' | 'success' | 'info' = 'info';
  popupMostrar = false;

  constructor(private fb: FormBuilder, private service: TicketSoldReportService) {
    this.reportForm = this.fb.group({
      fechaInicio: [''],
      fechaFin: [''],
      nombreSala: [''],
    });
  }

  generateReport(): void {
    this.isLoading = true;
    this.infoMessage = '';

    const { fechaInicio, fechaFin, nombreSala } = this.reportForm.value;

    const startDate: string | null = fechaInicio?.toString().trim() || null;
    const endDate: string | null = fechaFin?.toString().trim() || null;
    const roomName: string | null = nombreSala?.toString().trim() || null;

    this.service.generateReport(startDate, endDate, roomName).subscribe({
      next: (data: SoldTicketResponseReportDTO) => {
        console.log(data);
        this.report = data;
        this.infoMessage = 'Informe generado exitosamente';
        this.popupTipo = 'success';
        this.popupMostrar = true;
        this.isLoading = false;
      },
      error: (error: Error) => {
        this.infoMessage = `Error al generar el informe: ${error.message}`;
        this.popupTipo = 'error';
        this.popupMostrar = true;
        this.isLoading = false;
      },
    });
  }
}
