import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UrlService {

  constructor(private http:HttpClient) { }

  postUrl(data:any): Observable<any>{
    return this.http.post('/url/generate',data);
  }

  getStatus(): Observable<any>{
    return this.http.get("/url/status");
  }
}
