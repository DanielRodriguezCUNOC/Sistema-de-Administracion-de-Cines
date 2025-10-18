import { Component, OnInit } from '@angular/core';
import { Cine } from '../../../models/cinema/cine';
import { Router } from '@angular/router';
import { CineService } from '../../../services/cine/cine.service';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-show-cinema.component',
  imports: [DatePipe],
  templateUrl: './show-cinema.component.html',
  styleUrl: './show-cinema.component.scss',
})
export class ShowCinemaComponent implements OnInit {
  cines: Cine[] = [];
  cineService: CineService;

  constructor(private router: Router, cineService: CineService) {
    this.cineService = cineService;
  }

  ngOnInit(): void {
    this.cargarCines();
  }

  cargarCines() {
    /*
    this.cineService.obtenerCines().subscribe({
      next: (data: Cine[]): any => {
        this.cines = data;
      },
      error: (error: any): any => {
        console.error('Error al cargar los cines', error);
      },
    });
    */

    // Datos de ejemplo (eliminar cuando se conecte al backend)
    this.cines = [
      {
        idCine: 1,
        nombreCine: 'Cine Central',
        fechaCreacion: new Date('2024-01-15'),
        costoOcultacionAnuncios: 50,
      },
      {
        idCine: 2,
        nombreCine: 'Cine Plaza Mayor',
        fechaCreacion: new Date('2024-03-20'),
        costoOcultacionAnuncios: 75,
      },
      {
        idCine: 3,
        nombreCine: 'Cine Premium',
        fechaCreacion: new Date('2024-05-10'),
        costoOcultacionAnuncios: 100,
      },
    ];
  }
  ingresarCine(idCine: number): void {
    console.log('Ingresando al cine con ID:', idCine);

    // Navegar a la ruta del cine seleccionado
    this.router.navigate(['/cine-seleccionado', idCine]);
  }
}
