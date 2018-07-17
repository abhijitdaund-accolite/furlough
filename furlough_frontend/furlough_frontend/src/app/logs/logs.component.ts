import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {UserComponent} from '../user/user.component';
import { FurloughService} from '../furlough.service';
import { Constants } from '../constants';
import { DataService} from '../data.service';

@Component({
  selector: 'app-logs',
  templateUrl: './logs.component.html',
  styleUrls: ['./logs.component.css']
})

export class LogsComponent implements OnInit {

  logsUrl = Constants.baseUrl+'/logs';
  requestUrl = Constants.baseUrl+'/requests';
  users;
  selectedUser: UserComponent;
  reportsList={};
  userLogs=[];
  uniqueMsid=[];
  empObjlist=[];

  constructor(private router: Router,private fService: FurloughService, private dService: DataService) { }

  ngOnInit() {
    let keyValue =localStorage.getItem('key');
    if (keyValue!='1')
    this.router.navigate(['/loginPage']);

    this.fService.getUserDetails().subscribe(userList => {
      this.users=userList;
    }, error => console.log(error));

    this.fService.getCompleteLogs().subscribe(userLogs => {
      
    

      for(let i=0;i<100000;i++)
      {
        if(userLogs[i]!=undefined)
          {
        
          let empObj={
            'MSID':userLogs[i].msid,
            'NAME':userLogs[i].resourceName,
            'DATES':userLogs[i].dateAndOverlaps.map((obj)=>{return {'DATE':new Date(obj.furloughDate),'STATUS':obj.overlap}})
          }
          
          
          this.empObjlist.push(empObj);
        }
        else
        break;
      }

      console.log(this.empObjlist);
      
    })

  }

  logout()  {
    this.dService.logout();
  }
}
