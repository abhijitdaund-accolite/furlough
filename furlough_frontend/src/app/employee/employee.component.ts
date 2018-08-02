import {Component, OnInit, ViewChild, Inject} from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import {DataService} from '../data.service';
import {CollectionViewer, DataSource} from '@angular/cdk/collections';
import {BehaviorSubject, Observable, of, Subject} from 'rxjs';
import {catchError, finalize} from 'rxjs/operators';
import {Router} from '@angular/router';
import {MatSort} from '@angular/material';
import {Sort} from '@angular/material';
import {AddNewUserComponent} from '../add-new-user/add-new-user.component';
import { ToasterService } from '../toaster.service';
import {MatTableDataSource} from '@angular/material';

interface User {
  MSID: string;
  NAME: string;
  VENDOR: string;
  DIVISION: string;
  OFFICE_LOCATION: string;
  EMAIL: string;
  ACCOLITE_EMPLOYEE: string;
  'ACTIVE': boolean;
}

enum RESPONSE_STATUS {
  OK, ERROR, VOID
}
enum CRUD {
  VOID, NEW, EDIT, DEACTIVATE
}

export class UserDataSource implements DataSource<User> {
  private userSubject = new BehaviorSubject<User[]>([]);
  private loadingSubject = new BehaviorSubject<Boolean>(false);
  public loading$ = this.loadingSubject.asObservable();
  public userCompleteList = [];
  public totalObj = 0;
  constructor(private data: DataService) {}
  connect(collectionViewer: CollectionViewer): Observable<User[]> {
    return this.userSubject.asObservable();
  }
  disconnect(collectionViewer: CollectionViewer): void {
    this.loadingSubject.complete();
    this.userSubject.complete();
  }
  returnList() {
    return this.userSubject.asObservable();
  }
  loadUsers() {
    this.loadingSubject.next(true);
    this.data.getUserDetails().pipe(
      catchError(() => of([])),
      finalize(() => this.loadingSubject.next(false))
    ).subscribe(users => {
      const userList: User[] = [];
      for (const index in users) {
        userList[index] = {
          MSID: users[index].mSID,
          NAME: users[index].resourceName,
          VENDOR: users[index].vendorName,
          DIVISION: users[index].division,
          OFFICE_LOCATION: users[index].officeLocation,
          EMAIL: users[index].email,
          ACCOLITE_EMPLOYEE: users[index].accoliteEmployee,
          ACTIVE: users[index].active
        };
      }
      this.totalObj =  userList.length;
      this.userCompleteList = userList;
      this.userSubject.next(userList);
    });
  }
  sortData(sort: Sort) {
    const data = this.userCompleteList.slice();
    let sortedData = this.userCompleteList;
    if (!sort.active || sort.direction === '') {
      return;
    }
    sortedData = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'MSID' : return this.compare(a.MSID, b.MSID, isAsc);
        case 'NAME' : return this.compare(a.NAME, b.NAME, isAsc);
        default: return 0;
      }
    });
    this.userSubject.next(sortedData);
  }
  compare(a, b, isAsc) {
    return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
  }
}
@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css'],
  providers: [DataService]
})
export class EmployeeComponent implements OnInit {
  public userList;
  public userListHeaders;
  public dataSource: UserDataSource;
  public currentSelectedUserDetails;
  public editViewActive;
  public anyDataSelected;
  public currentDirtyUser;
  public status;
  public dataSource1 ;

  @ViewChild(MatSort) sort: MatSort;

