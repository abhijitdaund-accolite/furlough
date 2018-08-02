import { Injectable } from '@angular/core';
import {HttpClient, HttpEvent, HttpParams, HttpRequest} from '@angular/common/http';
import {Constants} from './constants';
import {Observable} from 'rxjs';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  private currentUser: string;
  private serverUri: string;
  constructor(private http: HttpClient, private router: Router) { }
  getUserDetails() {
    return this.http.get(Constants.employeesUrl());
  }
  getData(startDate, endDate) {
    startDate = this.reformatDate(startDate);
    endDate = this.reformatDate(endDate);
    return this.http.get(Constants.reportsUrl(startDate, endDate));
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
    this.router.navigate(['/login']);
  }
  getFilesList() {
    return this.http.get(Constants.filesUrl());
  }
  addNewUser(employeeDetails) {
    console.log(employeeDetails);
    return this.http.post(Constants.saveNewUser(), employeeDetails);
  }
  editUser(msid, employeeDetails) {
    console.log(msid);
    console.log(employeeDetails);
    return this.http.put(Constants.editUser(msid), employeeDetails);
  }
  deactivateUser(msid, employeeDetails) {
    console.log(msid);
    console.log(employeeDetails);
    return this.http.put(Constants.deactivateUser(msid), employeeDetails);
  }
  pushFileToStorage(file: File): Observable<HttpEvent<{}>> {
    const formdata: FormData = new FormData();
    const url = Constants.fileUploadUrl();
    formdata.append('file', file);
    const request = new HttpRequest('POST', url , formdata, {
      reportProgress: true,
      responseType: 'text'
    });
    return this.http.request(request);
  }
}
