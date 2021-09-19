package com.example.ms.spring.users.web;

import com.example.ms.spring.users.business.DepartmentBusinessImpl;
import com.example.ms.spring.users.business.UserBusinessImpl;
import com.example.ms.spring.users.model.Department;
import com.example.ms.spring.users.model.Role;
import com.example.ms.spring.users.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {


    private final UserBusinessImpl business;

    @GetMapping("/role")
    public Flux<Role> getAllRole() {
        return business.getAllRole();
    }

    @PostMapping("/role")
    public Mono<Role> insertRole(@RequestBody Role role) {
        return business.insertRole(role);
    }
/////////////////////
    @GetMapping("/")
    public Flux<User> getAllUser() {
        return business.getAll();
    }

    @PostMapping("/")
    public Mono<User> insertUser(@RequestBody User user) {
        return business.insert(user);
    }

    @PutMapping("{id}")
    public Mono<User> updateUser(@RequestBody User user, @PathVariable("id") String id) {
        return business.update(id, user);
    }

    @DeleteMapping("{id}")
    public Mono<Void> deleteUser(@PathVariable("id") String id) {
        return business.delete(id);
    }
}
