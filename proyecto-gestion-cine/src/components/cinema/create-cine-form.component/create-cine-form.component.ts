import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { SharePopupComponent } from '../../../shared/share-popup.component/share-popup.component';
import { CineService } from '../../../services/cine/cine.service';
import { Cine } from '../../../models/cinema/cine';
import { CreateCineDto } from '../../../models/dto/cine/create-cine-dto';

@Component({
  selector: 'app-create-cine-form',
  imports: [ReactiveFormsModule, SharePopupComponent],
  templateUrl: './create-cine-form.component.html',
  styleUrls: ['./create-cine-form.component.scss'],
})
export class CreateCineFormComponent implements OnInit {
  cineForm!: FormGroup;
  infoMessage: string | null = null;
  popupTipo: 'error' | 'success' | 'info' = 'info';
  popupMostrar = false;

  constructor(private fb: FormBuilder, private service: CineService) {}

  ngOnInit(): void {
    this.cineForm = this.fb.group({
      nombreCine: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(50)]],
      fechaCreacion: ['', [Validators.required]],
      costoOcultacionAnuncio: ['', [Validators.required, Validators.min(0)]],
    });
  }

  onSubmit(): void {
    if (this.cineForm.valid) {
      const cineData = this.cineForm.value;
      const cineDto: CreateCineDto = {
        nombreCine: cineData.nombreCine,
        fechaCreacion: cineData.fechaCreacion,
        costoOcultacionAnuncio: cineData.costoOcultacionAnuncio,
      };
      this.service.crearCine(cineDto).subscribe({
        next: (response) => {
          this.cineForm.reset();
          this.infoMessage = 'Cine creado exitosamente';
          this.popupTipo = 'success';
          this.popupMostrar = true;
        },
        error: (error) => {
          this.infoMessage = `Error al crear el cine: ${error.message}`;
          this.popupTipo = 'error';
          this.popupMostrar = true;
        },
      });
    }
  }

  onReset(): void {
    this.cineForm.reset();
  }

  get f() {
    return this.cineForm.controls;
  }
}
