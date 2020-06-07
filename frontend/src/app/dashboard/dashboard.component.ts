import { MediaMatcher } from '@angular/cdk/layout';
import { Component, OnInit, OnDestroy, ChangeDetectorRef } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.sass'],
})
export class DashboardComponent implements OnInit, OnDestroy {
  mobileQuery: MediaQueryList;

  sideNavItems = [
    {
      title: 'Sites',
      link: 'sites',
    },
    {
      title: 'Rules',
      link: 'rules',
    },
    {
      title: 'Emails',
      link: 'emails',
    },
  ];

  private _mobileQueryListener: () => void;

  constructor(
    changeDetectorRef: ChangeDetectorRef,
    media: MediaMatcher,
    private authService: AuthService,
    private router: Router
  ) {
    this.mobileQuery = media.matchMedia('(max-width: 600px)');
    this._mobileQueryListener = () => changeDetectorRef.detectChanges();
    this.mobileQuery.addListener(this._mobileQueryListener);
  }

  ngOnDestroy(): void {
    this.mobileQuery.removeListener(this._mobileQueryListener);
  }

  ngOnInit(): void {}

  logout() {
    this.authService.logout();
    this.router.navigate(['login']);
  }
}
