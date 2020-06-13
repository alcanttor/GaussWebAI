import { TestBed } from '@angular/core/testing';

import { EmailLabelsService } from './email-labels.service';

describe('EmailLabelsService', () => {
  let service: EmailLabelsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EmailLabelsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
