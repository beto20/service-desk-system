package com.example.ms.spring.client.repository;

import com.example.ms.spring.client.model.Client;
import com.example.ms.spring.client.model.Ticket;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ClientRepository extends ReactiveMongoRepository<Client, String> {
    public Mono<Ticket> findBy (String title);
}
