import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Ticket } from 'src/app/class/ticket';
import { TicketService } from 'src/app/service/ticket.service';

@Component({
  selector: 'app-ticket-details',
  templateUrl: './ticket-details.component.html',
  styleUrls: ['./ticket-details.component.css']
})
export class TicketDetailsComponent implements OnInit {

  titulo : string = "Ticket's details";
  ticket : Ticket = new Ticket();
  clientId : string = "614f959a5655534e458400fe";

  constructor(private ticketService:TicketService, private router:Router, private activatedRoute:ActivatedRoute) { }

  ngOnInit(): void {
    this.showData();
  }

  showData():void{
    this.activatedRoute.params.subscribe(ticket => {
      let id = ticket['id'];
      if (id) {
        this.ticketService.getTicketById(id).subscribe(ticketFounded => this.ticket = ticketFounded);
      }
    });
  }
}
