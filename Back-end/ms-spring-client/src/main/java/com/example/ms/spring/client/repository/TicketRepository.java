package com.example.ms.spring.client.repository;

import com.example.ms.spring.client.model.Ticket;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TicketRepository extends ReactiveMongoRepository<Ticket, String> {
    Flux<Ticket> findTicketsByTitleIsLike (String title);
    Flux<Ticket> findTicketsByClientId (String clientId);
}
