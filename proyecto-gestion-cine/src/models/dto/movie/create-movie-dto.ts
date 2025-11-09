export interface CreateMovieDto {
  tituloPelicula: string;
  sinopsis: string;
  duracion: number;
  cast: string;
  director: string;
  clasificacion: string;
  fechaEstreno: string;
  precioPelicula: number;
  poster?: File;
  categorias?: string[];
}
