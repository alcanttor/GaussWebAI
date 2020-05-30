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
  private sites;
  private site = {
    id: '',
    name: '',
    connectorId: '',
  };
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

  openCreateSiteModal(createSite) {
    this.modalService
      .open(createSite, { ariaLabelledBy: 'modal-basic-title' })
      .result.then(() => {
        const site = this.transformInputToRequestParams();
        this.sitesService.addSite(site).subscribe((data: any[]) => {
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
          this.site = {
            id: data.id,
            name: data.name,
            connectorId: data.connector.id,
          };
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
