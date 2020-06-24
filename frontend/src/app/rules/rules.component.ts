import {
  Component,
  OnInit,
  ElementRef,
  ViewChild,
  ViewChildren,
  QueryList,
  ViewEncapsulation,
} from '@angular/core';
import { Rule } from '../shared/models/rule';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { RulesService } from './rules.service';
import { SitesService } from '../sites/sites.service';
import { EmailTemplatesService } from '../email-templates/email-templates.service';
import { Site } from '../shared/models/site';
import moment from 'moment';
import {
  SortableHeader,
  SortEvent,
  compare,
} from '../shared/directives/SortableHeader';

@Component({
  selector: 'app-rules',
  templateUrl: './rules.component.html',
  styleUrls: ['./rules.component.sass'],
  encapsulation: ViewEncapsulation.None,
})
export class RulesComponent implements OnInit {
  @ViewChild('createRule') createRule: ElementRef;
  @ViewChildren(SortableHeader) headers: QueryList<SortableHeader>;

  public rules: Rule[] = [];
  public sortedRules: Rule[] = [];
  public rule: Rule;
  public enable: Boolean;
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
  public trigger = {
    id: -1,
  };
  public action = {};
  public actions = [];
  public templates = [];

  constructor(
    private rulesService: RulesService,
    private modalService: NgbModal,
    private sitesService: SitesService,
    private templateService: EmailTemplatesService
  ) {}

  ngOnInit(): void {
    this.rule = this.generateEmptyRule();
    this.getSites();
    this.getTemplates();
  }

  hasEmailTemplate(action) {
    return action && !!action.onEmail;
  }

  onSort({ column, direction }: SortEvent) {
    // resetting other headers
    this.headers.forEach((header) => {
      if (header.sortable !== column) {
        header.direction = '';
      }
    });

    // sorting countries
    if (direction === '' || column === '') {
      this.sortedRules = this.rules;
    } else {
      this.sortedRules = [...this.rules].sort((a, b) => {
        const res = compare(`${a[column]}`, `${b[column]}`);
        return direction === 'asc' ? res : -res;
      });
    }
  }

  getTemplates() {
    this.templateService.getTemplates().subscribe((data: any[]) => {
      this.templates = data;
      this.templates.push({
        name: 'Default',
        template: null,
      });
    });
  }

  getSites() {
    this.sitesService.getSites().subscribe((data: Site[]) => {
      this.sites = data;
      this.getRules();
    });
  }

  getFilteredActions() {
    const chosenActions = this.rule.rules.map((r) => r.systemRule.action.id);
    return this.actions.filter((t) => !chosenActions.includes(t.id));
  }

  getRules() {
    this.rules = this.rulesService.getRules(this.sites);
    this.sortedRules = this.rules;
  }

  updateSiteDependencies() {
    this.trigger = {
      id: -1,
    };
    this.action = {};
    this.getEventsByConnector();
  }

  getEventsByConnector() {
    this.rulesService
      .getEventsByConnector(this.site.connector.id)
      .subscribe((data: any[]) => {
        this.triggers = data;
      });
  }

  getActionsForEvent(eventId) {
    this.rulesService
      .getActions(eventId, this.site.connector.id)
      .subscribe((data: any[]) => {
        this.actions = data;
      });
  }

  generateEmptyRule = (): Rule => ({
    id: null,
    description: '',
    name: '',
    siteId: -1,
    rules: [],
    enabled: false,
  });

  listenForSearchAndSelect = ($event) => {
    if ($event.name === 'sites-search' && $event.value.id) {
      this.site = $event.value;
      this.updateSiteDependencies();
    } else if ($event.name === 'triggers-search' && $event.value.id) {
      this.trigger = $event.value;
      this.rule.rules = this.rule.rules.map((r) => {
        r.systemRule.systemEvent = this.trigger;
        return r;
      });
      this.getActionsForEvent($event.value.id);
    } else if ($event.name === 'actions-search' && $event.value.id) {
      this.rule.rules[$event.key]['systemRule']['action'] = $event.value;
    } else if ($event.name === 'templates-search') {
      this.rule.rules[$event.key]['emailTemplate'] = $event.value.id
        ? $event.value
        : null;
    }
  };

  addSubRule() {
    this.rule.rules.push({
      emailTemplate: null,
      id: null,
      systemRule: {
        action: {
          id: null,
        },
        connector: this.site.connector,
        systemEvent: this.trigger,
      },
    });
  }

  getFormattedDate(datetimeString) {
    return moment(datetimeString).fromNow();
  }

  removeSubRule(index) {
    this.rule.rules.splice(index, 1);
  }

  listen($event) {
    if ($event.action === 'add') {
      this.openCreateModal(this.createRule);
    }
  }

  openCreateModal(createRule) {
    this.rule = this.generateEmptyRule();
    this.modalService
      .open(createRule, { ariaLabelledBy: 'modal-basic-title' })
      .result.then(
        (onfulfilled) => {
          this.rulesService.addRule(this.site.id, this.rule).subscribe(() => {
            this.rule = this.generateEmptyRule();
            this.getSites();
          });
        },
        (onrejected) => {
          this.trigger = { id: null };
          this.site = {
            id: '',
            name: '',
            connector: {
              id: '',
            },
            siteToken: {
              token: '',
            },
          };
        }
      );
  }

  openEditModal(createRule, rule) {
    this.rule = rule;
    this.trigger = this.rule.rules[0].systemRule.systemEvent;
    const siteIndex = this.sites.findIndex((s) => s.id === rule.siteId);
    this.site = this.sites[siteIndex];
    this.getEventsByConnector();
    this.getActionsForEvent(this.trigger.id);
    this.modalService
      .open(createRule, { ariaLabelledBy: 'modal-basic-title' })
      .result.then(
        (onfulfilled) => {
          this.rulesService.editRule(this.rule).subscribe(() => {
            this.rule = this.generateEmptyRule();
            this.site = {
              id: '',
              name: '',
              connector: {
                id: '',
              },
              siteToken: {
                token: '',
              },
            };
            this.getSites();
          });
        },
        (onrejected) => {}
      );
  }
}
