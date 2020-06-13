import { BrowserModule } from '@angular/platform-browser';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ClipboardModule } from 'ngx-clipboard';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { IconsModule } from './icons/icons.module';

import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';

import { AuthService } from './auth.service';
import { SitesComponent } from './sites/sites.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ListActionsComponent } from './shared/components/list-actions/list-actions.component';
import { RulesComponent } from './rules/rules.component';
import { SearchAndSelectComponent } from './shared/components/search-and-select/search-and-select.component';
import { EmailLabelsComponent } from './email-labels/email-labels.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
    SitesComponent,
    ListActionsComponent,
    RulesComponent,
    SearchAndSelectComponent,
    EmailLabelsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    NgbModule,
    HttpClientModule,
    IconsModule,
    ClipboardModule,
  ],
  providers: [AuthService],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class AppModule {}
