import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EmailLabelsComponent } from './email-labels.component';

describe('EmailLabelsComponent', () => {
  let component: EmailLabelsComponent;
  let fixture: ComponentFixture<EmailLabelsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmailLabelsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmailLabelsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
