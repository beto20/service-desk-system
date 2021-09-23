import { TicketService } from './../services/ticket.service';
import { Component, OnInit } from '@angular/core';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { HttpClientModule } from '@angular/common/http';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { ticket } from '../classes/ticket';



@Component({
  selector: 'app-ticket',
  templateUrl: './ticket.component.html', 
  styleUrls: ['./ticket.component.css']
})
export class TicketComponent implements OnInit {
  ticketList! : ticket[];
  ticket : ticket = new ticket();
  constructor(private TicketService:TicketService) { }

  ngOnInit(): void {
    this.getAlltickets();
  }
  getAlltickets():void{
    this.TicketService.getAlltickets().subscribe(tickets => this.ticketList = tickets)
  }
 

  
}
