import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Client } from '../class/client';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  private url:string = "http://localhost:8084/clients";
  clientId : string = "614f959a5655534e458400fe";

  constructor(private http:HttpClient) { }

  //UPDATE PERSONAL INFORMATION FORM A CLIENT
  updatePersonalInformation(clientId:string, client:Client):Observable<Client> {
    return this.http.put<Client>(this.url +"/"+ this.clientId, client);
  }

  findClientById(clientId:string):Observable<Client> {
    return this.http.get<Client>(this.url + "/" + this.clientId);
  }

}
