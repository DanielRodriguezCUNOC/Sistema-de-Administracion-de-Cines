import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CreateAdminCinemaService } from '../../../services/sysadmin/actions/create-admin-cinema-service.service';
import { SharePopupComponent } from '../../../shared/share-popup.component/share-popup.component';
import { CreateAdminCinemaDto } from '../../../models/dto/sysadmin/create-admin-cinema/create-admin-cinema-dto';

@Component({
  selector: 'app-create-admin-cinema',
  imports: [SharePopupComponent, ReactiveFormsModule],
  templateUrl: './create-admin-cinema.component.html',
  styleUrls: ['./create-admin-cinema.component.scss'],
})
export class CreateAdminCinemaComponent implements OnInit {
  nuevoAdminCine!: FormGroup;
  infoMessage: string | null = null;
  popupTipo: 'error' | 'success' | 'info' = 'info';
  popupMostrar = false;
  selectedFile: File | null = null;

  constructor(private formBuilder: FormBuilder, private service: CreateAdminCinemaService) {}

  ngOnInit(): void {
    this.nuevoAdminCine = this.formBuilder.group({
      nombre: [null, [Validators.required]],
      email: [null, [Validators.required, Validators.email]],
      usuario: [null, [Validators.required]],
      password: [null, [Validators.required]],
      telefono: [null, [Validators.required]],
      fotografia: [null, [Validators.required]],
      rol: [2],
    });
  }

  submit(): void {
    if (this.nuevoAdminCine.valid) {
      const formData = this.nuevoAdminCine.value;

      const createAdminDto: CreateAdminCinemaDto = {
        idRol: formData.rol,
        nombreCompleto: formData.nombre,
        tipoUsuario: 'ADMIN_CINE',
        usuario: formData.usuario,
        password: formData.password,
        correo: formData.email,
        telefono: formData.telefono,
        foto: this.selectedFile,
      };

      this.service.createAdminCinema(createAdminDto).subscribe({
        next: (response) => {
          this.nuevoAdminCine.reset();
          this.infoMessage = 'Administrador de Cine creado exitosamente';
          this.popupTipo = 'success';
          this.popupMostrar = true;
        },
        error: (error: Error) => {
          this.infoMessage = `Error al crear el administrador: ${error.message}`;
          this.popupTipo = 'error';
          this.popupMostrar = true;
        },
      });
    } else {
      Object.keys(this.nuevoAdminCine.controls).forEach((key) => {
        this.nuevoAdminCine.get(key)?.markAsTouched();
      });
    }
  }

  onFileSelected(event: any): void {
    const file = event.target.files[0];
    if (file) {
      this.selectedFile = file;
    }
  }
}
