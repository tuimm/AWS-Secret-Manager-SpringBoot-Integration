package com.tuimm.springbootawssecretmanagersample.service;

import com.tuimm.springbootawssecretmanagersample.db.repository.FooRepository;
import com.tuimm.springbootawssecretmanagersample.domain.model.Foo;
import com.tuimm.springbootawssecretmanagersample.service.exception.EErrorCode;
import com.tuimm.springbootawssecretmanagersample.service.exception.ServiceException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@AllArgsConstructor
public class FooService {

  private FooRepository fooRepository;

  public Mono<Foo> createUser(Foo userEntity) {
    userEntity.setId(UUID.randomUUID());
    return fooRepository.save(userEntity);
  }

  public Mono<Foo> findById(UUID id) {
    return fooRepository.findById(id).switchIfEmpty(Mono.error(new ServiceException(EErrorCode.ERROR_001)));
  }
}
