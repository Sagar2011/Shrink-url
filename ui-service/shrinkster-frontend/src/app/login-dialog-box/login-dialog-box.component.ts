import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-dialog-box',
  templateUrl: './login-dialog-box.component.html',
  styleUrls: ['./login-dialog-box.component.css']
})
export class LoginDialogBoxComponent implements OnInit {

  constructor(private dialogRef: MatDialogRef<LoginDialogBoxComponent>, private user:UserService, private router:Router) { }

  ngOnInit() {
  }

  closeModal() {
    this.dialogRef.close();
  }
  // github(){
  //   this.user.getGithub().subscribe((res:Response)=>{
  //     if(res.status === 200){

  //     } else {
  //       this.router.navigate(['/internal']);
  //     }
  //   })
  // }

}
