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

  openCreateSitesModal(createSites) {
    this.inputSites.push(this.generateEmptySite());
    this.modalService
      .open(createSites, { ariaLabelledBy: 'modal-basic-title' })
      .result.then(
        (onfulfilled) => {
          this.sitesService
            .addSites(this.inputSites)
            .subscribe((data: any[]) => {
              this.site = this.generateEmptySite();
              this.getSites();
            });
        },
        (onrejected) => {}
      );
  }

  openDeleteSiteModal(deleteSite, siteId) {
    this.modalService
      .open(deleteSite, { ariaLabelledBy: 'modal-basic-title' })
      .result.then(
        (onfulfilled) => {
          this.sitesService.deleteSite(siteId).subscribe((data: any) => {
            this.site = this.generateEmptySite();
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
          this.sitesService.updateSite(site).subscribe((data: any[]) => {
            this.site = this.generateEmptySite();
            this.getSites();
          });
        },
        (onrejected) => {}
      );
  }
}
