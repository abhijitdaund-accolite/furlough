import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AppRoutingModule } from './/app-routing.module';
import { ReportsComponent } from './reports/reports.component';
import { UsersComponent } from './users/users.component';
import { UserComponent } from './user/user.component';
import { LogsComponent } from './logs/logs.component';
import { UploadComponent } from './upload/upload.component';
import { FurloughService } from './furlough.service';

import { Ng2SearchPipeModule } from 'ng2-search-filter';
import {ReportviewComponent} from './reportview/reportview.component';
import {CountOverlappedPipe, CountPlannedPipe} from './countPlanned.pipe';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatNativeDateModule, MatSelectModule} from '@angular/material';
import {HomeviewComponent} from './homeview/homeview.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
    HomeviewComponent,
    ReportsComponent,
    UsersComponent,
    UserComponent,
    LogsComponent,
    UploadComponent,
    ReportviewComponent,
    CountPlannedPipe,
    CountOverlappedPipe
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    Ng2SearchPipeModule,
    BrowserAnimationsModule,
    MatSelectModule,
    MatNativeDateModule
  ],
  providers: [FurloughService],
  bootstrap: [AppComponent]
})
export class AppModule { }
