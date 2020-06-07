import {
  Component,
  OnInit,
  ElementRef,
  ViewChild,
  ViewEncapsulation,
} from '@angular/core';
import { NgbTypeahead } from '@ng-bootstrap/ng-bootstrap';
import { Observable, Subject, merge } from 'rxjs';
import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';
import { Rule } from '../shared/models/rule';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { RulesService } from './rules.service';
import { SitesService } from '../sites/sites.service';
import { Site } from '../shared/models/site';

@Component({
  selector: 'app-rules',
  templateUrl: './rules.component.html',
  styleUrls: ['./rules.component.sass'],
  encapsulation: ViewEncapsulation.None,
})
export class RulesComponent implements OnInit {
  @ViewChild('createRule') createRule: ElementRef;
  @ViewChild('deleteModal') deleteRule: ElementRef;

  @ViewChild('instance') instance: NgbTypeahead;
  @ViewChild('triggerInstance') triggerInstance: NgbTypeahead;
  siteFocus$ = new Subject<string>();
  siteClick$ = new Subject<string>();

  triggerFocus$ = new Subject<string>();
  triggerClick$ = new Subject<string>();

  public rules: Rule[] = [];
  public rule: Rule;
  public site: Site = {
    id: '',
    name: '',
    connector: {
      id: '',
    },
    siteToken: {
      token: '',
    },
  };
  public sites: Site[] = [];
  public triggers = [];
  public trigger = {};
  public action = {};

  constructor(
    private rulesService: RulesService,
    private modalService: NgbModal,
    private sitesService: SitesService
  ) {}

  ngOnInit(): void {
    this.rulesService.getRules(1).subscribe((data: any[]) => {
      this.rules = data;
    });
    this.rule = this.generateEmptyRule();
    this.sitesService.getSites().subscribe((data: Site[]) => {
      this.sites = data;
    });
  }

  siteFormatter = (x: Site) => x.name;

  triggerFormatter = (x: any) => x.name;

  siteClickEvents($event, typeaheadInstance) {
    if (typeaheadInstance.isPopupOpen()) {
      this.siteClick$.next($event.target.value);
    }
  }

  triggerClickEvents($event, typeaheadInstance) {
    if (typeaheadInstance.isPopupOpen()) {
      this.triggerClick$.next($event.target.value);
    }
  }

  siteSearch = (text$: Observable<string>) => {
    const debouncedText$ = text$.pipe(
      debounceTime(200),
      distinctUntilChanged()
    );

    return merge(debouncedText$, this.siteFocus$, this.siteClick$).pipe(
      map((term) =>
        (term === ''
          ? this.sites
          : this.sites.filter(
              (v) => v.name.toLowerCase().indexOf(term.toLowerCase()) > -1
            )
        ).slice(0, 10)
      )
    );
  };

  triggerSearch = (text$: Observable<string>) => {
    const debouncedText$ = text$.pipe(
      debounceTime(200),
      distinctUntilChanged()
    );

    return merge(debouncedText$, this.triggerFocus$, this.triggerClick$).pipe(
      map((term) =>
        (term === ''
          ? this.triggers
          : this.triggers.filter(
              (v) => v.name.toLowerCase().indexOf(term.toLowerCase()) > -1
            )
        ).slice(0, 10)
      )
    );
  };

  updateSiteDependencies() {
    this.trigger = {};
    this.action = {};
    this.rulesService
      .getEventsByConnector(this.site.connector.id)
      .subscribe((data: any[]) => {
        this.triggers = data;
        console.log(this.triggers);
      });
  }

  generateEmptyRule = (): Rule => ({
    id: null,
    description: '',
    name: '',
    action: {
      id: null,
      name: '',
    },
    connector: {
      id: null,
      name: '',
    },
    systemEvent: {
      id: null,
      description: '',
    },
  });

  listen($event) {
    if ($event.action === 'add') {
      this.openCreateModal(this.createRule);
    } else if ($event.action === 'delete') {
      this.openDeleteModal(this.deleteRule, this.rules[0].id);
    }
  }

  openCreateModal(createRule) {
    this.modalService
      .open(createRule, { ariaLabelledBy: 'modal-basic-title' })
      .result.then(
        (onfulfilled) => {
          this.rulesService.addRule(this.site.id, this.rule).subscribe(() => {
            this.rule = this.generateEmptyRule();
            this.rulesService.getRules(1);
          });
        },
        (onrejected) => {}
      );
  }

  openDeleteModal(deleteRule, ruleId) {
    this.modalService
      .open(deleteRule, { ariaLabelledBy: 'modal-basic-title' })
      .result.then(
        (onfulfilled) => {
          this.rulesService.deleteRule(ruleId).subscribe(() => {
            this.rulesService.getRules(1);
          });
        },
        (onrejected) => {}
      );
  }
}
