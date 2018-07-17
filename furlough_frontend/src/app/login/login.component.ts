import { Component, OnInit } from '@angular/core';
import { DataService} from '../data.service';
import { FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
//import { url } from './UrlRepository.ts';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [DataService]
})
export class LoginComponent implements OnInit {

  userLogin = {'username':"" , 'password':""};
  errorMessage = true;
  constructor(private router: Router,private http:HttpClient) { }

  ngOnInit() {
    localStorage.setItem('key', "0");

  }
  
  onSubmit() {
    
//    this.http.post(url,this.userLogin).subscribe(result => {  console.log('DONE'); console.log(result)});

     if (this.userLogin.username=='rahul' && this.userLogin.password=='pareek')
      {
         localStorage.setItem('key', "1");
         localStorage.setItem('username',this.userLogin.username);
         this.router.navigate(['/welcomePage']);
      }
    else
      this.errorMessage=false;
  }

}