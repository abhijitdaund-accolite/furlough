import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { FurloughMaterialThemeModule } from './furlough-material-theme/furlough-material-theme.module';
import { AppComponent } from './app.component';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {RouterModule, Routes} from '@angular/router';
import { ReportViewComponent } from './report-view/report-view.component';
import { LoginComponent } from './login/login.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { UploadComponent } from './upload/upload.component';
import { EmployeeComponent } from './employee/employee.component';
import {LossCalculatorPipe} from './loss-calculator.pipe';
import {FormatMonthPipe} from './format-month.pipe';
import {CountOverlappedPipe, CountPlannedPipe} from './countPlanned.pipe';
import { ReporttestComponent } from './reporttest/reporttest.component';
import { AddNewUserComponent } from './add-new-user/add-new-user.component';
// import { AppRoutingModule } from './/app-routing.module';
import { WelcomeComponent } from './welcome/welcome.component';
import { TopbarComponent } from './topbar/topbar.component';
import { ToasterService } from './toaster.service';
const furloughRoutes: Routes = [
  { path: '', component: LoginComponent},
  { path: 'login', component: LoginComponent},
  { path: 'reports', component: ReporttestComponent},
  { path: 'employees', component: EmployeeComponent},
  { path: 'uploads', component: UploadComponent},
  { path: 'welcome', component: WelcomeComponent}
]

@NgModule({
  declarations: [
    AppComponent,
    ReportViewComponent,
    LoginComponent,
    UploadComponent,
    EmployeeComponent,
    LossCalculatorPipe,
    FormatMonthPipe,
    CountPlannedPipe,
    CountOverlappedPipe,
    ReporttestComponent,
    AddNewUserComponent,
    WelcomeComponent,
    TopbarComponent
  ],
  imports: [
    RouterModule.forRoot(furloughRoutes, {enableTracing: false}),
    BrowserModule,
    BrowserAnimationsModule,
    FurloughMaterialThemeModule,
    HttpClientModule,
    FormsModule,
    // AppRoutingModule
  ],
  providers: [HttpClientModule,ToasterService],
  entryComponents: [AddNewUserComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
