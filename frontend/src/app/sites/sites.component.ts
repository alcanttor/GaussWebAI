import { Component, OnInit } from '@angular/core';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { SitesService } from './sites.service';

@Component({
  selector: 'app-sites',
  templateUrl: './sites.component.html',
  styleUrls: ['./sites.component.sass'],
  providers: [SitesService],
})
export class SitesComponent implements OnInit {
  public sites;
  public site = {
    id: '',
    name: '',
    connectorId: '',
  };
  public inputSites = [
    {
      id: '',
      name: '',
      connectorId: '',
    },
  ];

  constructor(
    private sitesService: SitesService,
    private modalService: NgbModal
  ) {}

  ngOnInit(): void {
    this.getSites();
  }

  getSites() {
    this.sitesService.getSites().subscribe((data: any[]) => {
      this.sites = data;
    });
  }

  addSite() {
    this.inputSites.push({
      id: '',
      name: '',
      connectorId: '',
    });
  }

  open(content) {
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' });
  }

  transformInputToRequestParams() {
    const transformedSite = {
      id: this.site.id,
      name: this.site.name,
      connector: {
        id: this.site.connectorId,
      },
    };
    return transformedSite;
  }

  openCreateSitesModal(createSites) {
    this.modalService
      .open(createSites, { ariaLabelledBy: 'modal-basic-title' })
      .result.then(() => {
        const sites = this.inputSites.map((site) => ({
          id: site.id,
          name: site.name,
          connector: {
            id: site.connectorId,
          },
        }));

        this.sitesService.addSites(sites).subscribe((data: any[]) => {
          this.sites = [];
          this.site = {
            id: '',
            name: '',
            connectorId: '',
          };
          this.getSites();
        });
      });
  }

  openDeleteSiteModal(deleteSite, siteId) {
    this.modalService
      .open(deleteSite, { ariaLabelledBy: 'modal-basic-title' })
      .result.then(() => {
        this.sitesService.deleteSite(siteId).subscribe((data: any) => {
          // this.site = {
          //   id: data.id,
          //   name: data.name,
          //   connectorId: data.connector.id,
          // };
          this.getSites();
        });
      });
  }

  openEditSiteModal(createSite, site) {
    this.site = {
      id: site.id,
      name: site.name,
      connectorId: site.connector.id,
    };
    this.modalService
      .open(createSite, { ariaLabelledBy: 'modal-basic-title' })
      .result.then(() => {
        const site = this.transformInputToRequestParams();
        this.sitesService.updateSite(site).subscribe((data: any[]) => {
          this.site = {
            id: '',
            name: '',
            connectorId: '',
          };
          this.getSites();
        });
      });
  }
}
