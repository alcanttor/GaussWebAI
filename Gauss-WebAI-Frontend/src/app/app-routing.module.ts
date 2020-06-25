import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { SitesComponent } from './sites/sites.component';
import { RulesComponent } from './rules/rules.component';
import { AuthGuard } from './shared/guards/auth.guard';
import { EmailLabelsComponent } from './email-labels/email-labels.component';
import { EmailTemplatesComponent } from './email-templates/email-templates.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  {
    path: 'home',
    component: DashboardComponent,
    canActivateChild: [AuthGuard],
    children: [
      {
        path: '',
        component: SitesComponent,
      },
      {
        path: 'rules',
        component: RulesComponent,
      },
      {
        path: 'emails',
        children: [
          {
            path: 'labels',
            component: EmailLabelsComponent,
          },
          {
            path: 'templates',
            component: EmailTemplatesComponent,
          },
        ],
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
