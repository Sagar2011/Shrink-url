import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginDialogBoxComponent } from '../login-dialog-box/login-dialog-box.component';
import { MatDialog } from '@angular/material/dialog';
import * as SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';
@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {
  private stompClient = null;
  name: string;
  disabled = true;
  greetings: any;
  constructor(private router:Router, private dialog:MatDialog){}
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
    const socket = new SockJS('/url/ws-graph');
    this.stompClient = Stomp.over(socket);
 
    const _this = this;
    this.stompClient.connect({}, function (frame) {
      _this.setConnected(true);
      console.log('Connected: ' + frame);
      _this.stompClient.subscribe('/topic/greetings', function (hello) {
        console.log("ddwd");
        _this.showGreeting(JSON.parse(hello.body));
        console.log(hello+";;;;;;;");
      });
    });
    
  }
  setConnected(connected: boolean) {
    this.disabled = !connected;
 
    if (connected) {
      this.greetings = [];
    }
  }
  showGreeting(message) {
    this.greetings = message.urlCount;
  }
 disconnect() {
    if (this.stompClient != null) {
      this.stompClient.disconnect();
    }
 
    this.setConnected(false);
    console.log('Disconnected!');
  }
  ngOnDestroy(){
      this.disconnect();
  }

}
