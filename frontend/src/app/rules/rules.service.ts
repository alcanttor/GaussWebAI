import { Injectable } from '@angular/core';
import config from '../shared/config';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class RulesService {
  private token: string;
  constructor(private httpClient: HttpClient) {}

  addRule(siteId, rule) {
    return this.httpClient.post(`${config.BASE_URL}/addrule/${siteId}`, rule);
  }

  getRules(userId) {
    return this.httpClient.post(`${config.BASE_URL}/getallrules/${userId}`, {});
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
