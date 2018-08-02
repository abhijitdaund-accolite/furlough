import { Component, OnInit } from '@angular/core';
import{DataService} from '../data.service';
@Component({
  selector: 'app-topbar',
  templateUrl: './topbar.component.html',
  styleUrls: ['./topbar.component.css']
})
export class TopbarComponent implements OnInit {

  constructor(private dService: DataService) { }

  ngOnInit() {
  }

  logout(){
    this.dService.logout();
  }

}
