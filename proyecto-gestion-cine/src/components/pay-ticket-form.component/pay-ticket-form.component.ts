import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { PagoBoleto } from '../../models/pago-boleto';
import { PaymentTicketService } from '../../services/users/payment-ticket.service';

@Component({
  selector: 'app-pay-ticket-form.component',
  imports: [ReactiveFormsModule],
  templateUrl: './pay-ticket-form.component.html',
  styleUrl: './pay-ticket-form.component.scss',
})
export class PayTicketFormComponent implements OnInit {
  @Input() idPelicula!: number;
  @Input() idUsuario!: number;
  @Input() idSala!: number;

  payForm!: FormGroup;
  enviado: boolean = false;
  cargando: boolean = false;
  mensaje: string = '';

  constructor(private fb: FormBuilder, private paymentService: PaymentTicketService) {}

  ngOnInit(): void {
    this.payForm = this.fb.group({
      fechaPago: ['', Validators.required],
      cantidadBoleto: [1, [Validators.required, Validators.min(1)]],
      totalPago: ['', [Validators.required, Validators.min(0)]],
    });
  }

  get f() {
    return this.payForm.controls;
  }

  onSubmit(): void {
    this.enviado = true;
    if (this.payForm.invalid) return;

    const paymentDto: PagoBoleto = {
      idPagoBoleto: 0,
      idPelicula: this.idPelicula,
      idUsuario: this.idUsuario,
      idSala: this.idSala,
      fechaPago: this.payForm.value.fechaPago,
      cantidadBoleto: this.payForm.value.cantidadBoleto,
      totalPago: this.payForm.value.totalPago,
    };

    this.cargando = true;
    this.paymentService.createPayment(paymentDto).subscribe({
      next: () => {
        this.mensaje = 'Pago realizado con éxito.';
        this.payForm.reset();
        this.enviado = false;
      },
      error: () => {
        this.mensaje = 'Error al procesar el pago:';
      },
      complete: () => (this.cargando = false),
    });
  }
}
