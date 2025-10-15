import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  private isLoggedInSubject = new BehaviorSubject<boolean>(false);

  public isLoggedIn$ = this.isLoggedInSubject.asObservable();

  constructor() {
    this.checkLoginStatus();
  }

  private checkLoginStatus() {
    const loggedIn = localStorage.getItem('isLoggedIn') === 'true';
    this.isLoggedInSubject.next(loggedIn);
  }

  login() {
    this.isLoggedInSubject.next(false);
    localStorage.removeItem('isLoggedIn');
  }

  logout() {
    this.isLoggedInSubject.next(false);
    localStorage.removeItem('isLoggedIn');
  }

  get isLoggedIn(): boolean {
    return this.isLoggedInSubject.value;
  }
}
