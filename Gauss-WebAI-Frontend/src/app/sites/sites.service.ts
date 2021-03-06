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
    return this.httpClient.get(
      `${config.BASE_URL}/deletesitebyid/${id}`,
      this.auth.getAuthHeader()
    );
  }

  addSites(sites) {
    const userId = this.auth.getUserId();
    return this.httpClient.post(
      `${config.BASE_URL}/addsites/${userId}`,
      sites,
      this.auth.getAuthHeader()
    );
  }

  updateSite(site) {
    const userId = this.auth.getUserId();
    return this.httpClient.post(
      `${config.BASE_URL}/updatesite/${userId}`,
      site,
      this.auth.getAuthHeader()
    );
  }
}
