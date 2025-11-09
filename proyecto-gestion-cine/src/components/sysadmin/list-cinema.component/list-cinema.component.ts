import { Component, OnInit } from '@angular/core';
import { CineService } from '../../../services/cine/cine.service';
import { ListadoCineDTO } from '../../../models/dto/cine/listado-cine-dto';
import { SharePopupComponent } from '../../../shared/share-popup.component/share-popup.component';
import { Router } from '@angular/router';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-list-cinema',
  imports: [SharePopupComponent],
  templateUrl: './list-cinema.component.html',
  styleUrls: ['./list-cinema.component.scss'],
})
export class ListCinemaComponent implements OnInit {
  cines: ListadoCineDTO | null = null;
  infoMessage: string | null = null;
  popupTipo: 'error' | 'success' | 'info' = 'info';
  popupMostrar = false;
  tiposUsuarios: string[] = ['Usuario Normal', 'Usuario Especial'];

  constructor(private service: CineService, private router: Router) {}

  ngOnInit(): void {
    this.obtenerCines();
  }

  obtenerCines(): void {
    this.service.obtenerCines().subscribe((data) => {
      this.cines = data;
    });
  }

  toggleCine(idCine: number): void {
    this.service.desactivarCine(idCine).subscribe({
      next: () => {
        this.infoMessage = 'Estado del cine actualizado exitosamente';
        this.popupTipo = 'success';
        this.popupMostrar = true;
        this.obtenerCines();
      },
      error: (error: Error) => {
        this.infoMessage = `Error al actualizar el estado del cine: ${error.message}`;
        this.popupTipo = 'error';
        this.popupMostrar = true;
      },
    });
  }

  editarCine(idCine: number): void {
    this.router.navigate(['admin-system/update-cinema', idCine]);
  }
}
