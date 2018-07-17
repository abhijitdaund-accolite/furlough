import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DataService } from '../data.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})

export class DashboardComponent implements OnInit {

  constructor(private router: Router, private dService: DataService) { }
  
  ngOnInit() {
    let keyValue =localStorage.getItem('key');
    let userName=localStorage.getItem('username');
    if (keyValue!='1')
      this.router.navigate(['/loginPage']);
    
  }

  logout()  {
    this.dService.logout();
  }

}