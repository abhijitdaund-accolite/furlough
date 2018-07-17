import { Component, OnInit } from '@angular/core';
import {DataService} from '../data.service';
// import {st} from '@angular/core/src/render3';
// import {last} from 'rxjs/operators';
// import { DataSource} from '@angular/cdk/table';
import { MatTableDataSource, MatSort } from '@angular/material';

@Component({
  selector: 'app-homeview',
  templateUrl: './homeview.component.html',
  styleUrls: ['./homeview.component.css'],
  providers : [DataService]
})
export class HomeviewComponent implements OnInit {

  public monthlyDetails = [];
  private dataList = [];
  private data;
  public fromDate;
  public toDate ;
  public dateHeader = [];
  public furloughDateReport = [];
  private monthHeaders = ['JAN', 'FEB' , 'MAR' , 'APR' , 'MAY' , 'JUN' ,'JUL','AUG','SEP','OCT','NOV','DEC'];
  private monthHeadersMap = {'JAN':0, 'FEB':1 , 'MAR':2 ,'APR':3,'MAY':4,'JUN':5,'JUL':6,'AUG':7,'SEP':8,'OCT':9,'NOV':10,'DEC':11};

  private monthIndexes = {};
  public furloughData=[];
  public currentSelectedMonthDays;
  public formattedDayHeader;
  public MONTHVIEW=false;
  public DATEVIEW=true;
  public tablePristine=true;

  public dataSource;

  constructor(data: DataService) {this.data = data; }



  ngOnInit() {
    this.data.getData().subscribe((objList) => {


      for ( let i = 0; i < 500000; i++)
      {
        if(objList[i] !== undefined){
          let obj = {
            'MSID': objList[i].msid,
            'NAME': objList[i].resourceName,
            'FURLOUGHS': objList[i].dateAndOverlaps.map((dateObj) => {return {'DATE': new Date(dateObj.furloughDate), 'STATUS' : dateObj.overlap}})
          };

          this.dataList.push(obj);
        }
        else
          break;
      }

      console.log(this.dataList);

    });


  }

  logout(){
    this.data.logout();
  }

  switchView(e){
    this.DATEVIEW=false;
    this.MONTHVIEW=true;

    let currentMonth=e
    let s = e.split(' ');

    this.prepareDataMonth(s[0],s[1]);

  }

  prepareDateHeader() {

    this.tablePristine=false;
    this.furloughData=[];
    this.furloughDateReport=[];
    this.MONTHVIEW=false;
    this.DATEVIEW=true;


    console.log(this.toDate);
    console.log(this.fromDate);

    this.toDate=new Date(this.toDate);
    this.fromDate=new Date(this.fromDate);

    let months;
    months=(this.toDate.getFullYear()-this.fromDate.getFullYear())*12;
    months-=this.fromDate.getMonth();
    months+=1;
    months+=this.toDate.getMonth();

    this.dateHeader=[];
    let startMonth=this.fromDate.getMonth();
    let startYear=this.fromDate.getFullYear();

    let monthEnds=12-startMonth;
    let yearIterator = startYear;
    for(let i=0;i<months;i++)
    {
      let monthIterator = (startMonth+i)%12;
      if(i>0)
        (monthIterator === 0 ? yearIterator+=1 : yearIterator );
      this.dateHeader.push(
        this.monthHeaders[monthIterator]+" "+yearIterator
      );

      this.monthIndexes[this.monthHeaders[monthIterator]+yearIterator]=i;
    }


    console.log(this.dataList)
    console.log(this.monthIndexes)

    for(let i = 0; i < this.dataList.length;i++){

      let currentEmpFurloughs=this.dataList[i].FURLOUGHS;
      let monthBucket = [];
      let monthOBucket = [];

      for(let i=0;i<this.dateHeader.length;i++)
        monthBucket[i]=0;

      for(let i=0;i<this.dateHeader.length;i++)
        monthOBucket[i]=0;

      for(let j=0;j<currentEmpFurloughs.length;j++)
      {
        let newVal=0;
        let confirmed=0;
        if(currentEmpFurloughs[j].STATUS=='PLANNED')
          newVal=1;
        else if(currentEmpFurloughs[j].STATUS=='CANCELLED')
          newVal=-1;
        else if(currentEmpFurloughs[i].STATUS=='OVELAPPED')
          confirmed=1;

        monthBucket[
          this.monthIndexes[
            this.monthHeaders[
              currentEmpFurloughs[j].DATE.getMonth()
              ] + currentEmpFurloughs[j].DATE.getFullYear()
            ]
          ] += newVal;

        monthOBucket[
          this.monthIndexes[
          this.monthHeaders[
            currentEmpFurloughs[j].DATE.getMonth()
            ] + currentEmpFurloughs[j].DATE.getFullYear()
            ]
          ] += confirmed;

      }
      console.log(monthBucket);

      let monthPOBucket=[];
      for(let i=0;i<monthBucket.length;i++){
        monthPOBucket.push({'P':monthBucket[i],'O':monthOBucket[i]});
      }
      this.furloughData.push(
        {
          'MSID':this.dataList[i].MSID,
          'NAME':this.dataList[i].NAME,
          'PLBUCKETS': monthBucket,
          'OVBUCKETS': monthOBucket,
          'POLBUCKETS': monthPOBucket
        }
      )
    }

    this.dataSource= new MatTableDataSource(this.furloughData)
    console.log(this.furloughData);
  }



  prepareDataMonth(month,year){

    this.tablePristine=false;
    this.furloughData=[];
    this.furloughDateReport=[];

    let monthNumber=this.monthHeadersMap[month];
    let lastDate = new Date(year,monthNumber+1,0)
    let noOfDays=lastDate.getDate();
    this.furloughDateReport=[]
    this.currentSelectedMonthDays=noOfDays
    this.formattedDayHeader=Array(noOfDays).fill(0).map((x,i)=>i+1)


    for(let i=0;i<this.dataList.length;i++)
    {
      let dateBucket=[]
      for(let j=0;j<noOfDays;j++)
        dateBucket.push(0)

      for(let j=0;j<this.dataList[i].FURLOUGHS.length;j++)
      {
        let value=0;
        if(this.dataList[i].FURLOUGHS[j].STATUS=='PLANNED')
          value=1;
        else if(this.dataList[i].FURLOUGHS[j].STATUS=='CANCELLED')
          value=0;
        else value=2;

        if(this.dataList[i].FURLOUGHS[j].DATE.getMonth()==monthNumber)
          dateBucket[this.dataList[i].FURLOUGHS[j].DATE.getDate()]=value
      }

      console.log(dateBucket)

      let obj= {
        'MSID':this.dataList[i].MSID,
        'NAME':this.dataList[i].NAME,
        'BUCKET': dateBucket
      }

      this.furloughDateReport.push(obj);
    }


  }

  prepareTestData() {

    let data=[
      {'NAME':'AVINASH','COL1':'VAL1','COL2':'VAL2'},
      {'NAME':'AVINASH','COL1':'VAL1','COL2':'VAL2'},
      {'NAME':'AVINASH','COL1':'VAL1','COL2':'VAL2'},
      {'NAME':'AVINASH','COL1':'VAL1','COL2':'VAL2'},
    ]

    this.dataSource=new MatTableDataSource(data);
  }

}