import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-pay-advertisement-form.component',
  imports: [ReactiveFormsModule],
  templateUrl: './pay-advertisement-form.component.html',
  styleUrl: './pay-advertisement-form.component.scss',
})
export class PayAdvertisementFormComponent implements OnInit {
  payAdvertisementForm!: FormGroup;
  submitted: boolean = false;
  idAnuncio: number = 0;
  nombreAnuncio: string = '';

  constructor(private fb: FormBuilder, private route: ActivatedRoute) {}
  ngOnInit(): void {
    this.payAdvertisementForm = this.fb.group({
      fechaPago: [''],
      montoPago: [''],
    });

    // Obtener parámetros de la ruta
    this.idAnuncio = this.route.snapshot.params['idAnuncio'];
    this.cargarAnuncio(this.idAnuncio);
  }

  get f() {
    return this.payAdvertisementForm.controls;
  }

  onSubmit() {
    this.submitted = true;

    if (this.payAdvertisementForm.valid) {
      const pagoData = {
        id_anuncio: this.idAnuncio, // Usamos el ID recibido por parámetro
        fecha_pago: this.payAdvertisementForm.value.fechaPago,
        monto_pago: this.payAdvertisementForm.value.montoPago,
        // id_usuario se asignaría automáticamente desde el usuario logueado
      };

      console.log('Datos del pago:', pagoData);
      // Aquí iría tu servicio para guardar el pago
    }
  }

  onReset() {
    this.submitted = false;
    this.payAdvertisementForm.reset();
  }

  cargarAnuncio(idAnuncio: number) {
    // Aquí llamas a tu servicio para obtener los datos del anuncio
    // this.anuncioService.getAnuncioById(idAnuncio).subscribe(anuncio => {
    //   this.nombreAnuncio = anuncio.nombre_anuncio;
    // });

    // Ejemplo temporal:
    this.nombreAnuncio = `Anuncio #${idAnuncio}`; // Esto lo reemplazarás con el nombre real
  }
}
