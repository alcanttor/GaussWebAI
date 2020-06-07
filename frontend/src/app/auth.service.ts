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
      .get(`${config.BASE_URL}/token/${username}/${password}`, {
        responseType: 'text',
      })
      .subscribe((token: string) => {
        localStorage.setItem('token', token);
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
