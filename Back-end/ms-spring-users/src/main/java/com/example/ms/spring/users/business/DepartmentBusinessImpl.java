package com.example.ms.spring.users.business;

import com.example.ms.spring.users.model.Department;
import com.example.ms.spring.users.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class DepartmentBusinessImpl {

    private final DepartmentRepository repository;

    public Flux<Department> getAll() {
        return repository.findAll();
    }

    public Mono<Department> insert(Department department) {
        return repository.save(department);
    }

    public Mono<Department> update(String id, Department department) {
        return repository.findById(id).map(updateDepartment -> Department.builder()
                .id(id)
                .departmentName(department.getDepartmentName())
                .build()
        ).flatMap(repository::save);
    }

    public Mono<Void> delete(String id) {
        return repository.deleteById(id);
    }

}
