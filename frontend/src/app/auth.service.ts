import { Injectable } from '@angular/core';
import config from './shared/config';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private httpClient: HttpClient) {}

  login(username, password, cb) {
    this.httpClient
      .get(`${config.BASE_URL}/token/${username}/${password}`)
      .subscribe((response: any) => {
        localStorage.setItem('token', response.jwt);
        if (cb) {
          cb();
        }
      });
  }

  getAuthHeader() {
    return {
      headers: { Authorization: `Bearer ${localStorage.getItem('token')}` },
    };
  }

  logout() {
    localStorage.removeItem('token');
  }

  isLoggedIn() {
    return !!localStorage.getItem('token');
  }
}
