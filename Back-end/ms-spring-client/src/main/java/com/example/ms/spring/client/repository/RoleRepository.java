package com.example.ms.spring.client.repository;

import com.example.ms.spring.client.model.Client;
import com.example.ms.spring.client.model.Role;
import com.example.ms.spring.client.model.Ticket;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface RoleRepository extends ReactiveMongoRepository<Role, String> {

}
