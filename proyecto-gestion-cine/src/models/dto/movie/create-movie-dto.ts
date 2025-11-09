export interface CreateMovieDto {
  idUsuario: number;
  tituloPelicula: string;
  sinopsis: string;
  duracion: number;
  reparto: string;
  director: string;
  clasificacion: string;
  fechaEstreno: string;
  precioPelicula: number;
  poster?: File;
  categorias?: string[];
}
