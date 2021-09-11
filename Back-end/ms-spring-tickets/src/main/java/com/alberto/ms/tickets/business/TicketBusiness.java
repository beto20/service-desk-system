package com.alberto.ms.tickets.business;

import com.alberto.ms.tickets.model.domain.Ticket;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TicketBusiness {
    Flux<Ticket> getAll();
    Mono<Ticket> getById(Integer id);
    Mono<Ticket> insert(Ticket ticket);
    Mono<Ticket> update(Integer id, Ticket ticket);
    Mono<Void> delete(Integer id);
}
