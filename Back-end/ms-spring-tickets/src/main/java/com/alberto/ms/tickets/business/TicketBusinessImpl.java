package com.alberto.ms.tickets.business;

import com.alberto.ms.tickets.model.domain.Ticket;
import com.alberto.ms.tickets.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;


@Service
@AllArgsConstructor
public class TicketBusinessImpl implements TicketBusiness {

    private final TicketRepository repository;

    @Override
    public Flux<Ticket> getAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Ticket> getById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Ticket> insert(Ticket ticket) {
        LocalDateTime localDateTime = LocalDateTime.now();
        ticket.setDateReported(localDateTime);
        ticket.setStatus("Active");
        return repository.save(ticket);
    }

    @Override
    public Mono<Ticket> update(String id, Ticket ticket) {
        LocalDateTime localDateTime = LocalDateTime.now();
        return repository.findById(id)
                .map(upTicket -> Ticket.builder()
                        .id(id)
                        .name(ticket.getName())
                        .description(ticket.getDescription())
                        .category(ticket.getCategory())
                        .priority(ticket.getPriority())
                        .dateReported(localDateTime)
                        .status("Active")
                        .build()
                ).flatMap(repository::save);
    }

    @Override
    public Mono<Void> delete(String id) {
        return repository.deleteById(id);
    }
}
