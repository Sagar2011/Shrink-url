import { Component, OnInit } from '@angular/core';
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

  constructor(private url:UrlService,private router:Router, private fb:FormBuilder) { 
    this.urlGroup = this.fb.group({
      url :new FormControl('', [
        Validators.required,
      ])
    })
  }

  ngOnInit() {
  }

  generate(){
    console.log('adadad'+ Object.values(this.urlGroup.value));
  }

}
