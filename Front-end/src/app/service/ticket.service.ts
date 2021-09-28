import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ticket } from '../class/ticket';

@Injectable({
  providedIn: 'root'
})
export class TicketService {

  private url:string = "http://localhost:8084/tickets";
  clientId : string = "614f959a5655534e458400fe";

  constructor(private http:HttpClient) { }

  //GET TICKETS BY CLIENT
  getTicketByClient(clientId:string):Observable<Ticket[]> {
    return this.http.get<Ticket[]>(this.url +"/user/"+ this.clientId);
  }

  //GET TICKET BY ID
  getTicketById(id:string):Observable<Ticket> {
    return this.http.get<Ticket>(this.url +"/"+ id);
  }

  //INSERT A NEW TICKET
  insertTicket(clientId:string, ticket:Ticket):Observable<Ticket> {
    return this.http.post<Ticket>(this.url +"/"+ this.clientId, ticket);
  }

  //UPDATE A TICKET
  updateTicket(id:string, ticket:Ticket):Observable<Ticket> {
    return this.http.put<Ticket>(this.url +"/"+ id, ticket);
  }

  //DELETE A TICKET
  deleteTicket(id:string):Observable<Ticket> {
    return this.http.delete<Ticket>(this.url +"/deleteV2/"+ id);
  }

  //SEACH A TICKET BY HIS TITLE
  searchTicketByTitle(title:string):Observable<Ticket[]> {
    return this.http.get<Ticket[]>(this.url + "/search-title/" + title);
  }

}
