import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchAndSelectComponent } from './search-and-select.component';

describe('SearchAndSelectComponent', () => {
  let component: SearchAndSelectComponent;
  let fixture: ComponentFixture<SearchAndSelectComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchAndSelectComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchAndSelectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
