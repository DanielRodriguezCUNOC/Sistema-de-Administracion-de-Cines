import { Component } from '@angular/core';
import { ListMovieDto } from '../../../models/dto/movie/list-movie-dto';
import { PeliculaService } from '../../../services/pelicula/pelicula.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list-movie.component',
  imports: [CommonModule],
  templateUrl: './list-movie.component.html',
  styleUrl: './list-movie.component.scss',
})
export class ListMovieComponent {
  peliculas: ListMovieDto[] = [];

  constructor(private service: PeliculaService, private router: Router) {}

  ngOnInit(): void {
    this.obtenerPeliculas();
  }

  obtenerPeliculas(): void {
    this.service.getPeliculas().subscribe({
      next: (data) => {
        this.peliculas = data;
      },
      error: (error) => {
        console.error('Error al obtener las películas:', error);
      },
    });
  }
  editarPelicula(idPelicula: number): void {
    this.router.navigate(['/editar-pelicula', idPelicula]);
  }

  desactivarPelicula(idPelicula: number): void {
    this.service.desactivarPelicula(idPelicula).subscribe({
      next: () => {
        console.log('Película desactivada con éxito');
        this.obtenerPeliculas();
      },
      error: (error) => {
        console.error('Error al desactivar la película:', error);
      },
    });
  }
}
