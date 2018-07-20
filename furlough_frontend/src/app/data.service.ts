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

  public getData(startDate, endDate) {
    startDate = this.reformatDate(startDate);
    endDate = this.reformatDate(endDate);
    return this.http.get(Constants.baseUrl + '/requests?from=' + startDate + '+&to=' + endDate );
  }

  reformatDate(dateObj): string {
    const dYear = dateObj.getFullYear();
    let dMonth = dateObj.getMonth() + 1;
    let dDate =  dateObj.getDate();
    if (dMonth < 10)
      dMonth = '0' + dMonth;
    if (dDate < 10)
      dDate = '0' + dDate;
    return (dYear + '-' + dMonth + '-' + dDate).toString();
  }
  logout() {
    localStorage.setItem('key','0');
    this.router.navigate(['/loginPage']);
  }

}
