import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-create-advertisement-form',
  imports: [ReactiveFormsModule],
  templateUrl: './create-advertisement-form.component.html',
  styleUrl: './create-advertisement-form.component.scss',
})
export class CreateAdvertisementFormComponent implements OnInit {
  advertisementForm!: FormGroup;
  submitted = false;
  tipoAnuncio: any[] = [];
  vigenciaAnuncio: any[] = [];

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    // Rellenar con el listado que venga del backend
    this.tipoAnuncio = [
      { value: 'banner', viewValue: 'Banner' },
      { value: 'video', viewValue: 'Video' },
      { value: 'popup', viewValue: 'Popup' },
    ];

    this.vigenciaAnuncio = [
      { value: '7_days', viewValue: '7 Días' },
      { value: '15_days', viewValue: '15 Días' },
      { value: '30_days', viewValue: '30 Días' },
    ];

    this.advertisementForm = this.fb.group({
      tipoAnuncio: ['', Validators.required],
      vigenciaAnuncio: ['', Validators.required],
      nombreAnuncio: ['', [Validators.required, Validators.maxLength(100)]],
      fechaCompra: ['', Validators.required],
      fechaCaducidad: ['', Validators.required],
      texto: ['', Validators.maxLength(600)],
      imagen: [null],
      linkVideo: ['', [Validators.maxLength(600)]],
      estado: [true],
    });
  }

  get f() {
    return this.advertisementForm.controls;
  }

  onFileSelected(event: any) {
    const file = event.target.files[0];
    this.advertisementForm.patchValue({
      imagen: file,
    });
  }

  onSubmit() {
    this.submitted = true;

    if (this.advertisementForm.invalid) {
      // Lógica para manejar el formulario inválido
      return;
    }

    // Lógica para guardar el anuncio
    const payload = this.advertisementForm.value;
    console.log('Crear anuncio payload:', payload);
  }

  onReset() {
    this.submitted = false;
    this.advertisementForm.reset({ estado: true });
  }
}
