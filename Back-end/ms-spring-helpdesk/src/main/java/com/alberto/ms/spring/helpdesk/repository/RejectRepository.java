package com.alberto.ms.spring.helpdesk.repository;

import com.alberto.ms.spring.helpdesk.model.Reject;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface RejectRepository extends ReactiveMongoRepository<Reject, String> {
}
