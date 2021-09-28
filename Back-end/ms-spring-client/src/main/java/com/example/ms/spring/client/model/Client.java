package com.example.ms.spring.client.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "client_document")
public class Client {
    @Id
    private String id;
    private String name;
    private String lastname;
    private String password;
    private String photo;
    /* add the following attributes
    private String email;
    private String phoneNumber;
     */
    private String hierarchy;
    private Role role;
    private String department;
    private List<Ticket> ticketList;
}
