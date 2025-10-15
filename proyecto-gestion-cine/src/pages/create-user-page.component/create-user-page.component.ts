import { Component } from '@angular/core';
import { CreateUserComponent } from '../../components/create-user.component/create-user.component';

@Component({
  selector: 'app-create-user-page.component',
  imports: [CreateUserComponent],
  templateUrl: './create-user-page.component.html',
  styleUrl: './create-user-page.component.scss',
})
export class CreateUserPageComponent {}
