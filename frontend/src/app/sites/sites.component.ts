import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { SitesService } from './sites.service';
import { Site } from '../shared/models/site';

@Component({
  selector: 'app-sites',
  templateUrl: './sites.component.html',
  styleUrls: ['./sites.component.sass'],
  providers: [SitesService],
})
export class SitesComponent implements OnInit {
  @ViewChild('createSites') createSites: ElementRef;
  @ViewChild('deleteModal') deleteSite: ElementRef;
  public sites: Site[] = [];
  public site: Site;
  public inputSites: Site[] = [];

  constructor(
    private sitesService: SitesService,
    private modalService: NgbModal
  ) {}

  ngOnInit(): void {
    this.getSites();
    this.inputSites.push(this.generateEmptySite());
  }

  generateEmptySite = (): Site => ({
    id: '',
    name: '',
    connector: {
      id: '',
    },
    siteToken: {
      token: '',
    },
  });

  listen($event) {
    if ($event.action === 'add') {
      this.openCreateSitesModal(this.createSites);
    } else if ($event.action === 'delete') {
      this.openDeleteSiteModal(this.deleteSite, this.sites[0].id);
    }
  }

  getSites() {
    this.sitesService.getSites().subscribe((data: any[]) => {
      this.sites = data;
    });
  }

  addSite() {
    this.inputSites.push(this.generateEmptySite());
  }

  open(content) {
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' });
  }

  removeInputSite(index) {
    this.inputSites.splice(index, 1);
  }

  openCreateSitesModal(createSites) {
    this.modalService
      .open(createSites, { ariaLabelledBy: 'modal-basic-title' })
      .result.then(
        (onfulfilled) => {
          this.sitesService.addSites(this.inputSites).subscribe(() => {
            this.site = this.generateEmptySite();
            this.getSites();
          });
        },
        (onrejected) => {
          this.inputSites = [this.generateEmptySite()];
        }
      );
  }

  openDeleteSiteModal(deleteSite, siteId) {
    this.modalService
      .open(deleteSite, { ariaLabelledBy: 'modal-basic-title' })
      .result.then(
        (onfulfilled) => {
          this.sitesService.deleteSite(siteId).subscribe(() => {
            this.getSites();
          });
        },
        (onrejected) => {}
      );
  }

  openEditSiteModal(createSite, site) {
    this.site = site;
    console.log(site);
    this.modalService
      .open(createSite, { ariaLabelledBy: 'modal-basic-title' })
      .result.then(
        (onfulfilled) => {
          this.sitesService.updateSite(site).subscribe(() => {
            this.site = this.generateEmptySite();
            this.getSites();
          });
        },
        (onrejected) => {
          this.site = this.generateEmptySite();
        }
      );
  }
}
