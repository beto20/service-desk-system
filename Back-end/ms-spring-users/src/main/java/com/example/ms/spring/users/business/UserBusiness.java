package com.example.ms.spring.users.business;

import com.example.ms.spring.users.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserBusiness {
    Flux<User> getAll();
    Mono<User> insert(User user);
    Mono<User> update(Integer id, User user);
    Mono<Void> delete(Integer id);
}
