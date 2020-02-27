import { Component, OnInit } from "@angular/core";
import * as CanvasJS from "../../assets/lib/canvasjs.min";
import * as SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';
@Component({
  selector: "app-graph",
  templateUrl: "./graph.component.html",
  styleUrls: ["./graph.component.css"]
})
export class GraphComponent implements OnInit {
    private stompClient = null;
  name: string;
  disabled = true;
  greetings: any;
  constructor() {}
  datapoints = [];
  xValue = 0;
  ngOnInit() {
	  for (let index = 0; index < 10; index++) {
		this.datapoints.push({x:this.xValue++,y:0});
	  }
	let chart = new CanvasJS.Chart("chartContainer", {
		animationEnabled: true,
		exportEnabled: true,
		axisX:{
		  crosshair: {
		  enabled: true,
		  labelFormatter: e => { return "Hello"}
		},
		gridThickness: 0,
		tickLength: 0,
		 labelFormatter: function(){
			return " ";
		  }
	  },axisY:{
		crosshair: {
		enabled: true,
		labelFormatter: e => { return "Hello"}
	  },
	  minimum:-0.5,
	  tickLength: 0,
	  gridThickness: 0,
	  labelFormatter: function(){
		return " ";
	  }
	},dataPointWidth: 25,
		data: [
		  {
			color: "#696969",
			type: "column",
			showInLegend: true,
			legendMarkerType: "none",
			legendText: "Registering tiny urls",
			dataPoints: this.datapoints
		  }
		]
	  });
	  chart.render();
	  
	const socket = new SockJS('/url/ws-graph');
    this.stompClient = Stomp.over(socket);
 
    const _this = this;
    this.stompClient.connect({}, function (frame) {
      _this.setConnected(true);
      _this.stompClient.subscribe('/topic/greetings', function (hello) {
		_this.showGreeting(JSON.parse(hello.body));
		chart.render();
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
	// this.greetings = message.tinyUrl;
	this.datapoints.push({
		x: this.xValue++,
		y: message.tinyUrl
	  });
	  if (this.datapoints.length > 20) {
		this.datapoints.shift();
	  }
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
