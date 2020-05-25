import { Component, OnInit } from '@angular/core';
import { SitesService } from './sites.service';

@Component({
  selector: 'app-sites',
  templateUrl: './sites.component.html',
  styleUrls: ['./sites.component.sass'],
  providers: [SitesService],
})
export class SitesComponent implements OnInit {
  private sites;
  constructor(private sitesService: SitesService) {}

  ngOnInit(): void {
    this.sitesService.getSites().subscribe((data: any[]) => {
      console.log(data);
    });
  }
}
