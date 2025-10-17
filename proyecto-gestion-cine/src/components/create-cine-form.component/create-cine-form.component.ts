import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';

@Component({
  selector: 'app-create-cine-form',
  imports: [ReactiveFormsModule],
  templateUrl: './create-cine-form.component.html',
  styleUrls: ['./create-cine-form.component.scss'],
})
export class CreateCineFormComponent implements OnInit {
  cineForm!: FormGroup;
  submitted = false;

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.cineForm = this.fb.group({
      nombreCine: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(50)]],
      fechaCreacion: ['', [Validators.required]],
      costoOcultacionAnuncios: ['', [Validators.required, Validators.min(0)]],
    });
  }

  onSubmit(): void {
    this.submitted = true;
    if (this.cineForm.valid) {
      console.log('Formulario de cine válido:', this.cineForm.value);
    } else {
      console.log('Formulario de cine inválido');
      return;
    }
  }

  onReset(): void {
    this.submitted = false;
    this.cineForm.reset();
  }

  get f() {
    return this.cineForm.controls;
  }
}
