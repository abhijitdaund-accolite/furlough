import { Injectable } from '@angular/core';
declare var toastr:any;
@Injectable({
  providedIn: 'root'
})
export class ToasterService {

  constructor() { 
      toastr.options = {
      "closeButton": true,
      "debug": false,
      "newestOnTop": false,
      "progressBar": true,
      "positionClass": "toast-top-center",
      "preventDuplicates": false,
      "onclick": null,
      "showDuration": "600",
      "hideDuration": "5000",
      "timeOut": "7000",
      "extendedTimeOut": "3000",
      "showEasing": "swing",
      "hideEasing": "linear",
      "showMethod": "fadeIn",
      "hideMethod": "fadeOut"
    }
  }

  Success(title:string,message?:string)  {
    toastr.success(message,title);
  }
  Error(title:string,message?:string) {
    toastr.error(message,title);
  }
  Info(title:string,message?:string) {
    toastr.info(message,title);
  }
}
