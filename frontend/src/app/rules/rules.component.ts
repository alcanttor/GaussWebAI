import {
  Component,
  OnInit,
  ElementRef,
  ViewChild,
  ViewEncapsulation,
} from '@angular/core';
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
  public actions = {};

  constructor(
    private rulesService: RulesService,
    private modalService: NgbModal,
    private sitesService: SitesService
  ) {}

  ngOnInit(): void {
    this.getRules();
    this.rule = this.generateEmptyRule();
    this.sitesService.getSites().subscribe((data: Site[]) => {
      this.sites = data;
    });
  }

  getRules() {
    this.rulesService.getRules().subscribe((data: any[]) => {
      this.rules = data;
    });
  }

  updateSiteDependencies() {
    this.trigger = {};
    this.action = {};
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
    rules: [],
  });

  listenForSearchAndSelect = ($event) => {
    console.log($event);
    if ($event.name === 'sites-search' && $event.value.id) {
      this.site = $event.value;
      this.updateSiteDependencies();
    } else if ($event.name === 'triggers-search' && $event.value.id) {
      this.rule.rules[$event.key]['systemRule']['systemEvent'] = $event.value;
      this.getActionsForEvent($event.value.id);
    } else if ($event.name === 'actions-search' && $event.value.id) {
      this.rule.rules[$event.key]['systemRule']['action'] = $event.value;
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
        systemEvent: {
          id: null,
        },
      },
    });
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
    this.modalService
      .open(createRule, { ariaLabelledBy: 'modal-basic-title' })
      .result.then(
        (onfulfilled) => {
          console.log(this.rule);
          this.rulesService.addRule(this.site.id, this.rule).subscribe(() => {
            this.rule = this.generateEmptyRule();
            this.getRules();
          });
        },
        (onrejected) => {}
      );
  }

  openEditModal(createRule, rule) {
    this.rule = rule;
    console.log(rule);
    this.modalService
      .open(createRule, { ariaLabelledBy: 'modal-basic-title' })
      .result.then(
        (onfulfilled) => {
          console.log(this.rule);
          // this.rulesService.addRule(this.site.id, this.rule).subscribe(() => {
          //   this.rule = this.generateEmptyRule();
          //   this.getRules();
          // });
        },
        (onrejected) => {}
      );
  }
}
