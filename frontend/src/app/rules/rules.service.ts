import { Injectable } from '@angular/core';
import config from '../shared/config';
import { HttpClient } from '@angular/common/http';
import { AuthService } from '../auth.service';

@Injectable({
  providedIn: 'root',
})
export class RulesService {
  constructor(private httpClient: HttpClient, private auth: AuthService) {}

  addRule(siteId, rule) {
    return this.httpClient.post(`${config.BASE_URL}/addrule/${siteId}`, rule);
  }

  getRules(userId) {
    return this.httpClient.post(
      `${config.BASE_URL}/getallrules/${userId}`,
      this.auth.getAuthHeader()
    );
  }

  deleteRule(id) {
    return this.httpClient.get(`${config.BASE_URL}/deleterulebyid/${id}`);
  }

  editRule(rule) {
    return this.httpClient.post(`${config.BASE_URL}/updaterule`, rule);
  }

  getEventsByConnector(connectorId) {
    return this.httpClient.get(
      `${config.BASE_URL}/getEventsbyConnector/${connectorId}`
    );
  }

  getActions(eventId, connectorId) {
    return this.httpClient.get(
      `${config.BASE_URL}/getActionsByConnectorsAndActions/${connectorId}/${eventId}`
    );
  }
}
