import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { PeliculaService } from '../../../services/pelicula/pelicula.service';
import { CreateMovieDto } from '../../../models/dto/movie/create-movie-dto';
import { SharePopupComponent } from '../../../shared/share-popup.component/share-popup.component';

@Component({
  selector: 'app-create-movie-form.component',
  imports: [ReactiveFormsModule, SharePopupComponent],
  templateUrl: './create-movie-form.component.html',
  styleUrl: './create-movie-form.component.scss',
})
export class CreateMovieFormComponent implements OnInit {
  movieForm!: FormGroup;
  submitted: boolean = false;
  posterFile: File | null = null;
  popupTipo: 'error' | 'success' | 'info' = 'info';
  popupMostrar = false;
  infoMessage: string | null = null;
  loading = false;

  constructor(private fb: FormBuilder, private service: PeliculaService) {}

  ngOnInit(): void {
    this.movieForm = this.fb.group({
      tituloPelicula: ['', [Validators.required, Validators.maxLength(50)]],
      sinopsis: ['', [Validators.required, Validators.maxLength(500)]],
      duracion: ['', [Validators.required, Validators.min(1), Validators.max(999)]], // Cambiado a número
      clasificacion: ['', [Validators.required, Validators.maxLength(5)]],
      fechaEstreno: ['', [Validators.required]],
      reparto: ['', [Validators.required, Validators.maxLength(300)]],
      director: ['', [Validators.required, Validators.maxLength(150)]],
      precioPelicula: ['', [Validators.required, Validators.min(0)]],
      poster: [null, [Validators.required]],
      categorias: ['', [Validators.required]],
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

    const categoriasArray = this.movieForm.value.categorias
      .split(',')
      .map((cat: string) => cat.trim())
      .filter((cat: string) => cat.length > 0);

    const pelicula: CreateMovieDto = {
      tituloPelicula: this.movieForm.value.tituloPelicula,
      sinopsis: this.movieForm.value.sinopsis,
      duracion: this.movieForm.value.duracion,
      clasificacion: this.movieForm.value.clasificacion,
      fechaEstreno: this.movieForm.value.fechaEstreno,
      reparto: this.movieForm.value.reparto,
      director: this.movieForm.value.director,
      precioPelicula: this.movieForm.value.precioPelicula,
      poster: this.posterFile || undefined,
      categorias: categoriasArray,
      idUsuario: localStorage.getItem('usuarioActual')
        ? JSON.parse(localStorage.getItem('usuarioActual')!).idUsuario
        : 0,
    };
    console.log('ID Usuario:', pelicula.idUsuario);
    this.service.createNewPelicula(pelicula).subscribe({
      next: () => {
        this.popupTipo = 'success';
        this.infoMessage = 'Película creada exitosamente';
        this.popupMostrar = true;
        this.loading = false;
        this.onReset();
      },
      error: (error: Error) => {
        this.popupTipo = 'error';
        this.infoMessage = `Error al crear la película: ${error.message}`;
        this.popupMostrar = true;
        this.loading = false;
      },
    });
  }

  onReset(): void {
    this.submitted = false;
    this.posterFile = null;
    this.movieForm.reset();
  }
}
