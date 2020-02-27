import { Component, OnInit } from '@angular/core';
export interface Url{
  url:string,
  tinyUrl:string,
  expiryDate:string
}
@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})

export class HistoryComponent implements OnInit {

  constructor() { }
  isActive:boolean=true;
  historyData:Url[]=[
    { url : "https://www.hackerrank.com/challenges/java-date-and-time/problem?h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=",
      tinyUrl:"https://www.geeksforgeeks.org/deletion-binary-tree/",
      expiryDate: "12/12/2020"
    },{ url : "https://www.hackerrank.com/challenges/java-date-and-time/problem?h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v",
    tinyUrl:"https://www.geeksforgeeks.org/deletion-binary-tree/",
    expiryDate: "12/12/2020"
  },{ url : "https://www.hackerrank.com/challenges/java-date-and-time/problem?h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=z",
  tinyUrl:"https://www.geeksforgeeks.org/deletion-binary-tree/",
  expiryDate: "12/12/2020"
  }
  ];

  ngOnInit() {
    console.log('data',this.historyData);
    this.historyData.forEach(element => {
      console.log(element)
    });

  }

}
