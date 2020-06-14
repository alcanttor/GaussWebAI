import { Injectable } from '@angular/core';
import config from '../shared/config';
import { HttpClient } from '@angular/common/http';
import { AuthService } from '../auth.service';

@Injectable({
  providedIn: 'root',
})
export class EmailTemplatesService {
  constructor(private httpClient: HttpClient, private auth: AuthService) {}

  getTemplates() {
    const userId = this.auth.getUserId();
    return this.httpClient.get(
      `${config.BASE_URL}/getemailtemplatesbyuserid/${userId}`,
      this.auth.getAuthHeader()
    );
  }

  addTemplate(template) {
    const userId = this.auth.getUserId();
    return this.httpClient.post(
      `${config.BASE_URL}/addemailtemplate/${userId}`,
      template,
      this.auth.getAuthHeader()
    );
  }

  updateTemplate(template) {
    return this.httpClient.post(
      `${config.BASE_URL}/updateemailtemplate/${template.id}`,
      template,
      this.auth.getAuthHeader()
    );
  }

  delete(templateId) {
    return this.httpClient.get(
      `${config.BASE_URL}/deletetemplatebyid/${templateId}`,
      this.auth.getAuthHeader()
    );
  }
}
