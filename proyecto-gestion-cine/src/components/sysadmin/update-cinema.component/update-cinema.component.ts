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
    this.inicializarFormulario();
    this.obtenerCine(idCine);
  }

  inicializarFormulario(): void {
    this.cineForm = this.fb.group({
      idCine: [{ value: '', disabled: true }],
      nombreCine: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(50)]],
    });
  }

  obtenerCine(idCine: number): void {
    this.service.obtenerCinePorId(idCine).subscribe({
      next: (data) => {
        console.log('Cine obtenido:', data);
        this.cine = data;
        this.cineForm.patchValue(this.cine);
        this.cineForm.get('idCine')?.setValue(this.cine.idCine);
      },
      error: (error: Error) => {
        this.infoMessage = `Error al obtener el cine: ${error.message}`;
        this.popupTipo = 'error';
        this.popupMostrar = true;
      },
    });
  }

  actualizarCine(): void {
    if (this.cineForm.valid && this.cine) {
      // Obtén el valor directamente del control
      const nombreControl = this.cineForm.get('nombreCine');
      if (!nombreControl) {
        console.error('Control nombreCine no encontrado');
        return;
      }

      const nombreCineValue = nombreControl.value;

      console.log('=== DEBUG COMPONENTE ===');
      console.log('Valor del formulario:', nombreCineValue);
      console.log('Tipo:', typeof nombreCineValue);
      console.log('Cine ID:', this.cine.idCine);
      console.log('========================');

      // Prepara los datos para enviar
      const cineData = {
        nombreCine: nombreCineValue,
      };

      console.log('Datos a enviar al servicio:', cineData);

      this.service.actualizarCine(this.cine.idCine, cineData).subscribe({
        next: () => {
          this.infoMessage = 'Cine actualizado exitosamente';
          this.popupTipo = 'success';
          this.popupMostrar = true;
          setTimeout(() => {
            this.router.navigate(['/list-cinema']);
          }, 2000);
        },
        error: (error: Error) => {
          console.error('Error en la actualización:', error);
          this.infoMessage = `Error al actualizar el cine: ${error.message}`;
          this.popupTipo = 'error';
          this.popupMostrar = true;
        },
      });
    } else {
      console.log('Formulario inválido o cine no definido');
      this.cineForm.markAllAsTouched();
    }
  }
}
