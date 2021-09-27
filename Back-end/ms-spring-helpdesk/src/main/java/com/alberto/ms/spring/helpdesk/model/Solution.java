package com.alberto.ms.spring.helpdesk.model;


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
@Document(collection = "solution_document")
public class Solution {
    @Id
    private String id;
    private String title;
    private String description;
    private String solution;
    private String average_time_solution;
    private String impactRisk;
    private String ticketId;
}
