import { Injectable } from '@angular/core';
import config from '../shared/config';
import { HttpClient } from '@angular/common/http';
import { AuthService } from '../auth.service';

@Injectable({
  providedIn: 'root',
})
export class EmailLabelsService {
  constructor(private httpClient: HttpClient, private auth: AuthService) {}

  addLabel(label) {
    const userId = this.auth.getUserId();
    return this.httpClient.post(
      `${config.BASE_URL}/addemaillabel/${userId}`,
      label,
      this.auth.getAuthHeader()
    );
  }

  getLabels() {
    const userId = this.auth.getUserId();
    return this.httpClient.get(
      `${config.BASE_URL}/getemaillabelsbyuserid/${userId}`,
      this.auth.getAuthHeader()
    );
  }

  updateLabel(label) {
    return this.httpClient.post(
      `${config.BASE_URL}/updateemaillabel/${label.id}`,
      label,
      this.auth.getAuthHeader()
    );
  }

  deleteLabel(labelId) {
    return this.httpClient.get(
      `${config.BASE_URL}/deleteemaillabelbyid/${labelId}`,
      this.auth.getAuthHeader()
    );
  }

  associateTemplateWithLabel(templateId, labelId) {
    return this.httpClient.get(
      `${config.BASE_URL}/associate/${labelId}/${templateId}`,
      this.auth.getAuthHeader()
    );
  }
}
