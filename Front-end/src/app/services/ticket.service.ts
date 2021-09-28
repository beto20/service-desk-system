import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ticket } from './../classes/ticket';



@Injectable({
    providedIn: 'root'

})
export class TicketService{

private url:string= "http://localhost:9090";
constructor(private http:HttpClient){ }

getAlltickets():Observable<ticket[]>{   
    return this.http.get<ticket[]>(this.url);
}
}