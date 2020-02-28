import { Component, OnInit, ErrorHandler } from '@angular/core';
import { Router } from '@angular/router';
import {FormControl, Validators, FormBuilder, FormGroup} from '@angular/forms';
import { UrlService } from '../url.service';

@Component({
  selector: 'app-link-dashboard',
  templateUrl: './link-dashboard.component.html',
  styleUrls: ['./link-dashboard.component.css']
})
export class LinkDashboardComponent implements OnInit {
  urlGroup:FormGroup;
  tinyLink: any = 0;
  show = false;
  spin = false;
  constructor(private url:UrlService,private router:Router, private fb:FormBuilder) { 
    this.urlGroup = this.fb.group({
      urlLink :new FormControl('', [
        Validators.required,
      ])
    })
  }

  ngOnInit() {
  }

  generate(){
    this.spin = true;
    this.url.postUrl(this.urlGroup.value).subscribe((res)=>{
      if(res === undefined || res === null || res === ""){
        this.urlGroup.reset();
        this.spin = false;
        this.router.navigate(['/internal']);
      } else{
        this.tinyLink = res;
        this.show=true;
        this.spin = false;
        this.urlGroup.reset();
      }
    }, (error)=>{
      this.router.navigate(['/internal']);
    });
  }

}
