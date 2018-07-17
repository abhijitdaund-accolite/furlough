import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import { DashboardComponent }   from './dashboard/dashboard.component';
import { LoginComponent } from './login/login.component';
import { UploadComponent } from './upload/upload.component';
import { LogsComponent } from './logs/logs.component';
import { UsersComponent } from './users/users.component';
import { UserComponent } from './user/user.component';
import { HomeviewComponent } from './homeview/homeview.component';

const routes: Routes = [
  { path: 'loginPage', component: LoginComponent },
  { path: '', component: LoginComponent },
  { path: 'welcomePage', component: DashboardComponent },
  { path: 'uploadPage', component: UploadComponent },
  { path: 'logsPage', component: HomeviewComponent },
  { path: 'usersPage', component: UsersComponent },
  { path: 'newUserPage', component: UserComponent },
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule],
  declarations: []
})
export class AppRoutingModule { }
