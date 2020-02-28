import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { UrlService } from '../url.service';
import { from } from 'rxjs/internal/observable/from';
import { UserService} from '../user.service';

export interface users{
  name:any;
email:any;
avatarURL: any;
loggedIn: any;
urlCount:any;
}
export interface links{

   urlLink:any;
   tinyUrl:any;
   userId:any;
}

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})

export class AdminDashboardComponent implements OnInit {
 public progress;
  links='Links';
  userBank:any;
  linkBank:any;
  title='';
  constructor(private httpClient: HttpClient, private service:UrlService, private userService:UserService, private cd:ChangeDetectorRef ) { }
  getUsers(){
    this.userService.getUser().subscribe(result=>{
    // console.log("user-->",result);
    this.userBank = result;
    });
}
 getLinks(){
   this.service.getUrl().subscribe((result)=>{
    this.progress=result.length;
    this.linkBank=result;
    });
}
  getUrlCount(){
    this.userBank.forEach(function (arrayItem) {
      let count = 0;
     this.linkBank.forEach(function (link) {
        if(arrayItem.email===link.userId){
          count++;
         
        }
      });
      arrayItem.urlCount = count;
      console.log("userBank",arrayItem);
  }); 
  }
  ngOnInit() {
    const _this = this;
        _this.getUsers();
        _this.cd.detectChanges();
      _this.cd.markForCheck();
        _this.getLinks();
        _this.cd.markForCheck();
        _this.cd.detectChanges();
        _this.getUrlCount();
        _this.cd.markForCheck();
        _this.cd.detectChanges();
  }


  
 

}
