package com.example.ms.spring.users.business;

import com.example.ms.spring.users.model.Department;
import com.example.ms.spring.users.model.User;
import com.example.ms.spring.users.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UserBusinessImpl implements UserBusiness{

    private final UserRepository repository;

    @Override
    public Flux<User> getAll() {
        return repository.findAll();
    }

    @Override
    public Mono<User> insert(User user) {
        return repository.save(user);
    }

    @Override
    public Mono<User> update(Integer id, User user) {
        return repository.findById(id).map(updateUser -> User.builder()
                .id(id)
                .name(user.getName())
                .lastname(user.getLastname())
                .photo(user.getPhoto())
                .password(user.getPassword())
                .jobTitle(user.getJobTitle())
                .build()
        ).flatMap(repository::save);
    }

    @Override
    public Mono<Void> delete(Integer id) {
        return repository.deleteById(id);
    }
}
