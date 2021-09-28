package com.example.ms.spring.client.business;

import com.example.ms.spring.client.model.Client;
import com.example.ms.spring.client.model.Ticket;
import com.example.ms.spring.client.repository.ClientRepository;
import com.example.ms.spring.client.repository.TicketRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
@Slf4j
public class TicketBusinessImpl implements GenericBusiness<Ticket, String> {

    private final TicketRepository ticketRepo;
    private final ClientRepository clientRepo;

    @Override
    public Flux<Ticket> findAll() {
        return ticketRepo.findAll();
    }

    @Override
    public Mono<Ticket> findById(String id) {
        return ticketRepo.findById(id);
    }
    //CLIENTID
    public Flux<Ticket> findByClientId(String clientId) {
        return ticketRepo.findTicketsByClientId(clientId);

    }

    //TITLE
    public Flux<Ticket> findByTitle(String title) {
        return ticketRepo.findTicketsByTitleIsLike(title);
    }

    @Override
    public Mono<Ticket> insert(Ticket ticket) {
        LocalDateTime localDateTime = LocalDateTime.now();
        final String STATUS = "OPEN";
        ticket.setReportedDate(localDateTime);
        ticket.setStatus(STATUS);
        return ticketRepo.save(ticket);
    }


    public Mono<Client> insert2(String id, Ticket ticket) {
        LocalDateTime localDateTime = LocalDateTime.now();
        final String STATUS = "OPEN";
        ticket.setReportedDate(localDateTime);
        ticket.setClientId(id);
        ticket.setStatus(STATUS);
        return ticketRepo.save(ticket).flatMap(ticket1 -> {
            List<Ticket> t1 = new ArrayList<>();
            t1.add(ticket1);
            return clientRepo.findById(id).flatMap(client -> {
                List<Ticket> t2 = client.getTicketList();
                List<Ticket> res = Stream.concat(t1.stream(), t2.stream()).collect(Collectors.toList());
                client.setTicketList(res);
                return clientRepo.save(client);
           });
        });
    }

    @Override
    public Mono<Ticket> update(String id, Ticket ticket) {
        LocalDateTime localDateTime = LocalDateTime.now();
        final String STATUS = "OPEN";
        return ticketRepo.findById(id)
                .map(foundedTicket -> Ticket.builder()
                        .id(id)
                        .title(ticket.getTitle())
                        .description(ticket.getDescription())
                        .category(ticket.getCategory())
                        .priority(ticket.getPriority())
                        .reportedDate(localDateTime)
                        .status(STATUS)
                        .clientId(foundedTicket.getClientId())
                        .build()
                ).flatMap(ticketRepo::save);
    }



    public Mono<Client> updateClientTicketListV2(String ticketId, Ticket ticket) {
        LocalDateTime localDateTime = LocalDateTime.now();
        final String STATUS = "OPEN";
        return ticketRepo.findById(ticketId).map(ticketFounded -> {
            return Ticket.builder()
                    .id(ticketId)
                    .title(ticket.getTitle())
                    .description(ticket.getDescription())
                    .category(ticket.getCategory())
                    .priority(ticket.getPriority())
                    .reportedDate(localDateTime)
                    .status(STATUS)
                    .clientId(ticketFounded.getClientId())
                    .build();
        }).flatMap(ticketRepo::save).flatMap(ticketUpdated -> {
            return clientRepo.findById(ticketUpdated.getClientId()).map(client -> {
                List<Ticket> t1 = new ArrayList<>();
                t1.add(ticketUpdated);

                List<Ticket> t2 = client.getTicketList();
                List<Ticket> t2New = t2.stream()
                        .filter(ticketFilter -> !ticketId.equals(ticketFilter.getId()))// FILTRANDO POR EL id DEL ticket QUE SEAN DISTINTOS AL id QUE SE ENVIA
                        .collect(Collectors.toList()); // TRANSFORMANDO DE STREAM A LISTA
                List<Ticket> res = Stream.concat(t1.stream(),t2New.stream()).collect(Collectors.toList());
                return Client.builder()
                        .id(ticket.getClientId())
                        .name(client.getName())
                        .lastname(client.getLastname())
                        .password(client.getPassword())
                        .hierarchy(client.getHierarchy())
                        .photo(client.getPhoto())
                        .role(client.getRole())
                        .department(client.getDepartment())
                        .ticketList(res)
                        .build();
            }).flatMap(clientUpdate -> clientRepo.save(clientUpdate));

        });
    }


