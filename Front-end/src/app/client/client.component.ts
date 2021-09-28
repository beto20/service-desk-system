import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Client } from '../class/client';
import { ClientService } from '../service/client.service';

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent implements OnInit {

  titulo : string = "Client information";
  client : Client = new Client();
  clientId : string = "614f959a5655534e458400fe";

  constructor(private clientService:ClientService, private router:Router, private activatedRoute:ActivatedRoute) { }

  ngOnInit(): void {
    this.showData();
  }

  showData():void{
    this.activatedRoute.params.subscribe(client => {
      let id = client['id'];
      if (id) {
        this.clientService.findClientById(id).subscribe(clientFounded => this.client = clientFounded);
      }
    });
  }

  updateClientInformation():void{
    this.activatedRoute.params.subscribe(c => {
      let id = c['id'];
      this.clientService.updatePersonalInformation(id, this.client).subscribe(res => this.router.navigate(['tickets']));
    })
  }
}
