package com.example.ms.spring.client.web;

import com.example.ms.spring.client.business.ClientBusinessImpl;
import com.example.ms.spring.client.model.Client;
import com.example.ms.spring.client.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientBusinessImpl business;

    @PutMapping("/{id}")
    public Mono<Client> updatePersonalInformation(@PathVariable("id")String id, @RequestBody Client client) {
        return business.update(id, client);
    }

    @GetMapping("/{clientId}")
    public Mono<Client> getClientById(@PathVariable("clientId") String clientId) {
        return business.findById(clientId);
    }

    @GetMapping("/client")
    public Flux<Client> showClient() {
        return business.findAllClient();
    }

    @GetMapping("/role")
    public Flux<Role> showRole() {
        return business.findAllRole();
    }

    @PostMapping("/client")
    public Mono<Client> addClient(@RequestBody Client client) {
        return business.createClient(client);
    }

    @PostMapping("/role")
    public Mono<Role> addRole(@RequestBody Role role) {
        return business.createRole(role);
    }

}
