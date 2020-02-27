import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginDialogBoxComponent } from '../login-dialog-box/login-dialog-box.component';
import { MatDialog } from '@angular/material/dialog';
import { WebSocketAPI } from '../WebSocketAPI';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {
  webSocketApi: WebSocketAPI;
  greeting: any;
  name: string;
  constructor(private router:Router, private dialog:MatDialog){}
  // constructor(private router:Router, private dialog:MatDialog){}
  show() {
    if (document.cookie.length > 0) {
      this.router.navigate(["dashboard"]);
    } else {
      this.dialog.open(LoginDialogBoxComponent, {
        width: "400px",
        height: "250px"
      });
    }
  }
  ngOnInit() {
    this.webSocketApi = new WebSocketAPI(new HomePageComponent(this.router,this.dialog));
  }

  handleMessage(message){
    this.greeting = message;
  }

}
