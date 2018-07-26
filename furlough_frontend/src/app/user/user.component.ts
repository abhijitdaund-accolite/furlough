import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';
import {RequestOptions, Request, RequestMethod} from '@angular/http';
import { HttpClient } from '@angular/common/http';
import { Constants } from '../constants';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  constructor(private router: Router,private http: HttpClient) { }
  check;
  employeeInput = {'mSID':"" , 'accoliteEmployee':"", 'resourceName':"" , 'vendorName':"",'division':"" , 'officeLocation':"" , 'email':""};

  ngOnInit() {
    this.check=0;
  }

  onClick() {

    console.log("=============================================");
    console.log(this.employeeInput);
    console.log("=============================================");
    this.http.post(Constants.baseUrl+'/ms_employees', this.employeeInput)
      .subscribe ( (data) => console.log(data) );
    this.check=1;
  }

}
