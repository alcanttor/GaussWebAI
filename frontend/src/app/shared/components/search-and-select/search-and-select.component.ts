import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';
import { Observable, Subject, merge } from 'rxjs';

@Component({
  selector: 'app-search-and-select',
  templateUrl: './search-and-select.component.html',
  styleUrls: ['./search-and-select.component.sass'],
})
export class SearchAndSelectComponent implements OnInit {
  @Input() options = [];
  @Input() key = -1;
  @Input() selected = {
    id: null,
    key: this.key,
  };
  @Input() label = 'name';
  @Input() name = '';
  @Input() disabled = false;
  @Output() onSearch = new EventEmitter<object>();
  private debouncer: Subject<string> = new Subject<string>();
  click$ = new Subject<string>();
  focus$ = new Subject<string>();
  public entity;

  constructor() {}

  ngOnInit(): void {
    if (this.selected) {
      this.entity = this.selected;
    }
  }

  inputFormatter = (x: any) => x[this.label];

  updateDependencies = () => {
    this.onSearch.emit({
      action: 'update-input',
      key: this.key,
      value: this.entity,
      name: this.name,
    });
  };

  clickEvents($event, typeaheadInstance) {
    if (typeaheadInstance.isPopupOpen()) {
      this.click$.next($event.target.value);
    }
  }

  search = (text$: Observable<string>) => {
    const debouncedText$ = text$.pipe(
      debounceTime(200),
      distinctUntilChanged()
    );

    return merge(debouncedText$, this.focus$, this.click$).pipe(
      map((term) =>
        (term === ''
          ? this.options
          : this.options.filter(
              (v) =>
                v[this.label].toLowerCase().indexOf(term.toLowerCase()) > -1
            )
        ).slice(0, 10)
      )
    );
  };
}
