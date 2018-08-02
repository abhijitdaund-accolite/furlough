import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DataService } from '../data.service';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {

  constructor(private router: Router, private dService: DataService) { }

  ngOnInit() {
    let keyValue =localStorage.getItem('key');
    if (keyValue!='1')
      this.router.navigate(['/login']);
  }

  logout()  {
    this.dService.logout();
  }

}
