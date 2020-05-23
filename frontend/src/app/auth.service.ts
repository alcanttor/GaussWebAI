import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private token: string;
  constructor() {}

  login(email, password, cb) {
    this.token = '<some_token>';
    if (cb) {
      cb();
    }
  }

  logout() {
    this.token = null;
  }

  getAuthorizationHeaders() {}
}
