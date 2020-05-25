import { Injectable } from '@angular/core';
import config from '../common/config';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class SitesService {
  private token: string;
  constructor(private httpClient: HttpClient) {}

  getSites() {
    console.log(config.BASE_URL);
    return this.httpClient.get(`${config.BASE_URL}/getsitebyuser/1`);
  }
}
