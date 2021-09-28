package com.alberto.ms.spring.helpdesk.repository;

import com.alberto.ms.spring.helpdesk.model.Solution;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface SolutionRepository extends ReactiveMongoRepository<Solution, String> {
}
