package com.example.ms.spring.users.model;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    private Integer id;
    private String name;
    private String lastname;
    private String password;
    private String photo;
    private String jobTitle;
    private Integer rolId;
    private Department department;
}
