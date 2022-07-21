package com.tuimm.springbootawssecretmanagersample.domain.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document
public class Foo {

  @Id
  private UUID id;

  private String fooName;
}
