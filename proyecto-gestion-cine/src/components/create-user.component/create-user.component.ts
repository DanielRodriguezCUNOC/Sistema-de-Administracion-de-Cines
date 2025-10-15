import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-create-user',
  imports: [BrowserModule, ReactiveFormsModule],
  templateUrl: './create-user.component.html',
  styleUrl: './create-user.component.scss',
})
export class CreateUserComponent implements OnInit {
  nuevoRegistroUsuario!: FormGroup;
  nuevoRegistro!: Event;

  constructor(private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    this.nuevoRegistroUsuario = this.formBuilder.group({
      nombre: [null, [Validators.required, Validators.minLength(2), Validators.maxLength(150)]],
      email: [null, [Validators.required, Validators.email]],
    });
  }

  submit(): void {
    console.log(this.nuevoRegistroUsuario.value);
  }
}
