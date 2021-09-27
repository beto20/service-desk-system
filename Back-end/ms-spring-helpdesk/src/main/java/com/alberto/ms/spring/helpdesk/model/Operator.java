package com.alberto.ms.spring.helpdesk.model;

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
@Document(collection = "operator_document")
public class Operator {

    @Id
    private String id;
    private String name;
    private String lastname;
    private String password;
    private String photo;
    private String hierarchy;
    private String role;
    private String department;
    private List<Solution> solutionList;
    private List<Reject> rejectList;
}
