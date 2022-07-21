package com.tuimm.springbootawssecretmanagersample.db.repository;

import com.tuimm.springbootawssecretmanagersample.domain.model.Foo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.UUID;

public interface FooRepository extends ReactiveMongoRepository<Foo, UUID> {
}
