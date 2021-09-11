package com.alberto.ms.tickets.model.dto;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class TicketDto implements Serializable {

    @Id
    private Integer id;
    private String name;
    private String description;
    private String category;
    private LocalDateTime dateReported;
    private String priority;
    private String status;
    //User user
}
