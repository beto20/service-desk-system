package com.example.ms.spring.users.business;

import com.example.ms.spring.users.model.Department;
import com.example.ms.spring.users.model.Role;
import com.example.ms.spring.users.model.User;
import com.example.ms.spring.users.repository.DepartmentRepository;
import com.example.ms.spring.users.repository.RoleRepository;
import com.example.ms.spring.users.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UserBusinessImpl implements UserBusiness{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public Flux<User> getAll() {
        return userRepository.findAll();
    }
/////////
    public Flux<Role> getAllRole() {
        return roleRepository.findAll();
    }
    public Mono<Role> insertRole(Role role) {
        return roleRepository.save(role);
    }
////////////

    @Override
    public Mono<User> insert(User user) {
        return userRepository.save(user);
    }

    @Override
    public Mono<User> update(String id, User user) {
        return userRepository.findById(id).map(updateUser -> User.builder()
                .id(id)
                .name(user.getName())
                .lastname(user.getLastname())
                .photo(user.getPhoto())
                .password(user.getPassword())
                .jobTitle(user.getJobTitle())
                .build()
        ).flatMap(userRepository::save);
    }

    @Override
    public Mono<Void> delete(String id) {
        return userRepository.deleteById(id);
    }
}
