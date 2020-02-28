import { Component, OnInit } from '@angular/core';
import { UrlService } from '../url.service';
import { Router } from '@angular/router';
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

  constructor(private url:UrlService,private router: Router) { }
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
    }, (error)=>{
      this.router.navigate(['/internal']);
    });
  }
  onNavigate(link:any){
    const url =link;
    window.open(url, '_blank');
}
}
