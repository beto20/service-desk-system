package com.alberto.ms.spring.helpdesk.repository;

import com.alberto.ms.spring.helpdesk.model.Operator;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface OperatorRepository extends ReactiveMongoRepository<Operator, String> {
}
