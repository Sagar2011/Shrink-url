import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { LoginDialogBoxComponent } from './login-dialog-box/login-dialog-box.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'shrinkster';

  //   constructor(private router:Router, private dialog:MatDialog){}
  // show() {
  //   if (document.cookie.length > 0) {
  //     this.router.navigate(["search"]);
  //   } else {
  //     this.dialog.open(LoginDialogBoxComponent, {
  //       width: "400px",
  //       height: "250px"
  //     });
  //   }
  // }
}
