import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Client } from '../class/client';
import { Ticket } from '../class/ticket';
import { ClientService } from '../service/client.service';
import { TicketService } from '../service/ticket.service';

@Component({
  selector: 'app-ticket',
  templateUrl: './ticket.component.html',
  styleUrls: ['./ticket.component.css']
})
export class TicketComponent implements OnInit {

  titulo : string = "Ticket list";
  ticketList! : Ticket[];
  ticket : Ticket = new Ticket();
  client : Client = new Client();
  clientId : string = "614f959a5655534e458400fe";

  constructor(private ticketService:TicketService, private clientService:ClientService) { }

  ngOnInit(): void {
    this.getTicketByClient(this.clientId);
    this.getClientById(this.clientId);
  }

  getTicketByClient(clientId:string):void{
    this.ticketService.getTicketByClient(clientId).subscribe(tickets => this.ticketList = tickets);
  }

  getTicketByTitle(title:string):void{
    this.ticketService.searchTicketByTitle(title).subscribe(tickets => this.ticketList = tickets);
  }

  deleteTicketById(ticket:Ticket):void{
    this.ticketService.deleteTicket(ticket.id).subscribe(res => this.ticketService.getTicketByClient(this.clientId).subscribe(response => this.ticketList = response));
  }

  getClientById(clientId:string):void{
    this.clientService.findClientById(this.clientId).subscribe(clientFounded => this.client = clientFounded);
  }

}
