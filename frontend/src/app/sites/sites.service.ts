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
    return this.httpClient.get(`${config.BASE_URL}/getsitebyuserid/1`);
  }

  deleteSite(id) {
    return this.httpClient.get(`${config.BASE_URL}/deletesitebyid/${id}`);
  }

  addSite(site) {
    return this.httpClient.post(`${config.BASE_URL}/addsite/1`, site);
  }

  updateSite(site) {
    return this.httpClient.post(`${config.BASE_URL}/updatesite/1`, site);
  }
}
