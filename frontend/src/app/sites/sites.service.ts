import { Injectable } from '@angular/core';
import config from '../shared/config';
import { HttpClient } from '@angular/common/http';
import { AuthService } from '../auth.service';

@Injectable({
  providedIn: 'root',
})
export class SitesService {
  constructor(private httpClient: HttpClient, private auth: AuthService) {}

  getSites() {
    const userId = this.auth.getUserId();
    return this.httpClient.get(
      `${config.BASE_URL}/getsitebyuserid/${userId}`,
      this.auth.getAuthHeader()
    );
  }

  deleteSite(id) {
    const userId = this.auth.getUserId();
    return this.httpClient.get(`${config.BASE_URL}/deletesitebyid/${id}`);
  }

  addSites(sites) {
    const userId = this.auth.getUserId();
    return this.httpClient.post(`${config.BASE_URL}/addsites/${userId}`, sites);
  }

  updateSite(site) {
    const userId = this.auth.getUserId();
    return this.httpClient.post(
      `${config.BASE_URL}/updatesite/${userId}`,
      site
    );
  }
}
