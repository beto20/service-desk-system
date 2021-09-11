package com.alberto.ms.tickets.web;

import com.alberto.ms.tickets.business.TicketBusinessImpl;
import com.alberto.ms.tickets.model.domain.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("api")
@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:4200/")
public class TicketController {

    private final TicketBusinessImpl business;

    @GetMapping("/tickets")
    public Flux<Ticket> getAllTicket() {
        return business.getAll();
    }

    @GetMapping("/tickets/{id}")
    public Mono<Ticket> getTicketById(@PathVariable("id") Integer id) {
        return business.getById(id);
    }

    @PostMapping("/tickets")
    public Mono<Ticket> insertTicket(@RequestBody Ticket ticket) {
        return business.insert(ticket);
    }

    @PutMapping("/tickets/{id}")
    public Mono<Ticket> updateTicket(@PathVariable("id") Integer id, @RequestBody Ticket ticket) {
        return business.update(id, ticket);
    }

    @DeleteMapping("/tickets/{id}")
    public Mono<Void> deleteTicket(@PathVariable("id") Integer id) {
        return business.delete(id);
    }
}
