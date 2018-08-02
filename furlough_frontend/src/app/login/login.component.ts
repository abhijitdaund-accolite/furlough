import { Component, OnInit } from '@angular/core';
import { DataService} from '../data.service';
import { FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Constants } from '../constants';
import {Md5} from 'ts-md5/dist/md5';

interface loginResponse {
  adminDetails: string;
  userFound: boolean;
}

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [DataService]
})

export class LoginComponent implements OnInit {

  userLogin = {'email':"" , 'password':""};
  errorMessage = true;
  logoImagePath: string;
  constructor(private router: Router,private http:HttpClient) {
    this.logoImagePath = 'assets/Logo.png';
   }

  ngOnInit() {
    localStorage.setItem('key', "0");
  }
  
  onSubmit() {
    console.log(this.userLogin);
    //sha256('hello');
    let password = Md5.hashStr(this.userLogin.password);
    this.userLogin.password = password.toString();
    console.log(this.userLogin.password);
    this.http.post(Constants.loginUrl(), this.userLogin)
      .subscribe ( data => {
          console.log(data); 
          localStorage.setItem('key', "1");
          if(data == true)
            this.router.navigate(['/employees']);
          else
            this.errorMessage=false;
        } ,
        error => {console.log(error);this.errorMessage=false;});
   }
}