  constructor(private dService: DataService, private router: Router, public dialog: MatDialog, private tService: ToasterService) {
    this.userList = [];
    this.userListHeaders = ['MSID', 'NAME', 'ACCOLITE_EMPLOYEE', 'EMAIL'];
    this.currentSelectedUserDetails = {};
    this.currentDirtyUser = {};
    this.editViewActive = false;
    this.anyDataSelected = false;
    this.status = {'TYPE': CRUD.VOID, 'STATUS' : RESPONSE_STATUS.VOID};
  }
  ngOnInit() {
    let keyValue =localStorage.getItem('key');
    if (keyValue!='1')
      this.router.navigate(['/login']);
      
    this.dataSource = new UserDataSource(this.dService);
    this.dataSource.loadUsers();
    this.dataSource.returnList().subscribe(uList =>{ console.log(uList);
    this.dataSource1 = new MatTableDataSource(uList);});
  }
  onClickedUserRow(userRow) {
    this.anyDataSelected = true;
    this.currentSelectedUserDetails = userRow;
    this.currentDirtyUser = {
      'MSID': this.currentSelectedUserDetails.MSID,
      'NAME': this.currentSelectedUserDetails.NAME,
      'EMAIL': this.currentSelectedUserDetails.EMAIL,
      'ACCOLITE_EMPLOYEE' : this.currentSelectedUserDetails.ACCOLITE_EMPLOYEE
    };
  }
  addNewUser() {
    const dialogRef = this.dialog.open(AddNewUserComponent, {
      width: '450px'
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      this.status.TYPE = CRUD.NEW;
      const formattedEmployeeInput = {
        'mSID': result.mSID,
        'email': result.email,
        'accoliteEmployee': result.accoliteEmployee,
        'resourceName': result.resourceName,
        'active': true
      };
      this.dService.addNewUser(formattedEmployeeInput).
      subscribe ( data => {
                      console.log(data); 
                      this.status.STATUS = RESPONSE_STATUS.OK;
                      this.tService.Success("Success","User successfully added");
                      setTimeout(() => {location.reload();} ,2000);  } ,
        error => {
                      console.log(error); 
                      this.status.STATUS = RESPONSE_STATUS.ERROR;
                      this.tService.Error("Error","User creation failed"); });
    });
    
  }
  editHandler() {
    this.editViewActive = true;
  }
  clearHandler() {
    this.currentDirtyUser.NAME = '';
    this.currentDirtyUser.EMAIL = '';
    this.currentDirtyUser.MSID = '';
    this.currentDirtyUser.ACCOLITE_EMPLOYEE = '';
  }
  editSaveHandler() {
    this.status.TYPE = CRUD.EDIT;
    const formattedEmployeeData = {
      'mSID': this.currentDirtyUser.MSID,
      'accoliteEmployee': this.currentDirtyUser.ACCOLITE_EMPLOYEE,
      'resourceName': this.currentDirtyUser.NAME,
      'email': this.currentDirtyUser.EMAIL,
    };
    this.dService.editUser(this.currentSelectedUserDetails.MSID, formattedEmployeeData)
      .subscribe(data => {console.log(data); this.status.STATUS = RESPONSE_STATUS.OK ; },
        error => {console.log(error); this.status.STATUS = RESPONSE_STATUS.ERROR ; });
  }
  closeEditView () {
    this.editViewActive = false;
  }
  logout()  {
    this.dService.logout();
  }
  
  toggleDeactivateUser(activeValue) {
    const formattedEmployeeData = {
      'mSID': this.currentSelectedUserDetails.MSID,
      'accoliteEmployee': this.currentSelectedUserDetails.ACCOLITE_EMPLOYEE,
      'resourceName': this.currentSelectedUserDetails.NAME,
      'email': this.currentSelectedUserDetails.EMAIL,
      'active': activeValue
    };
    this.status.TYPE = CRUD.DEACTIVATE;
    this.dService.deactivateUser(this.currentSelectedUserDetails.MSID, formattedEmployeeData)
      .subscribe(data => { console.log(data); this.status.STATUS = RESPONSE_STATUS.OK; },
        error => { console.log(error) ; this.status.STATUS = RESPONSE_STATUS.ERROR; } );
    this.tService.Success("Success","User status successfully changed");
    setTimeout(() => {location.reload();} ,2000);
  }
  applyFilter(filterValue: string) {
    this.dataSource.returnList().subscribe(uList =>{ console.log(uList);
      this.dataSource1 = new MatTableDataSource(uList);
    this.dataSource1.filter = filterValue.trim();}
    );
  }
}