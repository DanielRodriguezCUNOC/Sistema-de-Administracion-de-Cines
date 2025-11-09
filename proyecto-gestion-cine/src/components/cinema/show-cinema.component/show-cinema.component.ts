import { Component, OnInit } from '@angular/core';
import { Cine } from '../../../models/cinema/cine';
import { CineService } from '../../../services/cine/cine.service';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-show-cinema',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLinkActive],
  templateUrl: './show-cinema.component.html',
  styleUrls: ['./show-cinema.component.scss'],
})
export class ShowCinemaComponent implements OnInit {
  cines: Cine[] = [];
  popupTipo: 'error' | 'success' | 'info' = 'info';
  popupMostrar = false;
  infoMessage: string | null = null;
  loading = false;

  constructor(private service: CineService) {}

  ngOnInit(): void {
    this.cargarCines();
  }

  cargarCines(): void {
    this.loading = true;
    this.service.obtenerCines().subscribe({
      next: (response) => {
        this.cines = response.cines;
        this.infoMessage = 'Cines cargados exitosamente.';
        this.popupTipo = 'success';
        this.popupMostrar = true;
        this.loading = false;
      },
      error: (error) => {
        this.popupTipo = 'error';
        this.infoMessage = `Error al cargar cines: ${error.message}`;
        this.popupMostrar = true;
        this.loading = false;
      },
    });
  }
}
