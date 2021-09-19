package com.example.ms.spring.users.repository;

import com.example.ms.spring.users.model.Role;
import com.example.ms.spring.users.model.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends ReactiveCrudRepository<Role, String> {


}
