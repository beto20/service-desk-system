package com.alberto.ms.tickets.repository;

import com.alberto.ms.tickets.model.domain.Ticket;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends ReactiveMongoRepository<Ticket, String> {
}
