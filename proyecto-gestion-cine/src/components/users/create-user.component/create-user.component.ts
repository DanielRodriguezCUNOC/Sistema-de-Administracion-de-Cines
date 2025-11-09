import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { CreateUserDto } from '../../../models/dto/user/create-user-dto';
import { SharePopupComponent } from '../../../shared/share-popup.component/share-popup.component';
import { CreateUserService } from '../../../services/users/create-user.service';
import { CommonModule, NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-create-user',
  imports: [ReactiveFormsModule, SharePopupComponent, CommonModule],
  templateUrl: './create-user.component.html',
  styleUrl: './create-user.component.scss',
})
export class CreateUserComponent implements OnInit {
  nuevoRegistroUsuario!: FormGroup;
  selectedFile: File | null = null;
  infoMessage: string | null = null;
  popupTipo: 'error' | 'success' | 'info' = 'info';
  popupMostrar = false;
  tiposUsuarios: string[] = ['Usuario Normal', 'Usuario Especial'];

  constructor(private formBuilder: FormBuilder, private service: CreateUserService) {}

  ngOnInit(): void {
    this.nuevoRegistroUsuario = this.formBuilder.group({
      nombre: [null, [Validators.required]],
      tipoUsuario: ['', [Validators.required]],
      email: [null, [Validators.required]],
      usuario: [null, [Validators.required]],
      password: [null, [Validators.required]],
      telefono: [null, [Validators.required]],
    });
  }

  onFileSelected(event: any): void {
    const file = event.target.files[0];
    if (file) {
      this.selectedFile = file;
    }
  }

  submit(): void {
    if (this.nuevoRegistroUsuario.valid) {
      const formData = this.nuevoRegistroUsuario.value;

      const userDto: CreateUserDto = {
        nombreCompleto: formData.nombre,
        usuario: formData.usuario,
        password: formData.password,
        correo: formData.email,
        telefono: formData.telefono,
        tipoUsuario: formData.tipoUsuario,
        foto: this.selectedFile,
      };

      this.service.createUser(userDto).subscribe({
        next: (response) => {
          this.nuevoRegistroUsuario.reset();
          this.infoMessage = 'Usuario creado exitosamente';
          this.popupTipo = 'success';
          this.popupMostrar = true;
        },
        error: (error: Error) => {
          this.infoMessage = `Error al crear el usuario: ${error.message}`;
          this.popupTipo = 'error';
          this.popupMostrar = true;
        },
      });
    } else {
      Object.keys(this.nuevoRegistroUsuario.controls).forEach((key) => {
        this.nuevoRegistroUsuario.get(key)?.markAsTouched();
      });
    }
  }
}
