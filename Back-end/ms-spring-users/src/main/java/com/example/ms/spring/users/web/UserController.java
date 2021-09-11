package com.example.ms.spring.users.web;

import com.example.ms.spring.users.business.UserBusinessImpl;
import com.example.ms.spring.users.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class UserController {

    private final UserBusinessImpl business;

    @GetMapping
    public Flux<User> getAllUser() {
        return business.getAll();
    }

    @PostMapping
    public Mono<User> insertUser(@RequestBody User user) {
        return business.insert(user);
    }

    @PutMapping("/{id}")
    public Mono<User> updateUser(@RequestBody User user, @PathVariable("id") Integer id) {
        return business.update(id, user);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteUser(@PathVariable("id") Integer id) {
        return business.delete(id);
    }
}
