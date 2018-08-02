import { Component, OnInit } from '@angular/core';
import {DataService} from '../data.service';
import {DataSource} from '@angular/cdk/table';
import {CollectionViewer} from '@angular/cdk/collections';
import {BehaviorSubject, Observable, of} from 'rxjs';
import {Data, Router} from '@angular/router';
import {catchError, finalize} from 'rxjs/operators';
import {Sort} from '@angular/material';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {ToasterService} from '../toaster.service';
//import { UploadFileService } from '../upload-file.service';
interface File {
  'NAME': string;
  'UPLOADED_AT': string;
  'UPLOAD_TIME': string;
}

export class FileDataSource implements DataSource<File> {
  private fileSubject = new BehaviorSubject<File[]>([]);
  private loadingSubject = new BehaviorSubject<Boolean>(false);
  public loading$ = this.loadingSubject.asObservable();
  public fileList = [];

  constructor(private data: DataService) {
    this.fileList = [];
    this.fileSubject.next([]);
  }
  connect(collectionViewer: CollectionViewer): Observable<File[]> {
    return this.fileSubject.asObservable();
  }
  disconnect(collectionViewer: CollectionViewer): void {
    this.loadingSubject.complete();
    this.fileSubject.complete();
  }
  loadFiles() {
    this.loadingSubject.next(true);
    this.data.getFilesList().pipe(
      catchError(() => of([])),
      finalize(() => this.loadingSubject.next(false))
    ).subscribe(files => {
      console.log(files);
      const fileList: File[] = [];
      for (let index in files) {
        const fileUploaded = new Date(files[index].lastModified);
        const file: File = {
         'NAME': files[index].filename,
          'UPLOADED_AT': fileUploaded.getDate() + '/' + (fileUploaded.getMonth()+1) + '/' + fileUploaded.getFullYear(),
          'UPLOAD_TIME': fileUploaded.getHours() + ':' + fileUploaded.getMinutes()
        };
        fileList.push(file);
      }
      this.fileList = fileList;
      console.log(fileList);
      this.fileList=fileList;
      this.fileSubject.next(fileList);
    });
  }
}

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent implements OnInit {
  public fileListHeaders = ['NAME', 'UPLOADED_AT', 'UPLOAD_TIME'];
  public dataSource;
  selectedFiles: FileList;
  currentFileUpload;
  constructor(private dService: DataService, private router: Router, private toasterService:ToasterService) {
  }

  ngOnInit() {
    let keyValue =localStorage.getItem('key');
    if (keyValue!='1')
      this.router.navigate(['/login']);

    this.dataSource = new FileDataSource(this.dService);
    this.dataSource.loadFiles();
  }
  selectFile(event) {
    this.selectedFiles = event.target.files;
  }

  upload() {
    this.currentFileUpload = this.selectedFiles.item(0);
    this.toasterService.Info("Please wait","Uploading the document !");
    this.dService.pushFileToStorage(this.currentFileUpload).subscribe(event => {
      if (event instanceof HttpResponse) {
          this.toasterService.Success("Success",event.body.toString());
          console.log(event);
          setTimeout(() => {location.reload();} ,3000);
        }
      },
      error => {
        if ( error instanceof HttpErrorResponse) {
          this.toasterService.Error("Error",error.error.toString());
          console.log(error);
        }
      });
    this.selectedFiles = undefined;
  }
  logout()  {
    this.dService.logout();
  }
}
