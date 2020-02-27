import { Component, OnInit } from '@angular/core';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { UrlService } from '../url.service';
import { from } from 'rxjs/internal/observable/from';
import { UserService} from '../user.service';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent implements OnInit {
 public progress;
  links='Links';
  userBank:any;
  title='';
  constructor(private httpClient: HttpClient, private service:UrlService, private userService:UserService ) { }

  ngOnInit() {
      
    this.service.getUrl().subscribe((result)=>{
    this.progress=result.length;
    
    });
    this.userService.getUser().subscribe((result)=>{
      console.log("user-->",result);
      this.userBank=result;
      
      });
    
  }

    
  
 

}
