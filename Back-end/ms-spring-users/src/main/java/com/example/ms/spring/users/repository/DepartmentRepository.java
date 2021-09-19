package com.example.ms.spring.users.repository;

import com.example.ms.spring.users.model.Department;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends ReactiveMongoRepository<Department, String> {
}
