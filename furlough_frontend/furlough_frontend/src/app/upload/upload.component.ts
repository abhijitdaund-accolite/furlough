import { Component, OnInit } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Router } from '@angular/router';
import { UploadFileService } from '../file-upload.service';
import { HttpClient, HttpResponse, HttpEventType } from '@angular/common/http';
import { DataService} from '../data.service';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css'],
  providers: [UploadFileService]
})
export class UploadComponent implements OnInit {

  selectedFiles: FileList;  
   currentFileUpload: File;
    
    constructor(private uploadService: UploadFileService,private router: Router, private dService: DataService) {}
    
    selectFile(event) {
        this.selectedFiles = event.target.files;
    }

    upload() { 
      this.currentFileUpload = this.selectedFiles.item(0);
      this.uploadService.pushFileToStorage(this.currentFileUpload).subscribe(event => {  
       if (event instanceof HttpResponse) {
          console.log('File is completely uploaded!');
        }  
      });
      this.selectedFiles = undefined;
    }

  ngOnInit() {
    let keyValue =localStorage.getItem('key');
    if (keyValue!='1')
    this.router.navigate(['/loginPage']);
  }

  logout()  {
    this.dService.logout();
  }
  
}