    @Override
    public Mono<Void> delete(String ticketId) {
        return ticketRepo.deleteById(ticketId);
    }

    public Mono<Void> delete4(String ticketId, String clientId) {
        return changeStatusToCancel(ticketId, clientId).flatMap(client -> ticketRepo.deleteById(ticketId));
    }
    public Mono<Client> changeStatusToCancel(String ticketId, String clientId) {
        return clientRepo.findById(clientId).flatMap(client -> {
            return ticketRepo.findById(ticketId).map(ticket -> {
                List<Ticket> t1 = new ArrayList<>();
                ticket.setStatus("CANCEL");
                t1.add(ticket);
                return t1;//ME RETORNA SOLO LA LISTA CON EL OBJETO MODIFICADO
            }).map(combineTickets -> {
                List<Ticket> t2 = client.getTicketList(); // ALMACENANDO LISTA DE tickets DEL client EN UNA NUEVA VARIABLE
                List<Ticket> t2New = t2.stream()
                        .filter(ticketFilter -> !ticketId.equals(ticketFilter.getId()))// FILTRANDO POR EL id DEL ticket QUE SEAN DISTINTOS AL id QUE SE ENVIA
                        .collect(Collectors.toList()); // TRANSFORMANDO DE STREAM A LISTA
                log.info("Lista de datos de los tickets: " + t2New.toString());
                List<Ticket> res = Stream.concat(combineTickets.stream(),t2New.stream()).collect(Collectors.toList());

                return Client.builder()
                        .id(clientId)
                        .name(client.getName())
                        .lastname(client.getLastname())
                        .password(client.getPassword())
                        .hierarchy(client.getHierarchy())
                        .photo(client.getPhoto())
                        .role(client.getRole())
                        .department(client.getDepartment())
                        .ticketList(res)
                        .build();
            }).flatMap(clientNew -> clientRepo.save(clientNew));
        });
    }



    public Mono<Void> delete4V2(String ticketId) {
        return changeStatusToCancelV2(ticketId).flatMap(client -> ticketRepo.deleteById(ticketId));
    }
    public Mono<Client> changeStatusToCancelV2(String ticketId) {

            return ticketRepo.findById(ticketId).flatMap(ticket -> {

                return clientRepo.findById(ticket.getClientId()).flatMap(client -> { //BUSCA A UN client POR EL ClientId DEL ticket
                    return ticketRepo.findById(ticketId).map(ticket1 -> {
                        LocalDateTime localDateTime = LocalDateTime.now();
                        List<Ticket> t1 = new ArrayList<>();
                        ticket1.setStatus("CANCEL");
                        ticket1.setReportedDate(localDateTime);
                        t1.add(ticket1);
                        return t1;
                    }).map(combineTickets -> {
                        List<Ticket> t2 = client.getTicketList(); // ALMACENANDO LISTA DE tickets DEL client EN UNA NUEVA VARIABLE
                        List<Ticket> t2New = t2.stream()
                                .filter(ticketFilter -> !ticketId.equals(ticketFilter.getId()))// FILTRANDO POR EL id DEL ticket QUE SEAN DISTINTOS AL id QUE SE ENVIA
                                .collect(Collectors.toList()); // TRANSFORMANDO DE STREAM A LISTA

                        List<Ticket> res = Stream.concat(combineTickets.stream(),t2New.stream()).collect(Collectors.toList());

                        return Client.builder()
                                .id(client.getId())
                                .name(client.getName())
                                .lastname(client.getLastname())
                                .password(client.getPassword())
                                .hierarchy(client.getHierarchy())
                                .photo(client.getPhoto())
                                .role(client.getRole())
                                .department(client.getDepartment())
                                .ticketList(res)
                                .build();
                    }).flatMap(clientNew -> clientRepo.save(clientNew));
                });
            });
    }


}


