import { Component, OnInit } from '@angular/core';
import { UserComponent } from '../user/user.component';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { FurloughService} from '../furlough.service';
import { DataService} from '../data.service';
import { Constants } from '../constants';

interface msUser {
  resourceName: string;
  vendorName: string;
  division: string;
  officeLocation: string;
  msid: string;
}

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css'],
  providers: [FurloughService]
})

export class UsersComponent implements OnInit {
 
  employeeUrl = Constants.baseUrl+'/ms_employees';
  users;
  selectedUser: UserComponent; 
 
  constructor(private router: Router,private fService: FurloughService, private dService: DataService) { }

  ngOnInit() {
    let keyValue =localStorage.getItem('key');
    if (keyValue!='1')
    this.router.navigate(['/loginPage']);
    
    this.fService.getUserDetails().subscribe(userList => {
      this.users=userList;
    }, error => console.log(error));

  }
 
  onSelect(UserComponent: UserComponent): void {
    this.selectedUser = UserComponent;
  }

  logout()  {
    this.dService.logout();
  }

  gotoNewUserPage() {
    this.router.navigate(['/newUserPage']);
  }

}