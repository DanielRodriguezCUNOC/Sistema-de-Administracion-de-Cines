import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-create-room-form.component',
  imports: [ReactiveFormsModule],
  templateUrl: './create-room-form.component.html',
  styleUrl: './create-room-form.component.scss',
})
export class CreateRoomFormComponent implements OnInit {
  roomForm!: FormGroup;
  submitted: boolean = false;

  cines: Array<{ id: number; nombre: string }> = [];

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    //Rellenar con el listado que venga del backend
    this.cines = [
      { id: 1, nombre: 'CinePolis' },
      { id: 2, nombre: 'Cinemex' },
      { id: 3, nombre: 'AlbaCinema' },
    ];

    this.roomForm = this.fb.group({
      idCine: ['', Validators.required],
      nombreSala: ['', [Validators.required, Validators.maxLength(100)]],
      fila: [1, [Validators.required, Validators.min(1)]],
      columna: [1, [Validators.required, Validators.min(1)]],
      estadoComentario: [true],
      estadoValoracion: [true],
    });
  }

  get f() {
    return this.roomForm.controls;
  }

  onSubmit(): void {
    this.submitted = true;
    if (this.roomForm.invalid) {
      return;
    }

    const payload = this.roomForm.value;
    console.log('Crear sala payload:', payload);
    // TODO: llamar al servicio para persistir la sala
    // ejemplo: this.salaService.create(payload).subscribe(...)

    // opcional: reset tras guardar
    // this.onReset();
  }

  onReset(): void {
    this.submitted = false;
    this.roomForm.reset({
      idCine: '',
      nombreSala: '',
      fila: 1,
      columna: 1,
      estadoComentario: true,
      estadoValoracion: true,
    });
  }
}
