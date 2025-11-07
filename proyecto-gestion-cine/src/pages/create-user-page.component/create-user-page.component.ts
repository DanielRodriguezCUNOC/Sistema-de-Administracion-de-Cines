import { Component } from '@angular/core';
import { CreateUserComponent } from '../../components/users/create-user.component/create-user.component';
import { PageLayoutComponent } from '../../components/share/page-layout.component/page-layout.component';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-create-user-page.component',
  imports: [PageLayoutComponent, RouterOutlet],
  templateUrl: './create-user-page.component.html',
  styleUrl: './create-user-page.component.scss',
})
export class CreateUserPageComponent {}
