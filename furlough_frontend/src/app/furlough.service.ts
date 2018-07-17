import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Constants } from './constants';

@Injectable({
  providedIn: 'root'
})

export class FurloughService {

  private currentUser: string;
  private msUserUrl = Constants.baseUrl + '/ms_employees' ; 
  private requestsUrl: string = Constants.baseUrl + '/requests';



  constructor(private http: HttpClient) { }
  getUserDetails() {
    return this.http.get(this.msUserUrl);
  }


  getCompleteLogs(){
    return this.http.get(this.requestsUrl);
  }

  public getData(fromDate,toDate) {
    return this.http.get(this.requestsUrl+'?from='+fromDate+'?to='+toDate);
  }

}
