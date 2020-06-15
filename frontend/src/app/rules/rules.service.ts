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
    return this.httpClient.post(
      `${config.BASE_URL}/addrulegroup/${siteId}`,
      rule,
      this.auth.getAuthHeader()
    );
  }

  getRules(sites) {
    const ruleGroups = [];
    sites.map((site) => {
      site.ruleGroups.map((rg) => {
        ruleGroups.push({ ...rg, siteId: site.id });
      });
    });

    return ruleGroups;
  }

  deleteRule(id) {
    return this.httpClient.get(
      `${config.BASE_URL}/deleterulebyid/${id}`,
      this.auth.getAuthHeader()
    );
  }

  editRule(rule) {
    return this.httpClient.post(
      `${config.BASE_URL}/updaterule`,
      rule,
      this.auth.getAuthHeader()
    );
  }

  getEventsByConnector(connectorId) {
    return this.httpClient.get(
      `${config.BASE_URL}/geteventsbyconnector/${connectorId}`,
      this.auth.getAuthHeader()
    );
  }

  getActions(eventId, connectorId) {
    return this.httpClient.get(
      `${config.BASE_URL}/getactionsbyconnectorsandactions/${connectorId}/${eventId}`,
      this.auth.getAuthHeader()
    );
  }
}
