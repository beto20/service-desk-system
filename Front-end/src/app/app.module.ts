import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { HttpClientModule } from '@angular/common/http';
import { Routes, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { TicketComponent } from './ticket/ticket.component';
import { ClientComponent } from './client/client.component';
import { HomeComponent } from './home/home.component';
import { SolutionComponent } from './solution/solution.component';
import { TicketFormComponent } from './ticket/ticket-form/ticket-form.component';
import { TicketDetailsComponent } from './ticket/ticket-details/ticket-details.component';
import { TicketSearchComponent } from './ticket/ticket-search/ticket-search.component';



const routes:Routes = [
  //{ path:'', redirectTo:'/', pathMatch:'full' },
  { path:'home', component:HomeComponent,  },
  //{ path:'login', component:loginComponent },
  { path:'tickets', component:TicketComponent },
  { path:'ticket/add', component:TicketFormComponent },
  { path:'ticket/edit/:id', component:TicketFormComponent },
  { path:'ticket/details/:id', component:TicketDetailsComponent },
  { path:'ticket/results', component:TicketSearchComponent },
  { path:'client/info/:id', component:ClientComponent },

  { path:'client/:id', component:ClientComponent},

  /* INTERFACES AUN NO DISPONIBLES
  { path:'repository/', component:SolutionComponent },
  { path:'repository/:id', component:SolutionComponent },
  */
]


@NgModule({
  declarations: [
    AppComponent,
    TicketComponent,
    TicketFormComponent,
    TicketDetailsComponent,
    ClientComponent,
    SolutionComponent,
    HomeComponent,
    TicketSearchComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
