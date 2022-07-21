package com.tuimm.springbootawssecretmanagersample.controller;

import com.tuimm.springbootawssecretmanagersample.domain.model.Foo;
import com.tuimm.springbootawssecretmanagersample.service.FooService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class FooController {

  private final FooService fooService;

  @Operation(
    summary = "Create a new foo",
    description = "Create a new foo",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Foo.class)))
  )
  @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "create foo")})
  @PostMapping(
    path = "/foo"
  )
  public Mono<ResponseEntity<Void>> create(
     @RequestBody Foo foo) {

    return fooService.createUser(foo).map(
      fooCreated -> new ResponseEntity<>(HttpStatus.CREATED));

  }
  @Operation(
    summary = "Get a foo by id.",
    description = "Get a foo by id."
  )
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "get Foo By Id"),
    @ApiResponse(responseCode = "404", description = "foo not found")})
  @GetMapping(
    path = "/foo/{id}"
  )
  public Mono<ResponseEntity<Foo>> retrieveById(
    @Parameter(name = "id", required = true) @PathVariable(value = "id") UUID id) {
    return this.fooService.findById(id).map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build());
  }

}
