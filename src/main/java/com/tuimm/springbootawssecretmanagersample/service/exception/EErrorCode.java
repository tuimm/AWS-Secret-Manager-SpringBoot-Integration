package com.tuimm.springbootawssecretmanagersample.service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * <p>
 * <p>
 * <p>
 * <p>
 * @author cjrequena
 * @version 1.0
 * @since JDK1.8
 * @see
 *
 */
public enum EErrorCode {

  ERROR_001("error_001"),
  ERROR_002("error_002"),
  ERROR_003("error_003");

  @Getter
  private String code;

  EErrorCode(String code) {
    this.code = code;
  }

}


