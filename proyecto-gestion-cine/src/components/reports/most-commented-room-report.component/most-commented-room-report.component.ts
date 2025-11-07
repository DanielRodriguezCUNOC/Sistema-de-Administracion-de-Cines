import { Component } from '@angular/core';
import { MostCommentedRoomReportResponseDTO } from '../../../models/dto/sysadmin/most-commented-room-report/most-commented-room-report-response-dto';
import { MostCommentedRoomReportService } from '../../../services/sysadmin/reports/most-commented-room-report-service.service';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { SharePopupComponent } from '../../../shared/share-popup.component/share-popup.component';

@Component({
  selector: 'app-most-commented-room-report.component',
  imports: [ReactiveFormsModule, SharePopupComponent],
  templateUrl: './most-commented-room-report.component.html',
  styleUrl: './most-commented-room-report.component.scss',
})
export class MostCommentedRoomReportComponent {
  reportForm: FormGroup;
  report: MostCommentedRoomReportResponseDTO | null = null;
  isLoading = false;
  errorMessage: string | null = null;

  constructor(private fb: FormBuilder, private service: MostCommentedRoomReportService) {
    this.reportForm = this.fb.group({
      fechaInicio: [''],
      fechaFin: [''],
    });
  }
  generateReport(): void {
    this.isLoading = true;
    this.errorMessage = '';

    const { fechaInicio, fechaFin } = this.reportForm.value;

    const startDate: string | null = fechaInicio?.toString().trim() || null;
    const endDate: string | null = fechaFin?.toString().trim() || null;

    this.service.generateReport(startDate, endDate).subscribe({
      next: (data: MostCommentedRoomReportResponseDTO) => {
        this.report = data;
        this.isLoading = false;
      },
      error: (error) => {
        this.errorMessage =
          'Error al generar el informe de anuncios comprados. Por favor, inténtelo de nuevo más tarde.';
        this.isLoading = false;
      },
    });
  }
}
