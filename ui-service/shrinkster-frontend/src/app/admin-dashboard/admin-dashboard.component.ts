import { Component, OnInit } from '@angular/core';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { UrlService } from '../url.service';
import { from } from 'rxjs/internal/observable/from';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent implements OnInit {
  progress=67;
  links='Links';
  title='';
  constructor(private httpClient: HttpClient, private service:UrlService ) { }

  ngOnInit() {
    
  }
  getAllLinks(){
    console.log("links outside");
    
    this.service.getUrl().subscribe((result)=>{
  
    console.log("links", result);
  });
    }


}
