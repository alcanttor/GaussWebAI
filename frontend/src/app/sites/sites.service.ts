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
    return this.httpClient.get(
      `${config.BASE_URL}/getsitebyuserid/1`,
      this.auth.getAuthHeader()
    );
  }

  deleteSite(id) {
    return this.httpClient.get(`${config.BASE_URL}/deletesitebyid/${id}`);
  }

  addSites(sites) {
    return this.httpClient.post(`${config.BASE_URL}/addsites/1`, sites);
  }

  updateSite(site) {
    return this.httpClient.post(`${config.BASE_URL}/updatesite/1`, site);
  }
}
