package com.example.ms.spring.client.business;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface GenericBusiness<T, K> {
    Flux<T> findAll();
    Mono<T> findById(K k);
    Mono<T> insert(T t);
    Mono<T> update(K k, T t);
    Mono<Void> delete(K k);
}
