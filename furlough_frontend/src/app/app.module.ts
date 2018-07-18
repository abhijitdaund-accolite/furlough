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
import { HomeviewComponent } from './homeview/homeview.component';
import { Ng2SearchPipeModule } from 'ng2-search-filter';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
    ReportsComponent,
    UsersComponent,
    UserComponent,
    LogsComponent,
    UploadComponent,
    HomeviewComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    Ng2SearchPipeModule
  ],
  providers: [FurloughService],
  bootstrap: [AppComponent]
})
export class AppModule { }
