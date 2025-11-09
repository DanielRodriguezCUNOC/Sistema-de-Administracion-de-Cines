import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CineService } from '../../../services/cine/cine.service';
import { Cine } from '../../../models/cinema/cine';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { SharePopupComponent } from '../../../shared/share-popup.component/share-popup.component';

@Component({
  selector: 'app-update-cinema',
  imports: [ReactiveFormsModule, SharePopupComponent],
  templateUrl: './update-cinema.component.html',
  styleUrls: ['./update-cinema.component.scss'],
})
export class UpdateCinemaComponent implements OnInit {
  cine: Cine | null = null;
  cineForm!: FormGroup;
  infoMessage: string | null = null;
  popupTipo: 'error' | 'success' | 'info' = 'info';
  popupMostrar = false;

  constructor(
    private route: ActivatedRoute,
    private service: CineService,
    private router: Router,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    const idCine = Number(this.route.snapshot.paramMap.get('idCine'));
    this.obtenerCine(idCine);
    this.inicializarFormulario();
  }

  inicializarFormulario(): void {
    this.cineForm = this.fb.group({
      idCine: [{ value: '', disabled: true }],
      nombreCine: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(50)]],
      fechaCreacion: ['', [Validators.required]],
      costoOcultacionAnuncio: ['', [Validators.required, Validators.min(0)]],
    });
  }

  obtenerCine(idCine: number): void {
    this.service.obtenerCinePorId(idCine).subscribe({
      next: (data) => {
        this.cine = data;
        this.cineForm.patchValue(this.cine);
      },
      error: (error: Error) => {
        this.infoMessage = `Error al obtener el cine: ${error.message}`;
        this.popupTipo = 'error';
        this.popupMostrar = true;
      },
    });
  }

  actualizarCine(): void {
    if (this.cineForm.valid) {
      const cineData = this.cineForm.value;
      this.service.actualizarCine(cineData.idCine, cineData).subscribe({
        next: () => {
          this.infoMessage = 'Cine actualizado exitosamente';
          this.popupTipo = 'success';
          this.popupMostrar = true;
          this.router.navigate(['/list-cinema']);
        },
        error: (error: Error) => {
          this.infoMessage = `Error al actualizar el cine: ${error.message}`;
          this.popupTipo = 'error';
          this.popupMostrar = true;
        },
      });
    }
  }
}
