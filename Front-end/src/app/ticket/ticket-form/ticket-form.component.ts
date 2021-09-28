import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Ticket } from 'src/app/class/ticket';
import { TicketService } from 'src/app/service/ticket.service';

@Component({
  selector: 'app-ticket-form',
  templateUrl: './ticket-form.component.html',
  styleUrls: ['./ticket-form.component.css']
})
export class TicketFormComponent implements OnInit {

  titulo:string = "Ticket form";
  ticket:Ticket = new Ticket();
  clientId:string = "614f959a5655534e458400fe";

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

  insertTicket():void{
    console.log(this.ticket);
    this.ticketService.insertTicket(this.clientId,this.ticket).subscribe(res => this.router.navigate(['tickets']));
  }

  updateTicket():void{
    this.activatedRoute.params.subscribe(t => {
      let id = t['id'];
      this.ticketService.updateTicket(id, this.ticket).subscribe(res => this.router.navigate(['tickets']));
    })
  }

}
