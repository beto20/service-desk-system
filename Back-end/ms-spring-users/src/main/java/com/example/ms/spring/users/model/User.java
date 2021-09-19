package com.example.ms.spring.users.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "user")
public class User {

    @Id
    private String id;
    private String name;
    private String lastname;
    private String password;
    private String photo;
    private String jobTitle;
    private Role roleId;
    private Department departmentId;
}
