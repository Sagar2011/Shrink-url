import { Component, OnInit } from '@angular/core';
import { UrlService } from '../url.service';
export interface Url{
  urlLink:string,
  tinyUrl:string,
  generateDate:string
}
@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})

export class HistoryComponent implements OnInit {

  constructor(private url:UrlService) { }
  isActive:boolean=true;
  historyData:Url[]=[];
  show = false;
  ngOnInit() {
    this.url.getUserHistory().subscribe((res)=>{
      this.historyData = res;
      if(this.historyData.length  !== 0){
        this.show = true;
      } else{
        this.show = false;
      }
    });
  }

}
