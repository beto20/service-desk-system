package com.alberto.ms.tickets.model.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.Id;
import java.time.LocalDateTime;


@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Ticket {

    @Id
    private Integer id;
    private String name;
    private String description;
    private String category;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateReported;
    private String priority;
    private String status;
    //User user

}
