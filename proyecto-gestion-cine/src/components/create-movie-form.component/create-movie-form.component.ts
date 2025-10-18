import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-create-movie-form.component',
  imports: [ReactiveFormsModule],
  templateUrl: './create-movie-form.component.html',
  styleUrl: './create-movie-form.component.scss',
})
export class CreateMovieFormComponent implements OnInit {
  movieForm!: FormGroup;
  submitted: boolean = false;
  posterFile: File | null = null;

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.movieForm = this.fb.group({
      tituloPelicula: ['', [Validators.required, Validators.maxLength(50)]],
      sinopsis: ['', [Validators.required, Validators.maxLength(500)]],
      duracion: [
        '',
        [Validators.required, Validators.maxLength(5), Validators.pattern(/^\d{2}:\d{2}$/)],
      ],
      clasificacion: ['', [Validators.required, Validators.maxLength(5)]],
      fechaEstreno: ['', [Validators.required]],
      cast: ['', [Validators.required, Validators.maxLength(300)]],
      director: ['', [Validators.required, Validators.maxLength(150)]],
      precioPelicula: ['', [Validators.required, Validators.min(0)]],
      poster: [null, [Validators.required]],
    });
  }

  get f() {
    return this.movieForm.controls;
  }

  onFileChange(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.posterFile = input.files[0];
      this.movieForm.patchValue({ poster: this.posterFile });
      this.movieForm.get('poster')?.updateValueAndValidity();
    }
  }

  onSubmit(): void {
    this.submitted = true;
    if (this.movieForm.invalid) {
      return;
    }

    // Preparar payload (ejemplo con FormData para envío con fichero)
    const formData = new FormData();
    formData.append('titulo_pelicula', this.f['tituloPelicula'].value);
    formData.append('sinopsis', this.f['sinopsis'].value);
    formData.append('duracion', this.f['duracion'].value);
    formData.append('cast', this.f['cast'].value);
    formData.append('director', this.f['director'].value);
    formData.append('clasificacion', this.f['clasificacion'].value);
    formData.append('fecha_estreno', this.f['fechaEstreno'].value);
    formData.append('precio_pelicula', this.f['precioPelicula'].value);
    if (this.posterFile) {
      formData.append('poster', this.posterFile, this.posterFile.name);
    }

    // TODO: llamar al servicio que haga POST al backend
    console.log('Formulario válido. Preparado para enviar:', this.movieForm.value);
    // ejemplo: this.movieService.createMovie(formData).subscribe(...)

    // Reset opcional
    this.onReset();
  }

  onReset(): void {
    this.submitted = false;
    this.posterFile = null;
    this.movieForm.reset();
  }
}
