import { Injectable } from '@angular/core';
import { RestConstants } from '../../shared/rest-constants';
import { HttpClient } from '@angular/common/http';
import { User } from '../../models/user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  restConstants = new RestConstants();

  constructor(private httpClient: HttpClient) {}

  public createNewUser(user: User): Observable<void> {
    return this.httpClient.post<void>(`${this.restConstants.getApiURL()}users`, user);
  }

  public getUser(): Observable<User> {
    return this.httpClient.get<User>(`${this.restConstants.getApiURL()}users`);
  }
}
