import { Injectable } from '@angular/core';
import { Observable} from 'rxjs';
import { HttpClient} from '@angular/common/http';
import { Constants } from './constants';
import { Router } from '@angular/router';



@Injectable({
  providedIn: 'root'
})
export class DataService {
  private currentUser: string;
  private serverUri: string;
  
  constructor(private http: HttpClient,private router: Router) { }
  getUserDetails() {
    return this.http.get(this.serverUri);
  }

  public getData(fromDate,toDate) {
    return this.http.get(Constants.baseUrl+'/requests?from='+fromDate+'?to='+toDate);
  }
  
  logout() {
    localStorage.setItem('key','0');
    this.router.navigate(['/loginPage']);
  }

}
