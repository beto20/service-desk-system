package com.example.ms.spring.client.web;

import com.example.ms.spring.client.business.TicketBusinessImpl;
import com.example.ms.spring.client.model.Client;
import com.example.ms.spring.client.model.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final TicketBusinessImpl business;

    //ENDPOINT FOR OPERATOR TICKETS POOL
    @GetMapping("/")
    public Flux<Ticket> getAllTicket() {
        return business.findAll();
    }

    //ENDPOINT FOR SEARCH BY TITLE IN CLIENT DASHBOARD
    @GetMapping("/search-title/{title}")
    public Flux<Ticket> getTicketByTitle(@PathVariable("title") String title) {
        return business.findByTitle(title);
    }

    //ENDPOINT FOR SEARCH BY CLIENTID
    @GetMapping("/user/{clientId}")
    public Flux<Ticket> getTicketByUser(@PathVariable("clientId") String clientId) {
        return business.findByClientId(clientId);
    }

    //ENDPOINT FOR SEARCH BY ID
    @GetMapping("/{id}")
    public Mono<Ticket> getTicketById(@PathVariable("id") String id) {
        return business.findById(id);
    }

    //ENDPOINT FOR ADD A NEW TICKET
    @PostMapping("/{id}")
    public Mono<Client> getTicketById(@PathVariable("id") String id, @RequestBody Ticket ticket) {
        //business.insert(ticket);
        return business.insert2(id, ticket);
    }

    //ENDPOINT FOR UPDATE A TICKET
    @PutMapping("/{ticketId}")
    public Mono<Client> updateTicket(@PathVariable("ticketId") String ticketId, @RequestBody Ticket ticket) {
        return business.updateClientTicketListV2(ticketId, ticket);
    }

    //ENDPOINT FOR DELETE A TICKET BY HIS ID AND CHANGE THE STATUS TO "CANCEL" IN CLIENT (V1)
    @DeleteMapping("/{id}/{clientId}")
    public Mono<Void> deleteTicket(@PathVariable("id") String ticketId, @PathVariable("clientId") String clientId) {
        return business.delete4(ticketId, clientId);
    }

    //ENDPOINT FOR DELETE A TICKET BY HIS ID AND CHANGE THE STATUS TO "CANCEL" IN CLIENT (V2)
    @DeleteMapping("/deleteV2/{id}")
    public Mono<Void> deleteTicketV2(@PathVariable("id") String ticketId) {
        return business.delete4V2(ticketId);
    }

    //CHANGE STATUS TEST
    @PostMapping("/status/{id}/{clientId}")
    public Mono<Client> changeStatus(@PathVariable("id") String ticketId, @PathVariable("clientId") String clientId) {
        return business.changeStatusToCancel(ticketId, clientId);
    }
}
