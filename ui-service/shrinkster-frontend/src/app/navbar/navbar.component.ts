import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../user.service';
import { UrlService } from '../url.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  userProfileAvatarUrl: any;
  constructor(private router:Router,private userService: UserService, private d:ChangeDetectorRef, private url:UrlService) { }
  username: string;
  show = false;
  userProfile:any;
  userAvatar:any;
  notifications: any;
  interval: any;
  ngOnInit() {
    this.userService.getUserProfile().subscribe(response => {
      this.userProfile = response;
      console.log('profile'+this.userProfile);
       if(this.userProfile != null){
   this.show = true;
    }
      this.userAvatar = this.userProfile.avatarURL;
      this.userProfileAvatarUrl = 'url(' + this.userAvatar + ')';
      this.username = this.userProfile.name;
      this.d.markForCheck();
    this.d.detectChanges();
    });
    this.interval =  setInterval(() => {
      this.checkUpdate();
  }, 3600000);
    this.d.markForCheck();
    this.d.detectChanges();
  }

  checkUpdate(){
    this.url.getStatus().subscribe((res)=>{
      this.notifications = res;
      this.d.markForCheck();
      this.d.detectChanges();
    });
  }
//wfgetrhyjkjhghhgfds
getUserProfile() {
  this.userService.getUserProfile().subscribe(response => {
    this.userProfile = response;
    console.log('profile'+this.userProfile);
     if(this.userProfile != null){
 this.show = true;
  }
    this.userAvatar = this.userProfile.avatarURL;
    this.userProfileAvatarUrl = 'url(' + this.userAvatar + ')';
    this.username = this.userProfile.name;
  });
}
logout() {
  this.userService.logusersOut().subscribe(res => {
    this.router.navigate(['']);
    this.show = false;
  });
}
notify(){
  this.checkUpdate();
}
ngOnDestroy() {
  if (this.interval) {
      clearInterval(this.interval);
  }
 }

}
