package com.example.ms.spring.client.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "ticket_document")
public class Ticket {

    @Id
    private String id;
    private String title;
    private String description;
    private String category;
    private String priority;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime reportedDate;
    private String status;
    private String clientId;
}
