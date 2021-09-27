package com.example.ms.spring.client.business;

import com.example.ms.spring.client.model.Client;
import com.example.ms.spring.client.model.Role;
import com.example.ms.spring.client.model.Ticket;
import com.example.ms.spring.client.repository.ClientRepository;
import com.example.ms.spring.client.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@AllArgsConstructor
public class ClientBusinessImpl {
    private final ClientRepository clientRepo;
    private final RoleRepository roleRepo;

    //UPDATE PERSONAL INFORMATION
    public Mono<Client> update(String id, Client client) {
        return clientRepo.findById(id)
                .map(foundedClient -> Client.builder()
                        .id(id)
                        .name(client.getName())
                        .lastname(client.getLastname())
                        .password(client.getPassword())
                        .hierarchy(client.getHierarchy())
                        .photo(client.getPhoto())
                        .role(foundedClient.getRole())
                        .department(foundedClient.getDepartment())
                        .ticketList(foundedClient.getTicketList())
                        .build()
                ).flatMap(clientRepo::save);
    }

    public Mono<Client> findById(String clientId) {
        return clientRepo.findById(clientId);
    }

    //JUST FOR TEST
    public Mono<Client> createClient(Client client) {
        return clientRepo.save(client);
    }
    public Mono<Role> createRole(Role role) {
        return roleRepo.save(role);
    }
    public Flux<Client> findAllClient() {
        return clientRepo.findAll();
    }
    public Flux<Role> findAllRole() {
        return roleRepo.findAll();
    }


}
