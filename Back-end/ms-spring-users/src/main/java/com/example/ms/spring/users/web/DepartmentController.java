package com.example.ms.spring.users.web;

import com.example.ms.spring.users.business.DepartmentBusinessImpl;
import com.example.ms.spring.users.business.UserBusinessImpl;
import com.example.ms.spring.users.model.Department;
import com.example.ms.spring.users.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentBusinessImpl business;


    @GetMapping("/")
    public Flux<Department> getAllUser() {
        return business.getAll();
    }

    @PostMapping("/")
    public Mono<Department> insertUser(@RequestBody Department department) {
        return business.insert(department);
    }

    @PutMapping("{id}")
    public Mono<Department> updateUser(@RequestBody Department department, @PathVariable("id") String id) {
        return business.update(id, department);
    }

    @DeleteMapping("{id}")
    public Mono<Void> deleteUser(@PathVariable("id") String id) {
        return business.delete(id);
    }

}
